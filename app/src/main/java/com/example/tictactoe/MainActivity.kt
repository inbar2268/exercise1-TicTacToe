package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var cellsPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var playerTurn = 1  //x-> 1, o-> 2
    private var totalTurns = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val cellsList = getCellsList()

        cellsList.forEachIndexed { index, cell ->
            cell?.setOnClickListener() {
                if (isCellEmpty(index)) {
                    cellClicked(cell as ImageButton, index)
                    cell.isEnabled = false
                }
            }
        }

        //binding?.restartGameButton?.setOnClickListener(){restartGame()}
    }

    private fun cellClicked(imageButton: ImageButton, cellNumber: Int){
        Log.d("TAG", "totalTurns: ${totalTurns}")
        cellsPositions[cellNumber] = playerTurn
        if (playerTurn == 1) {
            imageButton.setImageResource(R.drawable.ximage)
        } else {
            imageButton.setImageResource(R.drawable.oimage)
        }
    }

    private fun isCellEmpty(cellNumber: Int): Boolean{
        return if(cellsPositions[cellNumber] == 0){
            true
        } else{
            false
        }
    }

    private fun getCellsList(): List<ImageButton?> {
        return listOf(binding?.button1, binding?.button2, binding?.button3, binding?.button4, binding?.button5, binding?.button6, binding?.button7, binding?.button8 ,binding?.button9)
    }

}
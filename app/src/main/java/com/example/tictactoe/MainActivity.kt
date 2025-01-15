package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
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

        val cellsList = getCellsList();

        cellsList.forEachIndexed { index, cell ->
            cell?.setOnClickListener() {
                if (isCellEmpty(index)) {
                    game(cell as ImageButton, index)
                    cell.isEnabled = false
                }
            }
        }
        binding?.restartGameButton?.setOnClickListener() { restartGame() }
    }

    private fun game(imageButton: ImageButton, cellNumber: Int) {
        cellClicked(imageButton, cellNumber)
        if (checkWin()) {
            if (playerTurn == 1) {
                gameOver("Player X won");
            } else {
                gameOver("Player O won");
            }
        } else {
            if (checkDraw()) {
                gameOver("It's a draw");
            } else {
                switchTurn();
            }
        }
    }

    private fun cellClicked(imageButton: ImageButton, cellNumber: Int) {
        cellsPositions[cellNumber] = playerTurn
        if (playerTurn == 1) {
            imageButton.setImageResource(R.drawable.ximage)
        } else {
            imageButton.setImageResource(R.drawable.oimage)
        }
    }

    private fun isCellEmpty(cellNumber: Int): Boolean {
        return if (cellsPositions[cellNumber] == 0) {
            true
        } else {
            false
        }
    }

    private fun getCellsList(): List<ImageButton?> {
        return listOf(
            binding?.button1,
            binding?.button2,
            binding?.button3,
            binding?.button4,
            binding?.button5,
            binding?.button6,
            binding?.button7,
            binding?.button8,
            binding?.button9
        )
    }

    private fun switchTurn() {
        if (playerTurn == 1) {
            playerTurn = 2
            binding?.playerSymbol?.text = "Player: O"
        } else {
            playerTurn = 1
            binding?.playerSymbol?.text = "Player: X"
        }
    }

    private fun gameOver(gameResult: String) {
        binding?.playerSymbol?.text = gameResult
        binding?.restartGameButton?.visibility = View.VISIBLE;

        val cellList = getCellsList();
        cellList.forEach { cell ->
            cell?.isEnabled = false;
        }

    }

    private fun restartGame() {
        cellsPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        playerTurn = 1  //x-> 1, o-> 2
        binding?.restartGameButton?.visibility = View.GONE;
        binding?.playerSymbol?.text = "Player: X";
        val cellList = getCellsList();
        cellList.forEach { cell ->
            cell?.isEnabled = true;
            cell?.setImageResource(0)
        }
    }

    private fun checkWin(): Boolean {

        for (i in 0..2) {
            if (cellsPositions[i * 3] == cellsPositions[i * 3 + 1] && cellsPositions[i * 3] == cellsPositions[i * 3 + 2] && cellsPositions[i * 3] != 0) {
                return true
            }
        }

        for (i in 0..2) {
            if (cellsPositions[i] == cellsPositions[i + 3] && cellsPositions[i] == cellsPositions[i + 6] && cellsPositions[i] != 0) {
                return true
            }
        }

        if (cellsPositions[0] == cellsPositions[4] && cellsPositions[0] == cellsPositions[8] && cellsPositions[0] != 0) {
            return true
        }

        if (cellsPositions[2] == cellsPositions[4] && cellsPositions[4] == cellsPositions[6] && cellsPositions[4] != 0) {
            return true
        }
        return false
    }

    private fun checkDraw(): Boolean {
        return cellsPositions.all { it != 0 }
    }
}



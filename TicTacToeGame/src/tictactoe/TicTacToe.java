package tictactoe;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;


    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel("Tic Tac Toe");
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int turns =0;

    JButton resetButton = new JButton("Reset");


    TicTacToe()
    {
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        resetButton.setSize(80,40);
        //resetButton.setLocation(500,10);
        frame.add(resetButton,BorderLayout.AFTER_LAST_LINE);
        resetButton.addActionListener(e ->
            resetBoard()
        );
        resetButton.setFocusable(false); // Ensure it doesn't lose visibility during focus
        resetButton.setOpaque(true);
        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setOpaque(true);
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.GRAY);
        frame.add(boardPanel);
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j< 3 ; j++) {
                checkTile(i, j);
            }
        }
    }
    public void gameOver()
    {
        horizontalCheck();
        verticalCheck();
        diagonalCheck();
        draw();
    }

    public void setWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer + " is the Winner");
    }

    void checkTile(int i, int j)
    {
            JButton tile = new JButton();
            board[i][j] = tile;
            boardPanel.add(tile);
            tile.setFont(new Font("Arial", Font.BOLD, 120));
            tile.setFocusable(false);

            tile.addActionListener(e -> {
                if (gameOver) return;
                JButton tile3 = (JButton) e.getSource();
                if (tile3.getText().isEmpty()) {
                    tile3.setText(currentPlayer);
                    turns++;
                    gameOver();
                    if (!gameOver) {

                        currentPlayer = Objects.equals(currentPlayer, playerO) ? playerX : playerO;
                        textLabel.setText(currentPlayer + "'s Turn");
                    }
                }

            });

    }
    void horizontalCheck()
    {
        for(int r=0 ; r<3 ;r++)
        {
            if(board[r][0].getText().isEmpty() || board[r][1].getText().isEmpty() || board[r][2].getText().isEmpty())
                continue;
            if(Objects.equals(board[r][0].getText(), board[r][1].getText())
                    && Objects.equals(board[r][2].getText(), board[r][1].getText()))
            {
                for(int i = 0 ; i < 3 ; i++)
                {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
    }
    void verticalCheck()
    {
        for(int c=0 ; c<3 ;c++)
        {
            if(board[0][c].getText().isEmpty()
                    || board[1][c].getText().isEmpty() || board[2][c].getText().isEmpty())
                continue;

            if(Objects.equals(board[0][c].getText(), board[1][c].getText())
                    && Objects.equals(board[1][c].getText(), board[2][c].getText()))
            {
                for(int i = 0 ; i < 3 ; i++)
                {
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;
            }
        }
    }
    void diagonalCheck()
    {
        if (!board[0][0].getText().isEmpty() && Objects.equals(board[0][0].getText(), board[1][1].getText())
                && Objects.equals(board[2][2].getText(), board[1][1].getText())) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        if (!board[0][2].getText().isEmpty() && Objects.equals(board[2][0].getText(), board[1][1].getText())
                && Objects.equals(board[0][2].getText(), board[1][1].getText())
        ) {
            setWinner(board[2][0]);
            setWinner(board[1][1]);
            setWinner(board[0][2]);
            gameOver = true;
        }
    }
    void draw()
    {
        if(turns ==9)
        {
            gameOver=true;
            textLabel.setText("Game is Draw.");
        }
    }
    void resetBoard()
    {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setText(""); // Clear the text
                board[i][j].setEnabled(true); // Enable the button if disabled
                board[i][j].setForeground(Color.black);
            }
        gameOver = false;
        currentPlayer = playerX; // Reset to the starting player
        turns =0;
        textLabel.setText(currentPlayer + "'s Turn");
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameOver = false;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        // Create 3x3 buttons
        Font font = new Font("Arial", Font.BOLD, 50);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        // Reset button
        JButton resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.addActionListener(e -> resetGame());

        add(boardPanel, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        JButton buttonClicked = (JButton) e.getSource();

        // If already marked, ignore
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        buttonClicked.setText(String.valueOf(currentPlayer));

        // Check win
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            gameOver = true;
            return;
        }

        // Check draw
        if (checkDraw()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            gameOver = true;
            return;
        }

        // Switch player
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Win logic
    private boolean checkWin() {
        // Rows and columns
        for (int i = 0; i < 3; i++) {
            // Row check
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }

            // Column check
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Diagonals
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    // Draw check
    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }

    // Reset game
    private void resetGame() {
        currentPlayer = 'X';
        gameOver = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}


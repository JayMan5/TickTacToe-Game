import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {

    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';

    public TicTacToe() {
        // Initialize board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        // Create game panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 2, 2)); // Add gaps for borders
        panel.setBackground(Color.WHITE); // Set panel background to white

        // Create buttons for each cell
        JButton[][] buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 40)); // Optional: make X/O bigger
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.addActionListener(new ButtonListener(i, j, button));
                panel.add(button);
                buttons[i][j] = button;
            }
        }

        // Add panel to frame
        add(panel);

        // Set up frame
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        private int row;
        private int col;
        private JButton button;

        public ButtonListener(int row, int col, JButton button) {
            this.row = row;
            this.col = col;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Update board and button text
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                button.setText(String.valueOf(currentPlayer));

                // Check for winning conditions
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    System.exit(0);
                }

                // Check for draw
                if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    System.exit(0);
                }

                // Switch players
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
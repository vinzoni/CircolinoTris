import javax.swing.*;
import java.awt.*;

public class BoardUI {
    private Board board;
    private Player player1;
    private Player player2;

    private final JButton[][] buttons = new JButton[3][3];
    private final JPanel panel;

    public BoardUI() {
        this.panel = new JPanel();
        this.panel.setLayout(new java.awt.GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final JButton button = new JButton();
                buttons[i][j] = button;
                button.setText("");
                button.setName(i + "" + j);
                button.addActionListener(
                        e -> {
                            if (!button.getText().equals(""))
                                return;

                            if ((player1 != null) && (player1.waitingForAMove()))
                                player1.acceptMove(button);
                            else if ((player2 != null) && (player2.waitingForAMove()))
                                player2.acceptMove(button);
                        });
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                button.setFont(Configuration.UI_BOARD_BUTTON_FONT);
                panel.add(button);
            }
        }
        panel.setPreferredSize(new Dimension(Configuration.UI_BOARD_SIZE,Configuration.UI_BOARD_SIZE));
    }

    public void setGame(Game game) {
        this.board = game.board();
        this.player1 = game.player1();
        this.player2 = game.player2();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public void redraw(String move) {
        int x = move.charAt(0) - '0';
        int y = move.charAt(1) - '0';
        redrawButton(buttons[x][y]);

        if (board.winningStatus())
            showWinningTriplet();
    }

    private void redrawButton(JButton button) {
        if (board.moveCount() % 2 == 1) {
            button.setForeground(Color.blue);
            button.setText("X");
        } else {
            button.setForeground(Color.red);
            button.setText("O");
        }
    }

    public JPanel panel() { return panel; }

    private void showWinningTriplet() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (! board.winningSquare(buttons[i][j].getName()))
                    buttons[i][j].setEnabled(false);
            }
        }
    }

}

import entities.Field;
import entities.Player;

import java.util.Scanner;

public class TicTacToeGame {
    private Field field;
    private Player player_x;
    private Player player_o;

    public TicTacToeGame() {
        init();
    }

    private void init() {
        field = new Field();
        player_x = new Player("X");
        player_o = new Player("O");
    }

    public void start() {
        System.out.println("TicTacToe game!");
        boolean turn = true;
        Player curPlayer = player_x;
        Scanner in = new Scanner(System.in);
        System.out.println(field);
        while (!field.isGameOver(player_x, player_o)) {
            if (turn) curPlayer = player_x;
            else curPlayer = player_o;

            System.out.println(curPlayer + " turn!");
            System.out.println("Input position (1-9)");
            int pos = in.nextInt();
            if (pos < 0 || pos > field.getFIELD_SIZE() * field.getFIELD_SIZE()) {
                System.out.println("Incorrect position try again");
                continue;
            }

            try {
                field.makeMoveByPlayer(curPlayer, pos);
            } catch (IllegalArgumentException e) {
                System.out.println("Mark on this position already exist!");
                continue;
            }
            System.out.println(field);
            turn = !turn;
        }
        System.out.println("GAME OVER!");
        if (field.isPlayerWon(curPlayer)) System.out.println(curPlayer + " won!");
        else System.out.println("TIE!");
    }
}

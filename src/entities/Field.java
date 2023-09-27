package entities;

import java.util.Arrays;

public class Field {
    private String[][] field;
    private final int FIELD_SIZE = 3;

    public Field() {
        initField();
    }

    private void initField() {
        field = new String[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            Arrays.fill(field[i], "_");
        }
    }

    public void makeMoveByPlayer(Player player, int pos) {
        int i = (pos - 1) / FIELD_SIZE;
        int j = (pos - 1) % FIELD_SIZE;

        if (field[i][j].equals("_")) {
            field[i][j] = player.getPlayerMark();
        } else {
            throw new IllegalArgumentException();
        }

    }

    public boolean isFieldComplete() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j].equals("_")) return false;
            }
        }
        return true;
    }

    public boolean isPlayerWon(Player player) {
        String playerMark = player.getPlayerMark();
        for (int i = 0; i < FIELD_SIZE; i++) {
            //rows
            if (field[i][0].equals(playerMark) && field[i][1].equals(playerMark) && field[i][2].equals(playerMark))
                return true;
            //columns
            if (field[0][i].equals(playerMark) && field[1][i].equals(playerMark) && field[2][i].equals(playerMark))
                return true;
        }
        //diag upper left -> lower right
        int posStep = FIELD_SIZE + 1;
        int iter = 0;
        StringBuilder sb = new StringBuilder();
        while (iter < FIELD_SIZE * FIELD_SIZE) {
            sb.append(field[iter / 3][iter % 3]);
            iter += posStep;
        }
        if (sb.toString().replaceAll(playerMark, "").equals("")) return true;

        //diag upper right -> lower left
        posStep = FIELD_SIZE - 1;
        iter = 0;
        sb = new StringBuilder();
        while (iter < FIELD_SIZE * FIELD_SIZE - 1) {
            sb.append(field[iter / 3][iter % 3]);
            iter += posStep;
        }
        return sb.toString().replaceAll(playerMark, "").equals("");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                sb.append(field[i][j]);
            }
            if (!(i == FIELD_SIZE - 1)) sb.append('\n');
        }
        return sb.toString();
    }

    public int getFIELD_SIZE() {
        return FIELD_SIZE;
    }

    public boolean isGameOver(Player player1, Player player2) {
        return isFieldComplete() || isPlayerWon(player1) || isPlayerWon(player2);
    }
}

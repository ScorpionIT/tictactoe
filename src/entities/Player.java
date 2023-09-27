package entities;

public class Player {
    private final String playerMark;

    public Player(String playerMark) {

        this.playerMark = playerMark;
    }

    public String getPlayerMark() {
        return playerMark;
    }

    @Override
    public String toString() {
        return "Player " + playerMark;
    }
}

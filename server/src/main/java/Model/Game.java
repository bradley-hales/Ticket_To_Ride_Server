package Model;

import java.util.HashMap;

/**
 * Created by jbasden on 1/29/19.
 */

public class Game {
    private HashMap<String, Player> gamePlayers;
    private String gameName;
    private int numPlayers;

    public void addPlayer(String userName) {
        //TODO: Implement
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public HashMap<String, Player> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(HashMap<String, Player> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}

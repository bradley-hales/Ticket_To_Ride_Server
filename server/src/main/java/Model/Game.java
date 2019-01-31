package Model;

import java.util.HashMap;

/**
 * Created by jbasden on 1/29/19.
 */

public class Game {
    private HashMap<String, Player> gamePlayers;
    private String gameName;
    private int numPlayers;

    public boolean addPlayer(String userName) {
        if (gamePlayers.containsKey(userName)) {
            return false;
        }
        if (gamePlayers.size() == numPlayers) {
            return false;
        }
        Player newPlayer = new Player();
        newPlayer.setUserName(userName);
        int numPlayersSoFar = gamePlayers.size();
        if (numPlayersSoFar == 0) {
            newPlayer.setColor("Blue");
        }
        else if (numPlayersSoFar == 1) {
            newPlayer.setColor("Green");
        }
        else if (numPlayersSoFar == 2) {
            newPlayer.setColor("Red");
        }
        else if (numPlayersSoFar == 3) {
            newPlayer.setColor("Yellow");
        }
        else {
            newPlayer.setColor("Black");
        }
        gamePlayers.put(userName, newPlayer);
        return true;
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

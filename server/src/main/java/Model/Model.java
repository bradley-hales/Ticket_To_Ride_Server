package Model;

import java.util.HashMap;

/**
 * Created by jbasden on 1/30/19.
 */

public class Model {
    private static final Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    private Model() {
    }
    private HashMap<String, Game> games = new HashMap<>();
    private HashMap<String, User> users = new HashMap<>();
    public boolean createGame(String gameName, int numPlayers) {
        if (games.containsKey(gameName)) {
            return false;
        }
        if (numPlayers < 2 || numPlayers > 5) {
            return false;
        }
        Game game = new Game();
        game.setGameName(gameName);
        game.setNumPlayers(numPlayers);
        games.put(gameName, game);
        return true;
    }
    public boolean authenticateUser(String userName, String password) {
        if (!users.containsKey(userName)) {
            return false;
        }
        if (users.get(userName).equals(password)) {
            return true;
        }
        return false;
    }
    public boolean createUser(String userName, String password) {
        if (users.containsKey(userName)) {
            return false;
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        users.put(userName, user);
        return true;
    }

    public boolean checkIfGameReady(String gameName) {
        Game gameToCheck = games.get(gameName);
        if (gameToCheck.getNumPlayers() == gameToCheck.getGamePlayers().size()) {
            return true;
        }
        return false;
    }
}

package Model;

import java.util.HashMap;

import Result.GetCommandsResult;

/**
 * Created by jbasden on 1/30/19.
 */

public class Model {
    private static final Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    private Model() {}

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
        //TODO: Create Client Commands for other users
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

    public boolean beginGame(String gameName) {
        Game gameToCheck = games.get(gameName);
        if (gameToCheck == null) {
            return false;
        }
        if (gameToCheck.getNumPlayers() == gameToCheck.getGamePlayers().size()) {
            return true;
        }
        //TODO: Implement beginning the game
        return false;
    }

    public boolean joinGame(String userName, String gameName) {
        Game game = games.get(gameName);
        if (game == null) {
            return false;
        }
        //TODO: Create Client Commands for other players
        //TODO: Remove game is necessary for other users
        return game.addPlayer(userName);
    }

    public String getPlayerColor(String gameName, String userName) {
        Game game = games.get(gameName);
        if (game == null) {
            return "False Color";
        }
        Player player = game.getGamePlayers().get(userName);
        if (player == null) {
            return "False Color";
        }
        return player.getColor();
    }

    public GetCommandsResult getCommands(String userName) {
        GetCommandsResult res = new GetCommandsResult();
        User user = users.get(userName);
        res.setUserName(userName);
        if (user == null) {
            res.setErrorMessage("User does not exist");
            res.setSuccess(false);
            return res;
        }
        res.setCommandList(user.getCommands());
        res.setSuccess(true);
        return res;
    }
}

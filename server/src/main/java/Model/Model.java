package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.SysexMessage;

import Command.ClientCommand.AddGameCommand;
import Command.ClientCommand.BeginGameCommand;
import Command.ClientCommand.RemoveGameCommand;
import Command.ClientCommand.iClientCommand;
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

    public boolean createGame(String gameName, int numPlayers, String userName) {
        if (games.containsKey(gameName)) {
            return false;
        }
        if (numPlayers < 2 || numPlayers > 5) {
            return false;
        }
        Game game = new Game();
        game.setGameName(gameName);
        game.setNumPlayers(numPlayers);
        game.setStarted(false);
        games.put(gameName, game);
        AddGameCommand addGameCommand = new AddGameCommand();
        addGameCommand.setGameName(gameName);
        addGameCommand.setNumPlayers(numPlayers);
        for (User user: users.values()) {
            if (!user.getUserName().equals(userName)) {
                user.addCommand(addGameCommand);
            }
        }
        for (User user: users.values()) {
            List<iClientCommand> commands = user.getCommands();
            List<AddGameCommand> addGameCommands = new ArrayList<>();
            for (iClientCommand command: commands) {
                addGameCommands.add((AddGameCommand) command);
            }
        }
        return true;
    }

    public boolean authenticateUser(String userName, String password) {
        if (!users.containsKey(userName)) {
            return false;
        }
        return users.get(userName).getPassword().equals(password);
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

    public boolean joinGame(String userName, String gameName) {
        Game game = games.get(gameName);
        if (game == null) {
            return false;
        }
        if (game.addPlayer(userName)) {
            if (game.getNumPlayers() == game.getGamePlayers().size()) {
                List<String> userNamesOfPlayers = new ArrayList<>();
                for (Player player : game.getGamePlayers().values()) {
                    userNamesOfPlayers.add(player.getUserName());
                }
                BeginGameCommand beginGameCommand = new BeginGameCommand();
                beginGameCommand.setGameName(gameName);
                for (String user : userNamesOfPlayers) {
                    users.get(user).addCommand(beginGameCommand);
                }
                game.setStarted(true);
                RemoveGameCommand removeGameCommand = new RemoveGameCommand();
                removeGameCommand.setGameName(gameName);
                for (User user: users.values()) {
                    if (!userNamesOfPlayers.contains(user.getUserName())) {
                        user.addCommand(removeGameCommand);
                    }
                }
            }
            return true;
        }
        return false;
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
        res.setCommandList(new ArrayList<>(user.getCommands()));
        res.setSuccess(true);
        user.clearCommands();
        return res;
    }

    public void addAllAddableGamesToCommandLists(String userName) {
        users.get(userName).clearCommands();
        for (Game gameToCheck: games.values()) {
            if (!gameToCheck.isStarted()) {
                AddGameCommand addGameCommand = new AddGameCommand();
                addGameCommand.setGameName(gameToCheck.getGameName());
                addGameCommand.setNumPlayers(gameToCheck.getNumPlayers());
                users.get(userName).addCommand(addGameCommand);
            }
        }
    }

    public HashMap<String, Game> getGames() {
        return games;
    }

    public void setGames(HashMap<String, Game> games) {
        this.games = games;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
}

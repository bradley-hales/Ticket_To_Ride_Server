package Command.ClientCommand;

import Model.Game;

/**
 * Created by jbasden on 1/29/19.
 */

public class BeginGameCommand implements iClientCommand {
    private String gameName;
    private Game game;
    private boolean success;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

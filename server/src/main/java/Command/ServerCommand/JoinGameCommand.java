package Command.ServerCommand;

import Request.JoinGameRequest;
import Result.JoinGameResult;
import Service.JoinGameService;

/**
 * Created by jbasden on 1/29/19.
 */

public class JoinGameCommand implements iServerCommand {
    private JoinGameRequest data;
    public JoinGameResult execute() {
        JoinGameService joinGameService = new JoinGameService();
        return joinGameService.joinGame(data);
    }

    public JoinGameRequest getData() {
        return data;
    }

    public void setData(JoinGameRequest data) {
        this.data = data;
    }
}

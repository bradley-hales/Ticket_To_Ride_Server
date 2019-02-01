package Command.ServerCommand;

import Request.CreateGameRequest;
import Result.CreateGameResult;
import Service.CreateGameService;

/**
 * Created by jbasden on 1/29/19.
 */

public class CreateGameCommand implements iServerCommand {
    private CreateGameRequest data;
    public CreateGameResult execute() {
        CreateGameService createGameService = new CreateGameService();
        return createGameService.createGame(data);
    }

    public CreateGameRequest getData() {
        return data;
    }

    public void setData(CreateGameRequest data) {
        this.data = data;
    }
}

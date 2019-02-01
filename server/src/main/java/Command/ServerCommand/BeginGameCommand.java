package Command.ServerCommand;

import Request.BeginGameRequest;
import Result.BeginGameResult;
import Service.BeginGameService;

/**
 * Created by jbasden on 1/29/19.
 */

public class BeginGameCommand implements iServerCommand {
    private BeginGameRequest data;
    public BeginGameResult execute() {
        BeginGameService beginGameService = new BeginGameService();
        return beginGameService.beginGame(data);
    }

    public BeginGameRequest getData() {
        return data;
    }

    public void setData(BeginGameRequest data) {
        this.data = data;
    }
}

package Service;

import Model.Model;
import Request.CreateGameRequest;
import Result.CreateGameResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class CreateGameService {
    Model model = Model.getInstance();
    private CreateGameRequest data;
    public CreateGameResult createGame() {
        CreateGameResult res = new CreateGameResult();
        if (model.createGame(data.getGameName(), data.getNumPlayers())) {
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Game Name was already taken, or invalid number of players");
        res.setSuccess(false);
        return res;
    }
}

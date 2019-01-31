package Service;

import Model.Model;
import Request.CreateGameRequest;
import Result.CreateGameResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class CreateGameService {
    Model model = Model.getInstance();
    public CreateGameResult createGame(CreateGameRequest req) {
        CreateGameResult res = new CreateGameResult();
        if (model.createGame(req.getGameName(), req.getNumPlayers())) {
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Game Name was already taken, or invalid number of players");
        res.setSuccess(false);
        return res;
    }
}

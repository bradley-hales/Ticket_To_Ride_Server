package Service;

import Model.Model;
import Request.JoinGameRequest;
import Result.JoinGameResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class JoinGameService {
    Model model = Model.getInstance();
    public JoinGameResult joinGame(JoinGameRequest req) {
        JoinGameResult res = new JoinGameResult();
        if (model.joinGame(req.getUserName(), req.getGameName())) {
            res.setPlayerColor(model.getPlayerColor(req.getGameName(), req.getUserName()));
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Player was not allowed to join the game");
        res.setSuccess(false);
        return  res;
    }
}

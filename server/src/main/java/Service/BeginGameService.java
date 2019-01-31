package Service;

import Model.Model;
import Request.BeginGameRequest;
import Result.BeginGameResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class BeginGameService {
    Model model = Model.getInstance();
    public BeginGameResult beginGame(BeginGameRequest req) {
        BeginGameResult res = new BeginGameResult();
        if (model.beginGame(req.getGameName())) {
            res.setGameName(req.getGameName());
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Game could not be started");
        res.setSuccess(false);
        return res;
    }
}

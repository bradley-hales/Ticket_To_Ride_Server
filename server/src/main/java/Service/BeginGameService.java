package Service;

import Model.Model;
import Request.BeginGameRequest;
import Result.BeginGameResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class BeginGameService {
    Model model = Model.getInstance();
    private BeginGameRequest data;

    public BeginGameResult beginGame() {
        BeginGameResult res = new BeginGameResult();
        if (model.checkIfGameReady(data.getGameName())) {
            //TODO: Implement Beginning a new game
            res.setGameName(data.getGameName());
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Not enough players");
        res.setSuccess(false);
        return res;
    }
    public BeginGameRequest getData() {
        return data;
    }

    public void setData(BeginGameRequest data) {
        this.data = data;
    }
}

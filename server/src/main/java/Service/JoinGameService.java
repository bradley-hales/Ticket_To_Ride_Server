package Service;

import java.util.HashMap;

import Model.Model;
import Request.JoinGameRequest;
import Result.JoinGameResult;
import Model.Game;

/**
 * Created by jbasden on 1/29/19.
 */

public class JoinGameService {
    Model model = Model.getInstance();
    HashMap<String, Game> games = model.getGames();
    public JoinGameResult joinGame(JoinGameRequest req) {
        JoinGameResult res = new JoinGameResult();
        if (model.joinGame(req.getUserName(), req.getGameName())) {
            res.setPlayerColor(model.getPlayerColor(req.getGameName(), req.getUserName()));
            res.setUserName(req.getUserName());
            res.setGameName(req.getGameName());
            Game game = games.get(req.getGameName());
            res.setNumPlayers(game.getNumPlayers());
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Player was not allowed to join the game");
        res.setSuccess(false);
        return  res;
    }
}

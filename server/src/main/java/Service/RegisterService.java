package Service;

import Model.Model;
import Request.RegisterRequest;
import Result.RegisterResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class RegisterService {
    Model model = Model.getInstance();
    public RegisterResult register(RegisterRequest req) {
        RegisterResult res = new RegisterResult();
        if (model.createUser(req.getUserName(), req.getPassword())) {
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Username was already taken");
        res.setSuccess(false);
        return res;
    }
}

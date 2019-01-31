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
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            res.setErrorMessage("Passwords must match");
            res.setSuccess(false);
            return res;
        }
        if (model.createUser(req.getUserName(), req.getPassword())) {
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Username was already taken");
        res.setSuccess(false);
        return res;
    }
}

package Service;

import Model.Model;
import Request.RegisterRequest;
import Result.RegisterResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class RegisterService {
    Model model = Model.getInstance();
    private RegisterRequest data;
    public RegisterResult register() {
        RegisterResult res = new RegisterResult();
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            res.setErrorMessage("Passwords must match");
            res.setSuccess(false);
            return res;
        }
        if (model.createUser(data.getUserName(), data.getPassword())) {
            res.setSuccess(true);
            return res;
        }
        res.setErrorMessage("Username was already taken");
        res.setSuccess(false);
        return res;
    }

    public RegisterRequest getData() {
        return data;
    }

    public void setData(RegisterRequest data) {
        this.data = data;
    }
}

package Service;

import Request.LoginRequest;
import Result.LoginResult;

/**
 * Created by jbasden on 1/29/19.
 */

public class LoginService {
    Model.Model model = Model.Model.getInstance();
    private LoginRequest data;
    public LoginResult login() {
        LoginResult res = new LoginResult();
        if (model.authenticateUser(data.getUserName(), data.getPassword())) {
            res.setSuccess(true);
        }
        else {
            res.setErrorMessage("Invalid Login Credentials");
            res.setSuccess(false);
        }
        return res;
    }

    public LoginRequest getData() {
        return data;
    }

    public void setData(LoginRequest data) {
        this.data = data;
    }
}

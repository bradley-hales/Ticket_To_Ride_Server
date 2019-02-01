package Command.ServerCommand;

import Request.LoginRequest;
import Result.LoginResult;
import Service.LoginService;

/**
 * Created by jbasden on 1/29/19.
 */

public class LoginCommand implements iServerCommand {
    private LoginRequest data;
    public LoginResult execute() {
        LoginService loginService = new LoginService();
        return loginService.login(data);
    }

    public LoginRequest getData() {
        return data;
    }

    public void setData(LoginRequest data) {
        this.data = data;
    }
}

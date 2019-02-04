package Command.ServerCommand;

import Request.RegisterRequest;
import Result.RegisterResult;
import Service.RegisterService;

/**
 * Created by jbasden on 1/29/19.
 */

public class RegisterCommand implements iServerCommand {
    private RegisterRequest data;
    public RegisterCommand(RegisterRequest request) {
        data = request;
    }
    @Override
    public RegisterResult execute() {
        RegisterService registerService = new RegisterService();
        return registerService.register(data);
    }

    public RegisterRequest getData() {
        return data;
    }

    public void setData(RegisterRequest data) {
        this.data = data;
    }
}

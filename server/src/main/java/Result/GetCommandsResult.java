package Result;

import java.util.List;

import Command.ClientCommand.iClientCommand;

/**
 * Created by jbasden on 1/29/19.
 */

public class GetCommandsResult implements iResult {
    private String errorMessage;
    private boolean success;
    private List<iClientCommand> commandList;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<iClientCommand> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<iClientCommand> commandList) {
        this.commandList = commandList;
    }
}

package Command.ServerCommand;

import Command.ClientCommand.iClientCommand;
import Request.GetCommandsRequest;
import Result.GetCommandsResult;
import Service.GetCommandsService;

/**
 * Created by jbasden on 1/29/19.
 */

public class GetCommandsCommand implements iServerCommand {
    private GetCommandsRequest data;
    public GetCommandsResult execute() {
        GetCommandsService getCommandsService = new GetCommandsService();
        return getCommandsService.getCommands(data);
    }

    public GetCommandsRequest getData() {
        return data;
    }

    public void setData(GetCommandsRequest data) {
        this.data = data;
    }
}

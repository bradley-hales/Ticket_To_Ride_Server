package Model;

import java.util.ArrayList;
import java.util.List;

import Command.ClientCommand.iClientCommand;

/**
 * Created by jbasden on 1/29/19.
 */

public class User {
    private String userName;
    private String password;
    private List<iClientCommand> commandList = new ArrayList<>();

    public List<iClientCommand> getCommands() {
        List<iClientCommand> commands = new ArrayList<>();
        //TODO: Implement
        return commands;
    }
    public void addCommand(iClientCommand command) {
        //TODO:Implement
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

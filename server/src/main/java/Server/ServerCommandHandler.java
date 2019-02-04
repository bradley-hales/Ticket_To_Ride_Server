package Server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import Command.ServerCommand.CommandType;
import Command.ServerCommand.CreateGameCommand;
import Command.ServerCommand.GetCommandsCommand;
import Command.ServerCommand.JoinGameCommand;
import Command.ServerCommand.LoginCommand;
import Command.ServerCommand.RegisterCommand;
import Request.CreateGameRequest;
import Request.GetCommandsRequest;
import Request.JoinGameRequest;
import Request.LoginRequest;
import Request.RegisterRequest;
import Request.iRequest;
import Result.iResult;

import static Command.ServerCommand.CommandType.LOGIN;
import static Command.ServerCommand.CommandType.REGISTER;
import static Command.ServerCommand.CommandType.CREATE_GAME;
import static Command.ServerCommand.CommandType.JOIN_GAME;
import static Command.ServerCommand.CommandType.GET_COMMANDS;


/**
 * Created by jbasden on 1/31/19.
 */

public class ServerCommandHandler implements HttpHandler {
    private iRequest data;
    private iResult response;
    private String jsonStr = null;
    private String message = null;
    private Double result = null;
    CommandType type;

    public iResult execute() {
        switch (type){
            case CREATE_GAME:
                CreateGameCommand createGameCommand = new CreateGameCommand(data);
                return createGameCommand.execute();
            case GET_COMMANDS:
                GetCommandsCommand getCommandsCommand = new GetCommandsCommand(data);
                return getCommandsCommand.execute();
            case LOGIN:
                LoginCommand loginCommand = new LoginCommand(data);
                return loginCommand.execute();
            case REGISTER:
                RegisterCommand registerCommand = new RegisterCommand(data);
                return registerCommand.execute();
            case JOIN_GAME:
                JoinGameCommand joinGameCommand = new JoinGameCommand(data);
                return joinGameCommand.execute();
            default:
                return null;
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                Gson gson = new Gson();
                type = LOGIN;
                //CommandType type = reqHeaders.get("type"); //TODO: Check what Client Side is calling this
                switch (type) {
                    case LOGIN:
                        data = gson.fromJson(reqData, LoginRequest.class);
                    case REGISTER:
                        data = gson.fromJson(reqData, RegisterRequest.class);
                    case JOIN_GAME:
                        data = gson.fromJson(reqData, JoinGameRequest.class);
                    case CREATE_GAME:
                        data = gson.fromJson(reqData, CreateGameRequest.class);
                    case GET_COMMANDS:
                        data = gson.fromJson(reqData, GetCommandsRequest.class);
                }
                response = execute();
                jsonStr = gson.toJson(response);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                writeString(jsonStr, respBody);
                respBody.close();
                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                OutputStream respBody = exchange.getResponseBody();
                respBody.close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            OutputStream respBody = exchange.getResponseBody();
            respBody.close();
            e.printStackTrace();
        }
    }
    /*
        The readString method shows how to read a String from an InputStream.
    */
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
    /*
        The writeString method shows how to write a String to an OutputStream.
    */
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}


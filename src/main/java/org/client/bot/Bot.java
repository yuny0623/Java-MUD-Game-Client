package org.client.bot;

import org.client.Main;
import org.client.client.InputThread;
import org.client.client.MainClient;
import org.client.utils.JsonUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Thread{

    String nickname;
    Socket socket;
    PrintWriter out;
    JsonUtil jsonUtil;
    String command;
    String json;



    public Bot(Socket socket, String nickname){
        System.out.println("Create Bot.");
        this.socket = socket;
        this.nickname = nickname;
    }

    @Override
    public void run(){
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            jsonUtil = JsonUtil.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(!this.isInterrupted()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                out.close();
                break;
            }
            command = randomCommand();
            if(command.isEmpty() || command.isBlank()){
                System.out.println("[Bot] Invalid Command.");
                continue;
            }
            System.out.println("[Bot] " + command);
            json = jsonUtil.generateJson(command);
            out.println(json);
        }
    }

    public String randomCommand(){
        String command = "";
        int x;
        int y;
        int randomCommand = (int) (Math.random() * (4 - 0 + 1)) + 0;
        switch(randomCommand){
            case 0:
                x = (int) (Math.random() * (4 - (-4) + 1)) + (-4);
                y = (int) (Math.random() * (4 - (-4) + 1)) + (-4);
                command = "move " + x + " " + y;
                break;
            case 1:
                command = "attack";
                break;
            case 2:
                command = "monsters";
                break;
            case 3:
                command = "users";
                break;
            case 4:
                String to = randomReceiver();
                if(to.isBlank() || to.isEmpty()){
                    break;
                }
                String message = randomMessage();
                command = "chat " + to + " " + message;
                break;
        }
        return command;
    }

    public String randomReceiver(){
        int limit = MainClient.userList.size() - 1;
        int i = (int) (Math.random() * (limit - (-limit) + 1)) + (-limit);
        return MainClient.userList.get(i);
    }


    public String randomMessage(){
        int limit = MainClient.messageList.size() - 1;
        int i = (int) (Math.random() * (limit - (-limit) + 1)) + (-limit);
        return MainClient.messageList.get(i);
    }
}

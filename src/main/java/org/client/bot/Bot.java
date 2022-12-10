package org.client.bot;

import org.client.client.Client;
import org.client.utils.GameUtil;
import org.client.utils.JsonUtil;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Bot extends Thread{

    String nickname;
    Socket socket;
    PrintWriter out;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (!this.isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    out.close();
                    throw e;
                }
                command = randomCommand();
                if (command.isEmpty() || command.isBlank()) {
                    System.out.println("[Bot] Invalid Command.");
                    continue;
                }
                System.out.println("[Bot] " + command);
                json = JsonUtil.generateJson(command);
                out.println(json);
            }
        }catch(InterruptedException e){
            System.out.printf("[Bot] bot stop - %s\n", e.getMessage());
        }
    }

    public String randomCommand(){
        String command = "";
        int x;
        int y;
        int randomCommand = GameUtil.generateRandomNumber(0, 6);
        switch(randomCommand){
            case 0:
                x = GameUtil.generateRandomNumber(-3, 3);
                y = GameUtil.generateRandomNumber(-3, 3);
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
                String to = randomUser();
                if(to.isBlank() || to.isEmpty()){
                    break;
                }
                String message = randomMessage();
                command = "chat " + to + " " + message;
                break;
            case 5:
                command = "potion hp";
                break;
            case 6:
                command = "potion str";
                break;
        }
        return command;
    }

    public String randomUser(){
        int limit = Client.userList.size() - 1;
        if(limit <= 0)
            return "";
        int i = GameUtil.generateRandomNumber(0, limit);
        return Client.userList.get(i);
    }


    public String randomMessage(){
        int limit = Client.messageList.size() - 1;
        int i = GameUtil.generateRandomNumber(0, limit);
        return Client.messageList.get(i);
    }
}

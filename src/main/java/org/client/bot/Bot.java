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
                String to = randomUser();
                if(to.isBlank() || to.isEmpty()){
                    break;
                }
                String message = randomMessage();
                command = "chat " + to + " " + message;
                break;
        }
        return command;
    }

    public String randomUser(){
        int limit = MainClient.userList.size() - 1;
        if(limit <= 0)
            return "";
        int i = (int) (Math.random() * (limit - 0 + 1)) + (0);
        return MainClient.userList.get(i);
    }


    public String randomMessage(){
        int limit = MainClient.messageList.size() - 1;
        int i = (int) (Math.random() * (limit - 0 + 1)) + (0);
        return MainClient.messageList.get(i);
    }
}

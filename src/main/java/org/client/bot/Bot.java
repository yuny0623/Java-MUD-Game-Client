package org.client.bot;

import org.client.client.InputThread;
import org.client.utils.JsonUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
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
            System.out.println("[Bot] " + command);
            json = jsonUtil.generateJson(command);
            out.println(json);
        }
    }

    public String randomCommand(){
        String command = null;
        int x;
        int y;
        int randomCommand = (int) (Math.random() * (3 - 0 + 1)) + 0;
        switch(randomCommand){
            case 0:
                x = (int) (Math.random() * (3 - (-3) + 1)) + (-3);
                y = (int) (Math.random() * (3 - (-3) + 1)) + (-3);
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
        }
        return command;
    }
}

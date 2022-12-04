package org.client.client;

import org.client.utils.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DisplayThread extends Thread {

    Socket socket;
    String strIn;
    BufferedReader in;
    JsonUtil jsonUtil;

    public DisplayThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            jsonUtil = JsonUtil.getInstance();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            try {
                strIn = in.readLine();
                if (strIn.isEmpty() || strIn.isBlank()) {
                    continue;
                }
                System.out.println("[Notice] " + jsonUtil.parseJson(strIn));
            }catch(Exception e){
                if(e.getMessage().equals("Connection reset")) {
                    System.out.println("[Error] Socket " + e.getMessage());
                    System.out.println("Server is not running...");
                }else{
                    System.out.println(e.getMessage());
                }
                System.out.println("Exit Client.");
                break;
            }
        }
    }
}

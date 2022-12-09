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
    String parsedJson;

    public DisplayThread(Socket socket){
        this.socket = socket;
    }

    public boolean isValidInput(String input){
        if(input == null || input.isEmpty() || input.isBlank()){
            return false;
        }
        return true;
    }

    public boolean isValidParsedJson(String parsedJson){
        if(parsedJson == null || parsedJson.isEmpty() || parsedJson.isBlank()){
            System.out.println("[Error] parsing json error.");
            return false;
        }
        return true;
    }

    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        while(true){
            try {
                strIn = in.readLine();

                // 입력 유효성 검사
                if(!isValidInput(strIn)){
                    continue;
                }

                parsedJson = JsonUtil.parseJson(strIn);

                if(!isValidParsedJson(parsedJson)){
                    continue;
                }

                System.out.println("[Notice] " + parsedJson);
            }catch(Exception e){
                if(e.getMessage().equals("Connection reset")) {
                    System.out.println("[Error] Socket - " + e.getMessage());
                    System.out.println("Server is not running...");
                }else{
                    System.out.println(e.getMessage());
                }
                System.out.println("Exit Client.");
                if(Client.bot != null) {
                    System.out.println("Exit Bot.");
                    Client.bot.interrupt();
                }
                try {
                    socket.close();
                    in.close();
                }catch(IOException err){
                    err.printStackTrace();
                    break;
                }
                break;
            }
        }
    }
}

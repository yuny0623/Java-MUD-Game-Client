package org.client.client;

import org.client.utils.JsonUtil;

import java.io.*;
import java.net.Socket;

public class InputThread extends Thread{
    Socket socket;
    PrintWriter out;
    BufferedReader br;
    String clientInput;
    String json;
    JsonUtil jsonUtil;
    String nickname;

    public InputThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            br = new BufferedReader(new InputStreamReader(System.in));
            jsonUtil = JsonUtil.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // nickname 전송
        try {
            System.out.println("Type nickname:");
            nickname = br.readLine();
            while(true){
                if(nickname.isBlank() || nickname.isEmpty()){
                    System.out.println("Empty nickname.");
                    System.out.println("Type nickname:");
                    nickname = br.readLine();
                }else{
                    break;
                }
            }
            System.out.println("login as " + nickname);
            out.println(jsonUtil.generateJson("nickname " + nickname));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("login succeed.");

        while(true){
            try {
                System.out.print("Input Command:");
                clientInput = br.readLine();
                json = jsonUtil.generateJson(clientInput);
                if(json.isEmpty() || json.isBlank()){
                    System.out.println("Invalid Command.");
                    continue;
                }
                out.println(json);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

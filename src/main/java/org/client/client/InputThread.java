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

    public InputThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        jsonUtil = JsonUtil.getInstance();

        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

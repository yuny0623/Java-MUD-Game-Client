package org.client.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DisplayThread extends Thread {

    Socket socket;
    String strIn;
    BufferedReader in;

    public DisplayThread(Socket socket){
        this.socket = socket;
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
                if (strIn.isBlank()) {
                    continue;
                }
                System.out.println(strIn);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

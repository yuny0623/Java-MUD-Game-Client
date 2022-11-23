package org.client.Client;

import org.client.Utils.ClientConfig;
import org.client.Utils.JsonGenerator;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class MainClient{
    String ip;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    BufferedReader br;
    String strIn;
    String json;

    String clientInput;

    public MainClient(){
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            InetAddress ia = InetAddress.getLocalHost();
            String ipStr = ia.toString();
            ip = ipStr.substring(ipStr.indexOf("/") + 1);
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    public void start(){
        System.out.println("Starting client...");
        initNet(ip, ClientConfig.TCP_CONNECTION_DEFAULT_PORT);
    }

    public void initNet(String ip, int port){
        try{
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        }catch(UnknownHostException e){
            System.out.println("Different IP Address");
        }catch(IOException e){
            System.out.println("Connection failed");
        }
        run();
    }

    public void run(){
        while(true){
            try {
                strIn = in.readLine();
                if(strIn != null){
                    System.out.println(strIn);
                }
                clientInput = br.readLine();
                if(clientInput != null){
                    json = JsonGenerator.generateJson(clientInput);
                    if(json != null) {
                        System.out.println(json);
                        out.println(json);
                    }else{
                        System.out.println("Invalid Input!");
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}

package org.client.client;

import org.client.utils.ClientConfig;
import org.client.utils.JsonUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient{
    String ip;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    BufferedReader br;
    String strIn;
    String json;
    String clientInput;
    String gameLogo = "\n" +
            ".___  ___.  __    __   _______       _______      ___      .___  ___.  _______ \n" +
            "|   \\/   | |  |  |  | |       \\     /  _____|    /   \\     |   \\/   | |   ____|\n" +
            "|  \\  /  | |  |  |  | |  .--.  |   |  |  __     /  ^  \\    |  \\  /  | |  |__   \n" +
            "|  |\\/|  | |  |  |  | |  |  |  |   |  | |_ |   /  /_\\  \\   |  |\\/|  | |   __|  \n" +
            "|  |  |  | |  `--'  | |  '--'  |   |  |__| |  /  _____  \\  |  |  |  | |  |____ \n" +
            "|__|  |__|  \\______/  |_______/     \\______| /__/     \\__\\ |__|  |__| |_______|\n" +
            "                                                                               \n";

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
        System.out.println(gameLogo);
        System.out.println("***************Command list****************");
        System.out.println("*              move x y                   *");
        System.out.println("*              attack                     *");
        System.out.println("*              monsters                   *");
        System.out.println("*              users                      *");
        System.out.println("*              chat <username> <content>  *");
        System.out.println("*              bot                        *");
        System.out.println("*******************************************");
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
                if(strIn.isBlank()){
                    continue;
                }
                System.out.println("received from server: " + strIn);
                System.out.print("Input Command:");
                clientInput = br.readLine();
                String json = JsonUtil.generateJson(clientInput);
                if(json.isEmpty() || json.isBlank()){
                    System.out.println("Invalid Command!");
                    continue;
                }
                out.println(json);
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

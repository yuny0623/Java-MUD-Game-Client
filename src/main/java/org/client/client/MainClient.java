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
        System.out.println("Starting Client...");
        System.out.println(gameLogo);
        System.out.println("***********Command list***********");
        System.out.println("move x y");
        System.out.println("attack");
        System.out.println("monsters");
        System.out.println("users");
        System.out.println("chat <username> <content>");
        System.out.println("bot");
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
                // 아래 조건문 없으면 client와 server 상에서 한박자 어긋남.
                if(strIn.isBlank()){
                    continue;
                }
                clientInput = br.readLine();
                out.println(clientInput);
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

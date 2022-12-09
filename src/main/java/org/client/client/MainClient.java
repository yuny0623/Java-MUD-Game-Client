package org.client.client;

import org.client.bot.Bot;
import org.client.config.ClientConfig;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainClient{
    String ip;
    int port;
    Socket socket;
    public static Bot bot;

    public static List<String> userList = new ArrayList<>();
    public static List<String> messageList = new ArrayList<>();

    public MainClient(){
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String ipStr = ia.toString();
            ip = ipStr.substring(ipStr.indexOf("/") + 1);
            port = ClientConfig.TCP_CONNECTION_DEFAULT_PORT;
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    public void start(){
        printLogs();
        initNet(ip, port);
        initThreads(socket);

        messageList.add("hi");
        messageList.add("bye");
        messageList.add("good");
        messageList.add("bad");
        messageList.add("gg");
    }

    public void printLogs(){
        System.out.println(ClientConfig.GAME_LOGO);
        System.out.println(ClientConfig.COMMAND_LIST);
    }

    public void initThreads(Socket socket){
        DisplayThread displayThread = new DisplayThread(socket);
        displayThread.start();
        InputThread inputThread = new InputThread(socket);
        inputThread.start();
    }

    public void initNet(String ip, int port){
        while(true) {
            System.out.println("Connecting to MainServer.");
            try {
                socket = new Socket(ip, port);
            } catch (UnknownHostException e) {
                System.out.println("Different IP Address");
            } catch (IOException e) {
                System.out.println("Connection failed.");
            }
            if(socket != null){
                break;
            }
        }
    }
}

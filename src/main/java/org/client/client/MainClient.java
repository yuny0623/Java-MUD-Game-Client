package org.client.client;

import org.client.utils.ClientConfig;
import org.client.utils.JsonUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient{
    String ip;
    int port;
    Socket socket;

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
    }

    public void printLogs(){
        System.out.println(gameLogo);
        System.out.println("***************Command list****************");
        System.out.println("*              move x y                   *");
        System.out.println("*              attack                     *");
        System.out.println("*              monsters                   *");
        System.out.println("*              users                      *");
        System.out.println("*              chat <username> <content>  *");
        System.out.println("*              bot                        *");
        System.out.println("*******************************************");
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

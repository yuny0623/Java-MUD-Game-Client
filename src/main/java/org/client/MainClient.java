package org.client;

import org.client.Utils.ClientConfig;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainClient{
    String ip;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String str;
    String nickname;
    Scanner sc;

    public MainClient(){
        try {
            sc = new Scanner(System.in);

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
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
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
                String clientInput = sc.nextLine();
                if(clientInput == null){

                }
                str = in.readLine();     // 서버로부터 정보 전달받음
                if (str == null) {
                    continue;
                }
                /*
                    parsing str logic
                 */
                System.out.println(str); // 사용자에게 정보 보여줌.
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}

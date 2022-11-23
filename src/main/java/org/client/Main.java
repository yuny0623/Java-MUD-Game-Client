package org.client;

import java.net.InetAddress;

public class Main {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String ipStr = ia.toString();
            String ip = ipStr.substring(ipStr.indexOf("/") + 1);
            new MainClient(ip, ClientConfig.TCP_CONNECTION_DEFAULT_PORT);
        }catch(Exception err){
            err.printStackTrace();
        }
    }
}
package org.client;

import java.net.InetAddress;

public class MainClient {
    String ip;
    public MainClient(){
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String ipStr = ia.toString();
            ip = ipStr.substring(ipStr.indexOf("/") + 1);
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    public void start(){
        
    }
}

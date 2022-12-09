package org.client.config;

public final class ClientConfig {

    private static ClientConfig instance;
    private ClientConfig(){

    }
    public static ClientConfig getInstance(){
        if(instance == null){
            instance = new ClientConfig();
        }
        return instance;
    }

    public static final int TCP_CONNECTION_DEFAULT_PORT = 8080;
}

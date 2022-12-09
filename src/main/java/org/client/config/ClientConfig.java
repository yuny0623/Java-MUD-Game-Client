package org.client.config;

import java.util.ArrayList;
import java.util.List;

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
    public static final String GAME_LOGO = "\n" +
            ".___  ___.  __    __   _______       _______      ___      .___  ___.  _______ \n" +
            "|   \\/   | |  |  |  | |       \\     /  _____|    /   \\     |   \\/   | |   ____|\n" +
            "|  \\  /  | |  |  |  | |  .--.  |   |  |  __     /  ^  \\    |  \\  /  | |  |__   \n" +
            "|  |\\/|  | |  |  |  | |  |  |  |   |  | |_ |   /  /_\\  \\   |  |\\/|  | |   __|  \n" +
            "|  |  |  | |  `--'  | |  '--'  |   |  |__| |  /  _____  \\  |  |  |  | |  |____ \n" +
            "|__|  |__|  \\______/  |_______/     \\______| /__/     \\__\\ |__|  |__| |_______|\n" +
            "                                                                               \n";

    public static final String COMMAND_LIST =
            "***************COMMAND LIST****************\n" +
            "*              move x y                   *\n" +
            "*              attack                     *\n" +
            "*              monsters                   *\n" +
            "*              users                      *\n" +
            "*              chat <username> <content>  *\n" +
            "*              bot                        *\n" +
            "*              exit bot                   *\n" +
            "*              potion hp                  *\n" +
            "*              potion str                 *\n" +
            "*******************************************\n";
}

package org.client.client;

import org.client.bot.Bot;
import org.client.utils.JsonUtil;
import java.io.*;
import java.net.Socket;

public class InputThread extends Thread{
    Socket socket;
    PrintWriter out;
    BufferedReader br;
    String clientInput;
    String json;
    String nickname;

    Bot bot;

    public InputThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 로그인 진행
        try {
            System.out.println("Type nickname:");
            nickname = br.readLine();
            while(true){
                if(nickname.isBlank() || nickname.isEmpty()){
                    System.out.println("Empty nickname.");
                    System.out.println("Type nickname again:");
                    nickname = br.readLine();
                }else{
                    break;
                }
            }
            System.out.println("login as " + nickname);
            out.println(JsonUtil.generateJson("nickname " + nickname));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("login success.");

        // 게임 진행
        while(true){
            try {
                clientInput = br.readLine();

                if(clientInput.equals("bot")){
                    System.out.println("Start Bot mode.");
                    bot = new Bot(socket, nickname);
                    bot.start();
                    MainClient.bot = bot;
                    json = JsonUtil.generateJson("bot");
                    out.println(json);
                    clientInput = null;
                    continue;
                }
                if(clientInput.equals("exit bot")){
                    bot.interrupt();
                    MainClient.bot = null;
                    json = JsonUtil.generateJson("exit bot");
                    out.println(json);
                    System.out.println("Bot mode stop.");
                    clientInput = null;
                    continue;
                }
                json = JsonUtil.generateJson(clientInput);
                if(json.isEmpty() || json.isBlank()){
                    System.out.println("Invalid Command.");
                    continue;
                }
                out.println(json);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    if(bot.isAlive()){
                        bot.interrupt();
                    }
                    socket.close();
                    out.close();
                    br.close();
                }catch(IOException err){
                    err.printStackTrace();
                    System.exit(1);
                }
                break;
            }
        }
    }
}

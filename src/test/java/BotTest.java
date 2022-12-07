import org.client.bot.Bot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class BotTest {
    
    @Test
    @DisplayName("봇 생성 테스트")
    public void create_bot_test(){
        // given
        new Thread(() -> {
            while (true) {
                try {
                    ServerSocket serverSocket = new ServerSocket(8080);
                    serverSocket.setReuseAddress(true);
                    Socket socket = serverSocket.accept();
                    while(true){
                        /*
                            대기
                         */
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        List<Bot> botList = new ArrayList<>();
        Socket socket = null;
        String ip ="";
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String ipStr = ia.toString();
            ip = ipStr.substring(ipStr.indexOf("/") + 1);
            socket = new Socket(ip, 8080);
        }catch(IOException e){
            e.printStackTrace();
        }
        for(int i = 0; i < 10; i++){
            Bot bot = new Bot(socket, String.valueOf(i));
            botList.add(bot);
            bot.start();
        }

        // when
        for(int i = 0; i < botList.size(); i++){
            Bot bot = botList.get(i);
            if(i%2 == 0){
                bot.interrupt();
            }
        }

        try {
            Thread.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        int liveBot = 0;
        for(int i = 0; i < botList.size(); i++){
            if(botList.get(i).isAlive()){
                liveBot ++;
            }
        }

        // then
        Assert.assertEquals(5, liveBot);
    }
}

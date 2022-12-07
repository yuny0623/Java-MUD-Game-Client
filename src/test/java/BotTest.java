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

    @Test
    @DisplayName("Random user 뽑아내기 테스트")
    public void get_random_user_test(){
        // given
        List<String> userList = new ArrayList<>();
        userList.add("tony");
        userList.add("bruce");
        userList.add("maven");
        userList.add("gradle");
        userList.add("java");

        // when
        int limit = userList.size() - 1;
        int i = (int) (Math.random() * (limit - 0 + 1)) + 0;
        String user = userList.get(i);

        // then
        Assert.assertTrue(userList.contains(user));
    }

    @Test
    @DisplayName("(int) (Math.random() * (최댓값-최소값 + 1)) + 최소값/ 공식 테스트")
    public void random_number_range_test(){
        // given
        int limit = 9;
        int number = 0;
        boolean[] check = new boolean[10];

        // when
        for(int i = 0; i < 100000; i++){
            number = (int) (Math.random() * (limit - 0 + 1)) + 0;
            check[number] = true;
        }
        int k = 0;
        while (k < 10){
            if(!check[k]){
                break;
            }
            k++;
        }

        // then
        Assert.assertEquals(10, k);
    }
 }

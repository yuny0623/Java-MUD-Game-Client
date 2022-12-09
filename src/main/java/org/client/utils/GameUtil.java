package org.client.utils;

import java.util.UUID;

public final class GameUtil {

    public GameUtil(){

    }

    public static synchronized int generateRandomNumber(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}

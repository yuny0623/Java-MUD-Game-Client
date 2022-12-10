package org.client.utils;

import org.client.client.Client;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class JsonUtil {
    private JsonUtil(){

    }

    public static synchronized String parseJson(String json){
        String result = "";
        if(json.isEmpty() || json.isBlank()){
            System.out.println("Invalid receive json.");
            return result;
        }
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = (JSONObject) parser.parse(json);
        }catch(ParseException e){
            e.printStackTrace();
            return result;
        }
        String monsterInfo = (String) jsonObject.get("MonsterInfo");
        if(monsterInfo!= null){
            return monsterInfo;
        }
        String userInfo = (String) jsonObject.get("UserInfo");
        if(userInfo != null){
            String[] users = userInfo.split("\n");
            for(String user: users){
                String[] vals = user.split(" ");
                String userName = vals[0];
                Client.userList.add(userName);
            }
            return userInfo;
        }
        result = (String) jsonObject.get("Notice");
        return result;
    }

    public static synchronized String generateJson(String action){
        if(action.isEmpty() || action ==  null){
            return "";
        }
        JSONObject jsonObject = new JSONObject();
        String[] inputs = action.split(" ");
        String command = inputs[0];
        String result = "";
        switch(command){
            case "nickname":
                String nickname = inputs[1];
                jsonObject.put("command", "nickname");
                jsonObject.put("nickname", nickname);
                result = jsonObject.toJSONString();
                break;
            case "move":
                if(inputs.length != 3){
                    break;
                }
                int xVal = Integer.parseInt(inputs[1]);
                int yVal = Integer.parseInt(inputs[2]);
                if(xVal < -3){
                    xVal = -3;
                }else if(xVal > 3){
                    xVal = 3;
                }
                if(yVal < -3){
                    yVal = -3;
                }else if(yVal > 3){
                    yVal = 3;
                }
                jsonObject.put("command", "move");
                jsonObject.put("x", String.valueOf(xVal));
                jsonObject.put("y", String.valueOf(yVal));
                result = jsonObject.toJSONString();
                break;
            case "attack":
                jsonObject.put("command", "attack");
                result = jsonObject.toJSONString();
                break;
            case "monsters":
                jsonObject.put("command", "monsters");
                result = jsonObject.toJSONString();
                break;
            case "users":
                jsonObject.put("command", "users");
                result = jsonObject.toJSONString();
                break;
            case "chat":
                if(inputs.length != 3){
                    break;
                }
                String opponent = inputs[1];
                String content = inputs[2];
                jsonObject.put("command", "chat");
                jsonObject.put("opponent", opponent);
                jsonObject.put("content", content);
                result = jsonObject.toJSONString();
                break;
            case "bot":
                jsonObject.put("command","bot");
                result = jsonObject.toJSONString();
                break;
            case "exit":
                jsonObject.put("command", "exit bot");
                result = jsonObject.toJSONString();
                break;
            case "potion":
                String item = inputs[1];
                jsonObject.put("command", "potion");
                jsonObject.put("item", item);
                result = jsonObject.toJSONString();
        }
        return result;
    }
}

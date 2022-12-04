package org.client.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtil {
    private static JsonUtil instance;

    private JsonUtil(){

    }
    public static JsonUtil getInstance(){
        if(instance == null){
            instance = new JsonUtil();
        }
        return instance;
    }

    public String parseJson(String json){
        String result = "";
        if(json.isEmpty() || json.isBlank()){
            System.out.println("Invalid received json");
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
        result = (String) jsonObject.get("Notice");
        return result;
    }

    public String generateJson(String action){
        if(action.isEmpty() || action ==  null){
            return "";
        }
        JSONObject jsonObject = new JSONObject();
        String[] inputs = action.split(" ");
        String command = inputs[0];
        String result = "";
        switch(command){
            case "move":
                if(inputs.length != 3){
                    break;
                }
                String x_val = inputs[1];
                String y_val = inputs[2];
                jsonObject.put("command", "move");
                jsonObject.put("x", x_val);
                jsonObject.put("y", y_val);
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
            case "nickname":
                String nickname = inputs[1];
                jsonObject.put("command", "nickname");
                jsonObject.put("nickname", nickname);
                result = jsonObject.toJSONString();
                break;
            case "exit":
                return "exit";
        }
        return result;
    }
}

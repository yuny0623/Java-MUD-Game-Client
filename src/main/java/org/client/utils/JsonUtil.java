package org.client.utils;

import org.json.simple.JSONObject;

public class JsonUtil {
    public static String generateJson(String action){
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
        }
        return result;
    }
}

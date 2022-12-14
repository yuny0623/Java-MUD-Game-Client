import org.client.utils.JsonUtil;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class JsonUtilTest {
    @Test
    @DisplayName("json 파싱 기능 테스트")
    public void json_parser_test(){
        // given
        JSONObject inner = new JSONObject();
        inner.put("name","razlo");
        inner.put("age", "99");
        inner.put("address","seoul");
        JSONObject outer = new JSONObject();
        outer.put("info", inner);

        // when
        String json = outer.toJSONString();
        String resultJson = "{\"info\":{\"address\":\"seoul\",\"name\":\"razlo\",\"age\":\"99\"}}";

        // then
        Assert.assertEquals(json, resultJson);
    }

    @Test
    @DisplayName("json 생성 기능 테스트")
    public void json_generation_test(){
        // given
        String json = JsonUtil.generateJson("move 1 2");

        // when
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "move");
        jsonObject.put("x", "1");
        jsonObject.put("y", "2");
        String generatedJson = jsonObject.toJSONString();

        // then
        Assert.assertEquals(generatedJson, json);
    }
}

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    @Test
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
}

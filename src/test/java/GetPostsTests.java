import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class GetPostsTests extends TestBase {

    @Test
    public void shouldReturnAPostWithTitlePalmTree() {

        given().request().with()
                .contentType("application/json")
                .when().get("http://127.0.0.1:8080/posts/1")
                .then().assertThat().body("title", equalTo("Palm tree"));

    }


    @Test
    public void shouldReturnAPost() {

        String expectedResponseString="{" +
                "\"title\":\"Palm tree\","+
                "\"body\":\"Palm trees are a botanical family of perennial lianas, shrubs, and trees. They are in the family Arecaceae. They grow in hot climates\"," +
                "\"email\":\"tom@tv.com\"" +
                "}";

        Response response = given().request().with()
                .contentType("application/json")
                .when().get("http://127.0.0.1:8080/posts/1");

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.asString(), expectedResponseString);

    }
}

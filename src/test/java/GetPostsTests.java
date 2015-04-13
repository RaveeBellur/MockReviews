import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class GetPostsTests extends TestBase {

    @Test
    public void shouldAssertTitleOfAPost() {

        given()
                .request()
        .when()
                .get("http://localhost:8080/posts/1")
        .then()
                .assertThat().body("title", equalTo("Palm Tree"));

    }

    @Test
    public void shouldReturnPostsByAuthor() {

        String responseString =
                given()
                        .request().with()
                            .queryParam("author", "Tom")
                .when()
                        .get("http://localhost:8080/posts").asString();

        assertEquals(from(responseString).getList("posts.findAll { p -> p.author == 'Tom'}").size(), 2);

    }


}

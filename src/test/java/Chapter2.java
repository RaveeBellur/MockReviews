import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class Chapter2 extends TestBase {

    @Test
    public void shouldCreateAndGetReview() {

        String title = "Palm Tree";
        String body = "Palm trees are a botanical family of perennial lianas, shrubs, and trees. " +
                      "They are in the family Arecaceae. They grow in hot climates";
        String author = "Tom";
        String email = "tom@tv.com";
        String requestBody = String.format("\"title\":%s," +
                "\"body\":%s," +
                "\"author\":%s," +
                "\"email\":%s", title, body, author, email);

        Response response =
                given()
                    .request().with()
                        .queryParam("format", "json")
                        .body(requestBody)
                .when()
                    .post("http://localhost:8080/reviews");

        String reviewId = from(response.asString()).get("id").toString();


        given()
                .request().with()
                    .queryParam("format", "json")
        .when()
                .get(String.format("http://localhost:8080/reviews/%s", reviewId))
        .then()
                .assertThat().body("title",     equalTo(title))
                .assertThat().body("body",      equalTo(body))
                .assertThat().body("author",    equalTo(author))
                .assertThat().body("email",     equalTo(email));


    }

}

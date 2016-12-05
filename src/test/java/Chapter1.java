import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class Chapter1 extends TestBase {

    @Test
    public void shouldCreateAReview() {
        given()
            .request().with()
                .queryParam("format", "json")
                .body("\"title\":\"Xiaomi Redmi Note 3\"," +
                        "\"body\":\"Good hardware. The screen is huge and reproduces good colors. Watching Videos is fun on a 5.5 inch screen.\"," +
                        "\"author\":\"Tom\"," +
                        "\"email\":\"tom@tv.com\"")
        .when()
            .post("http://localhost:8080/reviews")
        .then()
            .assertThat()
            .statusCode(201)
            .body("id", notNullValue());
    }
}

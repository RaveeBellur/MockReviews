import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Chapter2 extends TestBase {

    @Test
    public void shouldAssertTitleOfReview() {
        given()
            .request().with()
                .queryParam("format", "json")
        .when()
            .get("http://localhost:8080/reviews/1")
        .then()
            .assertThat().body("title", equalTo("Xiaomi Redmi Note 3"));
    }

    @Test
    public void shouldCreateAndGetReview() {
        String title = "Xiaomi Redmi Note 3";
        String body = "Good hardware. The screen is huge and reproduces good colors. Watching Videos is fun on a 5.5 inch screen.";
        String author = "Tom";
        String email = "tom@tv.com";
        String requestBody = String.format("\"title\":%s," +
                "\"body\":%s," +
                "\"author\":%s," +
                "\"email\":%s", title, body, author, email);

        String reviewId =
                given()
                    .request().with()
                        .queryParam("format", "json")
                        .body(requestBody)
                .when()
                    .post("http://localhost:8080/reviews")
                .then()
                    .extract().jsonPath().get("id").toString();

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

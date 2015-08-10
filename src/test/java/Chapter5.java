import entities.Review;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Chapter5 extends TestBase {

    final String body = "Palm trees are a botanical family of perennial lianas, shrubs, and trees. " +
            "They are in the family Arecaceae. They grow in hot climates";
    final String author = "Tom";
    final String email = "tom@tv.com";

    @Test
    public void shouldValidateTile() {

        final String title = "";

        Review review = new Review(title, body, author, email);

        given()
                .request().with()
                    .queryParam("format", "json")
                    .body(review)
                .when()
                    .post("http://localhost:8080/reviews")
                .then()
                    .assertThat().body("error", equalTo("_ERROR_TITLE_EMPTY"));

    }
}

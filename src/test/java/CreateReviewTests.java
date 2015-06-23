import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateReviewTests extends TestBase {

    @Test
    public void shouldCreateNewReview() {

        given()
                .request().with()
                .body("\"title\":\"Palm Tree\"," +
                        "\"body\":\"Palm trees are a botanical family of perennial lianas, shrubs, and trees. They are in the family Arecaceae. They grow in hot climates\"," +
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

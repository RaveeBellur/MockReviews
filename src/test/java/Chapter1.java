import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class Chapter1 extends TestBase {

    @Test
    public void shouldCreateNewReview() {

        given()
                .request().with()
                    .queryParam("format", "json")
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

    @Test
    public void shouldAssertTitleOfReview() {

        given()
                .request().with()
                    .queryParam("format", "json")

        .when()
                .get("http://localhost:8080/reviews/1")
        .then()
                .assertThat().body("title", equalTo("Palm Tree"));

    }

}

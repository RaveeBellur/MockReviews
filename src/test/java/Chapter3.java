import entities.Review;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Chapter3 extends TestBase {

    @Test
    public void shouldCreateAndGetReview() {
        Review review = new Review(
                "Xiaomi Redmi Note 3",
                "Good hardware. The screen is huge and reproduces good colors. Watching Videos is fun on a 5.5 inch screen.",
                "Tom",
                "tom@tv.com");

        String reviewId =
                given()
                    .request().with()
                        .queryParam("format", "json")
                        .contentType("application/json")
                        .body(review)
                .when()
                    .post("http://localhost:8080/reviews")
                .then()
                    .extract().response().as(Review.class).getId();

        Review actualReview =
                given()
                    .request().with()
                        .queryParam("format", "json")
                        .contentType("application/json")
                 .when()
                    .get(String.format("http://localhost:8080/reviews/%s", reviewId))
                .then()
                    .extract().response().as(Review.class);

        assertEquals(actualReview.getTitle(), review.getTitle());
        assertEquals(actualReview.getBody(), review.getBody());
        assertEquals(actualReview.getAuthor(), review.getAuthor());
        assertEquals(actualReview.getEmail(), review.getEmail());
    }
}

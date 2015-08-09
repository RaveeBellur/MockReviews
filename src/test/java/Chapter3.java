import com.jayway.restassured.response.Response;
import entities.Review;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Chapter3 extends TestBase {

    @Test
    public void shouldCreateAndGetReview() {
        Review review = new Review(
                "Palm Tree",
                "Palm trees are a botanical family of perennial lianas, shrubs, and trees. " +
                        "They are in the family Arecaceae. They grow in hot climates",
                "Tom",
                "tom@tv.com");

        Response createReviewResponse =
                given()
                    .request().with()
                        .queryParam("format", "json")
                        .body(review)
                .when()
                    .post("http://localhost:8080/reviews")
                .then()
                        .extract().response();

        String reviewId = createReviewResponse.as(Review.class).getId();

        Response retrieveReviewResponse =
                given()
                        .request().with()
                        .queryParam("format", "json")
                .when()
                    .get(String.format("http://localhost:8080/reviews/%s", reviewId))
                .then()
                    .extract().response();

        Review actualReview = retrieveReviewResponse.as(Review.class);

        assertEquals(actualReview.getTitle(), review.getTitle());
        assertEquals(actualReview.getBody(), review.getBody());
        assertEquals(actualReview.getAuthor(), review.getAuthor());
        assertEquals(actualReview.getEmail(), review.getEmail());
    }

}

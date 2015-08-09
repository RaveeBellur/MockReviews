import com.jayway.restassured.internal.mapper.ObjectMapperType;
import entities.Review;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Chapter4 extends TestBase {


    @Test
    public void shouldCreateAndGetReviewWithXML() {
        Review review = new Review(
                "Palm Tree",
                "Palm trees are a botanical family of perennial lianas, shrubs, and trees. " +
                        "They are in the family Arecaceae. They grow in hot climates",
                "Tom",
                "tom@tv.com");

        String reviewId =
                given()
                    .request().with()
                        .queryParam("format", "xml")
                        .body(review, ObjectMapperType.JAXB)
                .when()
                    .post("http://localhost:8080/reviews")
                .then()
                    .extract().response().as(Review.class, ObjectMapperType.JAXB).getId();

        Review actualReview =
                given()
                    .request().with()
                        .queryParam("format", "xml")
                .when()
                    .get(String.format("http://localhost:8080/reviews/%s", reviewId))
                .then()
                    .extract().as(Review.class, ObjectMapperType.JAXB);

        assertEquals(actualReview.getTitle(), review.getTitle());
        assertEquals(actualReview.getBody(), review.getBody());
        assertEquals(actualReview.getAuthor(), review.getAuthor());
        assertEquals(actualReview.getEmail(), review.getEmail());
    }

}

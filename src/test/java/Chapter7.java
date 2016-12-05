import entities.Review;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class Chapter7 extends TestBase {

    final String body = "Good hardware. The screen is huge and reproduces good colors. Watching Videos is fun on a 5.5 inch screen.";
    final String author = "Tom";
    final String email = "tom@tv.com";

    @DataProvider
    public static Object[][] invalidTitleTestData() {
        return new Object[][]{
                {"", "_ERROR_TITLE_EMPTY"},
                {"This is really long title", "_ERROR_TITLE_MAX_LENGTH"},
        };
    }


    @Test(dataProvider = "invalidTitleTestData")
    public void shouldValidateTile(String title, String expectedError) {

        Review review = new Review(title, body, author, email);

        given()
            .request().with()
                .queryParam("format", "json")
                .body(review)
            .when()
                .post("http://localhost:8080/reviews")
            .then()
                .assertThat().body("error", equalTo(expectedError));



    }
}

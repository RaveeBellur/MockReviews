import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class GetReviewsTests extends TestBase {



    @Test
    public void shouldReturnReviewsByAuthor() {

        String responseString =
                given()
                        .request().with()
                            .queryParam("format", "json")
                            .queryParam("author", "Tom")
                .when()
                        .get("http://localhost:8080/reviews").asString();

        assertEquals(from(responseString).getList("findAll { r -> r.author == 'Tom'}").size(), 2);

    }


}

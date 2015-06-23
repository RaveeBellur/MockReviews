import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.Parameter;
import java.io.File;

import static com.jayway.restassured.path.json.JsonPath.from;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class Expectations {

    private ClientAndServer mockServer;

    public Expectations(ClientAndServer mockServer) {
        this.mockServer = mockServer;
        setExpectations();
    }

    private void setExpectations() {
        expectationForShouldCreateNewReview();
        expectationForShouldAssertTitleOfReview();
        expectationForShouldReturnReviewsByAuthor();
    }

    private void expectationForShouldCreateNewReview() {

        String responseString = "{\"id\":\"1\"}";

        mockServer
                .when(
                        request()
                                .withPath("/reviews")
                                .withMethod("POST"))
                .respond(
                        response()
                                .withStatusCode(201)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody(responseString));
    }

    private void expectationForShouldAssertTitleOfReview() {
        Object o = from(new File("target/classes/data.json"))
                .get("reviews.findAll { r -> r.title == 'Palm Tree'}[0]");
        String responseString = RequestHelper.getJsonString(o);

        mockServer
                .when(
                        request()
                                .withPath("/reviews/1")
                                .withMethod("GET"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody(responseString));
    }

    private void expectationForShouldReturnReviewsByAuthor() {
        Object o = from(new File("target/classes/data.json"))
                .getList("reviews.findAll { r -> r.author == 'Tom'}");
        String responseString = RequestHelper.getJsonString(o);


        mockServer
                .when(
                        request()
                                .withQueryStringParameters(
                                        new Parameter("author", "Tom"))
                                .withPath("/reviews")
                                .withMethod("GET"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody(responseString));
    }

}

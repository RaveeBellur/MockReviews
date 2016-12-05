import entities.Review;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.Parameter;
import java.io.File;

import static com.jayway.restassured.path.json.JsonPath.from;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


public class Expectations {

    private ClientAndServer mockServer;

    final String body = "Good hardware. The screen is huge and reproduces good colors. Watching Videos is fun on a 5.5 inch screen.";
    String author = "Tom";
    String email = "tom@tv.com";


    public Expectations(ClientAndServer mockServer) {
        this.mockServer = mockServer;
        setExpectations();
    }

    private void setExpectations() {
        expectationForTitleEmptyValidations();
        expectationForTitleMaxLengthValidations();

        expectationForCreateReviewJson();
        expectationForCreateReviewXml();
        expectationForGetReviewJson();
        expectationForGetReviewXml();
        expectationForGetReviewsByAuthor();

    }

    private void expectationForCreateReviewJson() {

        String responseString = "{\"id\":\"1\"}";

        mockServer
                .when(
                        request()
                                .withPath("/reviews")
                                .withQueryStringParameter("format", "json")
                                .withMethod("POST"))
                .respond(
                        response()
                                .withStatusCode(201)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody(responseString));
    }

    private void expectationForCreateReviewXml() {

        String responseString = "<review><id>1</id></review>";

        mockServer
                .when(
                        request()
                                .withPath("/reviews")
                                .withQueryStringParameter("format", "xml")
                                .withMethod("POST"))
                .respond(
                        response()
                                .withStatusCode(201)
                                .withHeaders(
                                        new Header("Content-Type", "application/xml")
                                )
                                .withBody(responseString));
    }

    private void expectationForGetReviewJson() {
        Object o = from(new File("target/classes/data.json"))
                .get("reviews.findAll { r -> r.title == 'Xiaomi Redmi Note 3'}[0]");
        String responseString = RequestHelper.getJsonString(o);

        mockServer
                .when(
                        request()
                                .withPath("/reviews/1")
                                .withQueryStringParameter("format", "json")
                                .withMethod("GET"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody(responseString));
    }


    private void expectationForGetReviewXml() {
        String responseString = "<review><id>1</id><title>Xiaomi Redmi Note 3</title>" +
                "<body>Good hardware. The screen is huge and reproduces good colors. Watching Videos is fun on a 5.5 inch screen.</body>" +
                "<author>Tom</author><email>tom@tv.com</email></review>";

        mockServer
                .when(
                        request()
                                .withPath("/reviews/1")
                                .withQueryStringParameter("format", "xml")
                                .withMethod("GET"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/xml")
                                )
                                .withBody(responseString));
    }

    private void expectationForGetReviewsByAuthor() {
        Object o = from(new File("target/classes/data.json"))
                .getList("reviews.findAll { r -> r.author == 'Tom'}");
        String responseString = RequestHelper.getJsonString(o);


        mockServer
                .when(
                        request()
                                .withQueryStringParameters(
                                        new Parameter("format", "json"),
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

    private void expectationForTitleEmptyValidations() {

        final String title="";
        Review review = new Review(title, body, author, email);

        mockServer
                .when(
                request()
                        .withQueryStringParameter("format", "json")
                        .withPath("/reviews")
                        .withBody(RequestHelper.getJsonString(review))
                        .withMethod("POST"))
                .respond(
                        response()
                                .withStatusCode(403)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody("{\"error\":\"_ERROR_TITLE_EMPTY\"}"));
    }

    private void expectationForTitleMaxLengthValidations() {

        final String title="This is really long title";
        Review review = new Review(title, body, author, email);

        mockServer
                .when(
                        request()
                                .withQueryStringParameter("format", "json")
                                .withPath("/reviews")
                                .withBody(RequestHelper.getJsonString(review))
                                .withMethod("POST"))
                .respond(
                        response()
                                .withStatusCode(403)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody("{\"error\":\"_ERROR_TITLE_MAX_LENGTH\"}"));
    }

}

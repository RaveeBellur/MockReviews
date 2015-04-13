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
        expectationForShouldAssertTitleOfAPost();
        expectationForShouldReturnPostsByAuthor();
    }

    private void expectationForShouldAssertTitleOfAPost() {
        Object o = from(new File("target/classes/data.json"))
                .get("posts.findAll { p -> p.title == 'Palm Tree'}[0]");
        String responseString = RequestHelper.getJsonString(o);

        mockServer
                .when(
                        request()
                                .withPath("/posts/1")
                                .withMethod("GET"))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json")
                                )
                                .withBody(responseString));
    }

    private void expectationForShouldReturnPostsByAuthor() {
        Object o = from(new File("target/classes/data.json"))
                .getList("posts.findAll { p -> p.author == 'Tom'}");
        String responseString = "{\"posts\": " + RequestHelper.getJsonString(o) + "}";


        mockServer
                .when(
                        request()
                                .withPath("/posts")
                                .withQueryStringParameters(
                                        new Parameter("author", "Tom"))
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

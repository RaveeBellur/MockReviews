import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class TestBase {

    private ClientAndServer mockServer;


    @BeforeMethod
    public void startMockServer() {
        mockServer = new ClientAndServer(8080);
        setExpectations(mockServer);
    }

    @AfterMethod
    public void stopMockServer() {
        mockServer.stop();
    }


    private void setExpectations(ClientAndServer mockServer) {
        expectationForGetPostByIdRequest(mockServer);
        expectationForGetAllPostsRequest(mockServer);
    }

    private void expectationForGetPostByIdRequest(ClientAndServer mockServer) {
        String responseString = "{" +
                "\"title\":\"Palm tree\"," +
                "\"body\":\"Palm trees are a botanical family of perennial lianas, shrubs, and trees. They are in the family Arecaceae. They grow in hot climates\"," +
                "\"author\":\"Tom\"," +
                "\"email\":\"tom@tv.com\"" +
                "}";

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

    private void expectationForGetAllPostsRequest(ClientAndServer mockServer) {
        String responseString = "{[{" +
                "\"title\":\"Palm tree\"," +
                "\"body\":\"Palm trees are a botanical family of perennial lianas, shrubs, and trees. They are in the family Arecaceae. They grow in hot climates\"," +
                "\"author\":\"Tom\"," +
                "\"email\":\"tom@tv.com\"" +
                "}]}";

        mockServer
                .when(
                        request()
                                .withPath("/posts")
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

import org.mockserver.integration.ClientAndServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestBase {

    private ClientAndServer mockServer;
    private Expectations expectations;


    @BeforeMethod
    public void startMockServer() {
        mockServer = new ClientAndServer(8080);
        expectations = new Expectations(mockServer);
    }

    @AfterMethod
    public void stopMockServer() {
        mockServer.stop();
    }



}

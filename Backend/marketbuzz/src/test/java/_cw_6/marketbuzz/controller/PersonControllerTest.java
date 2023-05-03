package _cw_6.marketbuzz.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PersonControllerTest {
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void usernameFailedTest() {

        // Send request and receive response
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                body("").
                when().
                get("/people");


        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        /** 
        // Check response body for correct response
        String returnString = response.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(returnString); 
            assertEquals("failed", returnObj.get("username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        **/
    } 
}
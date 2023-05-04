package _cw_6.marketbuzz.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;


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
    public void createAndDeleteUser() {
        JSONObject requestParams = new JSONObject(); 
        try{
            requestParams.put("username", "testRandom");
            requestParams.put("type", "admin");
            requestParams.put("password", "1");
        }catch(JSONException e){
            e.printStackTrace();
        }
        // Send request and receive response
        Response response = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(requestParams.toString()).
                when().
                post("/people/post");


        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Check response body for correct response
        String returnString = response.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(returnString); 
            //assertEquals("failed", returnObj.get("username"));
            assertEquals(requestParams.get("username"), returnObj.get("username"));
            assertEquals(requestParams.get("password"), returnObj.get("password"));
            assertEquals( 10000.0, returnObj.get("cashValue"));
            assertEquals(10000.0, returnObj.get("totalValue"));
            assertEquals("admin", returnObj.get("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response del = RestAssured.given().
            header("Content-Type","application/json" ).
            header("Accept","application/json" ).
            body("").
            when().
            delete("/person/delete/{username}", "testRandom");

        assertEquals(200, del.getStatusCode());

        String delReturn = del.getBody().asString();
        System.out.println(delReturn);
        try{
            JSONObject retObject = new JSONObject(delReturn);
            assertEquals(true, retObject.get("deleted"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    } 
}

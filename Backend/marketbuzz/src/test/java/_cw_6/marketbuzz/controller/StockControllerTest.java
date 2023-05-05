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
public class StockControllerTest {
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void createAndDeleteStock() {
        JSONObject params = new JSONObject();
        try{
            params.put("ticker", "AAPL");
            params.put("currVal", "102");
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        // Send request and receive response
        Response response = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(params.toString()).
                when().
                post("/stock/post");

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        Response response2 = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(params.toString()).
                when().
                post("/stock/post/{ticker}/{currVal}", "AMZN", "78");

        // Check status code
        int statusCode2 = response2.getStatusCode();
        assertEquals(200, statusCode2);

        // Check response body for correct response
        String returnString = response.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(returnString);
            //assertEquals("failed", returnObj.get("username"));
            assertEquals(params.get("ticker"), returnObj.get("ticker"));
            assertEquals(102.0, returnObj.get("currVal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response delete = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                delete("/stock/delete/{ticker}", "AAPL");

        assertEquals(200, delete.getStatusCode());

        Response delete2 = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                delete("/stock/delete/{ticker}", "AMZN");

        assertEquals(200, delete2.getStatusCode());

        String delReturn = delete.getBody().asString();
        System.out.println(delReturn);
        try{
            JSONObject retObject = new JSONObject(delReturn);
            assertEquals(true, retObject.get("deleted"));
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

//    @Test
//    public void createAndDeleteStockFromURL(){
//
//    }

    @Test
    public void getStocks(){
        Response stocks = RestAssured.given().
                header("Context-Type", "application/json").
                header("Accept", "application/json").
                body("").
                when().
                get("stock");

        int code = stocks.getStatusCode();
        assertEquals(200, code);
    }
}

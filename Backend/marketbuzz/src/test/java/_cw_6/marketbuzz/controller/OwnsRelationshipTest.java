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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OwnsRelationshipTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void ownsTest(){
        JSONObject owner = new JSONObject();
        try{
            owner.put("username", "testRandom");
            owner.put("type", "admin");
            owner.put("password", "1");
        }catch(JSONException e){
            e.printStackTrace();
        }
        JSONObject stock = new JSONObject();
        try{
            stock.put("ticker", "AAPL");
            stock.put("currVal", "102");
        }
        catch (JSONException e){
            e.printStackTrace();
        }


        Response create_person = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(owner.toString()).
                when().
                post("/people/post");

        Response create_stock = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(stock.toString()).
                when().
                post("/stock/post");


        JSONObject owns = new JSONObject();
        try{
            owns.put("quantity", "1");
            owns.put("owner", "testRandom");
            owns.put("ticker", "AAPL");
        }catch(JSONException e){
            e.printStackTrace();
        }

        Response buy = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(owns.toString()).
                when().
                post("/people/stocks/buy");

        String returnString = buy.getBody().asString();

        System.out.println(returnString);
        try{
            JSONObject returnObj = new JSONObject(returnString);
            assertEquals(returnObj.get("quantity"), 1);
        }catch (JSONException e){
            e.printStackTrace();
        }

        Response del = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                delete("/person/delete/{username}", "testRandom");
        Response del2 = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                delete("/stock/delete/{ticker}", "AAPL");
    }

    @Test
    public void getOwns(){
        Response testOwning = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                get("owns");

        int tmpCode = testOwning.getStatusCode();
        assertEquals(200, tmpCode);
    }
}

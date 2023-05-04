package _cw_6.marketbuzz.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.path.json.JsonPath;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.greaterThan;

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
    public void followingTest(){
        JSONObject cre = new JSONObject();
        try{
            cre.put("username", "testRandom");
            cre.put("type", "admin");
            cre.put("password", "1");
        }catch(JSONException e){
            e.printStackTrace();
        }

        JSONObject cre2 = new JSONObject();
        try{
            cre.put("username", "testRandom2");
            cre.put("type", "admin");
            cre.put("password", "1");
        }catch(JSONException e){
            e.printStackTrace();
        }

        Response create = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(cre.toString()).
                when().
                post("/people/post");
        Response create2 = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(cre2.toString()).
                when().
                post("/people/post");


        JSONObject fol = new JSONObject();
        try{
            cre.put("username", "testRandom");
            cre.put("followingUser", "testRandom2");
        }catch(JSONException e){
            e.printStackTrace();
        }
        Response follow = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(cre2.toString()).
                when().
                post("/following/post");

        String returnString = follow.getBody().asString();
         try {
             JSONObject returnObj = new JSONObject(returnString);
             assertEquals(returnObj.get("followingUser"), "testRandom2");
         } catch (JSONException e) {
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
                delete("/person/delete/{username}", "testRandom2");
    }

    // @Test
    // public void checkCurrentUsers(){
    //     JSONObject requestParams = new JSONObject(); 
    //     try{
    //         requestParams.put("username", "testRandom");
    //         requestParams.put("type", "admin");
    //         requestParams.put("password", "1");
    //     }catch(JSONException e){
    //         e.printStackTrace();
    //     }
    //     // Send request and receive response
    //     Response response = RestAssured.given().
    //             header("Content-Type","application/json" ).
    //             header("Accept","application/json" ).
    //             body(requestParams.toString()).
    //             when().
    //             post("/people/post");

    //     // Check status code
    //     assertEquals(200, response.getStatusCode());

    //     RestAssured.given().contentType(ContentType.JSON).
    //         when().get("/people/").then()
    //         .body("$.size()", greaterThan(0));


    //     // Check response body for correct response
    //     String returnString = response.getBody().asString();
    //     try {
    //         JSONObject returnObj = new JSONObject(returnString); 
    //         assertEquals(requestParams.get("username"), returnObj.get("username"));
    //         assertEquals(requestParams.get("password"), returnObj.get("password"));
    //         assertEquals( 10000.0, returnObj.get("cashValue"));
    //         assertEquals(10000.0, returnObj.get("totalValue"));
    //         assertEquals("admin", returnObj.get("type"));
    //     } catch (JSONException e) {
    //         e.printStackTrace();
    //     }
    //     //delete the user
    //     Response del = RestAssured.given().
    //         header("Content-Type","application/json" ).
    //         header("Accept","application/json" ).
    //         body("").
    //         when().
    //         delete("/person/delete/{username}", "testRandom");

    //     assertEquals(200, del.getStatusCode());

    //     String delReturn = del.getBody().asString();
    //     System.out.println(delReturn);
    //     try{
    //         JSONObject retObject = new JSONObject(delReturn);
    //         assertEquals(true, retObject.get("deleted"));
    //     }catch (JSONException e){
    //         e.printStackTrace();
    //     }
        
    // }

    @Test
    public void testVerification(){
        JSONObject tmp = new JSONObject();
        try{
            tmp.put("username", "testRandom");
        }catch(JSONException e){
            e.printStackTrace();
        }

                //people/authenticate/register
        Response testRegister = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(tmp.toString()).
                when().
                post("people/authenticate/register");

        int tmpCode = testRegister.getStatusCode();
        assertEquals(200, tmpCode);

        String returnString = testRegister.getBody().asString();
        System.out.println("pass??");
        System.out.println(returnString);
        try {
            JSONObject returnObj = new JSONObject(returnString); 
            //assertEquals("failed", returnObj.get("username"));
            assertEquals(returnObj.get("message"), "success");
            assertEquals(returnObj.get("error"), "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject cre = new JSONObject();
        try{
            cre.put("username", "testRandom");
            cre.put("type", "admin");
            cre.put("password", "1");
        }catch(JSONException e){
            e.printStackTrace();
        }

        Response create = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(cre.toString()).
                when().
                post("/people/post");

        Response failRegister = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(tmp.toString()).
                when().
                post("people/authenticate/register");

        int failCode = failRegister.getStatusCode();
        assertEquals(200, failCode);

        String failString = failRegister.getBody().asString();
        System.out.println("fail??");
        System.out.println(failString);
        try {
            JSONObject failObj = new JSONObject(failString); 
            //assertEquals("failed", returnObj.get("username"));
            assertEquals(failObj.get("message"), "failure");
            assertEquals(failObj.get("error"), "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response del = RestAssured.given().
            header("Content-Type","application/json" ).
            header("Accept","application/json" ).
            body("").
            when().
            delete("/person/delete/{username}", "testRandom");
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

package _cw_6.marketbuzz.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
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
            cre2.put("username", "testRandom2");
            cre2.put("type", "admin");
            cre2.put("password", "1");
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


        System.out.println(cre.toString() + "----" + cre2.toString());


        JSONObject fol = new JSONObject();
        try{
            fol.put("username", "testRandom");
            fol.put("followingUser", "testRandom2");
        }catch(JSONException e){
            e.printStackTrace();
        }
        Response follow = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(fol.toString()).
                when().
                post("/following/post");

        String returnString = follow.getBody().asString();
        System.out.println(returnString);
         try {
             JSONObject returnObj = new JSONObject(returnString);
             assertEquals(returnObj.get("followingUser"), "testRandom2");
         } catch (JSONException e) {
             e.printStackTrace();
         }

        Response followVerif =RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(fol.toString()).
                when().
                post("/following/people");
         assertEquals(200, followVerif.getStatusCode());
        String verifFol = followVerif.getBody().asString();
        System.out.println("VERIFY" + verifFol);
        try {
            JSONArray returnArr = new JSONArray(verifFol);
            JSONObject testObj = (JSONObject) returnArr.get(0);
            assertEquals(testObj.get("followingUser"), "testRandom2");
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
    public void testUserData(){
        //username/string/data
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

        JSONObject veri = new JSONObject();
        try{
            veri.put("username", "testRandom");
        }catch(JSONException e){
            e.printStackTrace();
        }
        Response verifyUser = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(veri.toString()).
                when().
                post("/username/string/data");

        System.out.println(verifyUser.toString());

        
        
        Response del = RestAssured.given().
            header("Content-Type","application/json" ).
            header("Accept","application/json" ).
            body("").
            when().
            delete("/person/delete/{username}", "testRandom");
    

    }

    @Test
    public void getPeople(){
        Response testPeople = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                get("people");

        int tmpCode = testPeople.getStatusCode();
        assertEquals(200, tmpCode);
    }

    @Test
    public void getFollowing(){
        Response testFollowing = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                get("following");

        int tmpCode = testFollowing.getStatusCode();
        assertEquals(200, tmpCode);
    }

    @Test
    public void getLeaderboard(){
        Response testLeaderboard = RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body("").
                when().
                get("leaderboard");

        int tmpCode = testLeaderboard.getStatusCode();
        assertEquals(200, tmpCode);
    }

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

        //people/authenticate
        Response verify =RestAssured.given().
                header("Content-Type","application/json" ).
                header("Accept","application/json" ).
                body(cre.toString()).
                when().
                post("/people/authenticate");
        String findUser = verify.getBody().asString();
        System.out.println("verify: " + findUser);
        try {
            JSONObject verObj = new JSONObject(findUser);
            //assertEquals("failed", returnObj.get("username"));
            assertEquals(verObj.get("message"), "success");
            assertEquals(verObj.get("error"), "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


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

        Response checkData = RestAssured.given().
            header("Content-Type","application/json" ).
            header("Accept","application/json" ).
            body(requestParams.toString()).
            when().
            post("/person/data");
        assertEquals(200, checkData.getStatusCode());
        String returnData = checkData.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(returnData);
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

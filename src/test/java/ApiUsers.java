import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiUsers {


    String uriBase = "https://reqres.in/api/users/";

    @Test
    public void getUsers() {
        given().
                when()
                .get(uriBase)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void postUsers() {
        given()
                .body("{\n " +
                        "\"name\": \"eduardo\",\n" +
                        "\"job\": \"analista\", \n" +
                        "}")
                .when()
                .post(uriBase)
                .then()
                .assertThat()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("postValidationSchema.json"));


    }
    @Test
    public void validarGet400(){
        given()
                .when()
                .get(uriBase + "23")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void updateUsers(){
        given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put(uriBase + "2")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
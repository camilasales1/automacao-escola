package APITests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MateriaTest {

    @Before
    public void setUp() {
        baseURI = "http://localhost:8080";
    }

    @Test
    public void getMateriaById() {
        when()
                .get("/materia/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("nome", equalTo("Java"));
    }

    @Test
    public void getMaterias() {
        List<Object> list = RestAssured
                .when()
                .get("/materia")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().body().jsonPath().getList("");
    }

    @Test
    public void addMateria() {
//        map ou JSONObject
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("nome", "QA");

        JSONObject request = new JSONObject();
        request.put("nome", "QA");
        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post("/materia")
                .then()
                .statusCode(201);
    }

    @Test
    public void updateMateria() {
        JSONObject request = new JSONObject();
        request.put("nome", "qa");
        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .put("/materia/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMateria() {
        when()
                .delete("/materia/2")
                .then()
                .statusCode(204);
    }
}




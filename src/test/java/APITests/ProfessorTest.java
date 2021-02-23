package APITests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProfessorTest {

    @Before
    public void setUp() {
        baseURI = "http://localhost:8080";
    }

    @Test
    public void getProfessorById() {
        when()
                .get("/professor/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("nome", equalTo("Julia"))
                .body("pais", equalTo("Espanha"));
    }

    @Test
    public void getProfessores() {
        List<Object> list = RestAssured
                .when()
                .get("/professor")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().body().jsonPath().getList("");
    }

    @Test
    public void addProfessor() {
//        map ou JSONObject
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("nome", "QA");

        JSONObject request = new JSONObject();
        request.put("nome", "Walter");
        request.put("pais", "Canada");
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
    public void updateProfessor() {
        JSONObject request = new JSONObject();
        request.put("nome", "Walter");
        request.put("pais", "Italia");
        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .put("/professor/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMateria() {
        when()
                .delete("/professor/2")
                .then()
                .statusCode(204);
    }
}

package APITests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MentoriaTest {

    @Before
    public void setUp() {
        baseURI = "http://localhost:8080";
    }

    @Test
    public void getMentoriaById() {
        when()
                .get("/mentoria/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("aluno.id", equalTo(1))
                .body("professor.id", equalTo(1));


    }

    @Test
    public void getMentorias() {
        List<Object> list = RestAssured
                .when()
                .get("/mentoria")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().body().jsonPath().getList("");
    }

    @Test
    public void addMentoria() {
//        map ou JSONObject
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("nome", "QA");

        JSONObject request = new JSONObject();
        request.put("aluno.id", 2);
        request.put("professor.id", 1);
        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post("/mentoria")
                .then()
                .statusCode(201);
    }

    @Test
    public void updatePrograma() {
        JSONObject request = new JSONObject();
        request.put("nome", "Estagio");
        request.put("dataInicio", "2020-12-20");
        request.put("dataFim", "2021-03-22");
        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .put("/programa/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMateria() {
        when()
                .delete("/programa/2")
                .then()
                .statusCode(204);
    }
}

package APITests;

import static io.restassured.RestAssured.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AlunoTest {

    @Before
    public void setUp() {
        baseURI = "http://localhost:8080";
    }

    @Test
    public void teste() {
        Response response = get("http://localhost:8080/aluno/");

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void GetAlunoById() {
        when()
                .get("aluno/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("nome", equalTo("Renata"));
    }

    @Test
    public void GetAlunos() {
        List<Object> list = RestAssured
                .when()
                .get("/aluno")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON).extract().body().jsonPath().getList("");

    }

    @Test
    public void AddAluno() {
        JSONObject request = new JSONObject();
        request.put("classe", "2-B");
        request.put("nome", "João");
        request.put("programa.id", 1);
        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post("/aluno")
                .then()
                .statusCode(201);
    }

    @Test
    public void updateAluno() {
        JSONObject request = new JSONObject();
        request.put("classe", "2-B");
        request.put("nome", "John");

        System.out.println(request);

        given()
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .put("/aluno/8")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteAluno() {
        when()
                .delete("/aluno/7")
                .then()
                .statusCode(204);
    }
}


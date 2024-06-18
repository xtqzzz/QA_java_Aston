package org.example;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

public class AppTest {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGet() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    public void testPost() {
        given()
                .contentType("application/json")
                .body("{\"name\":\"Nikita\", \"lastName\":\"Kostyukov\"}")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Nikita"))
                .body("json.lastName", equalTo("Kostyukov"));
    }

    @Test
    public void testPut() {
        given()
                .contentType("application/json")
                .body("{\"foo1\":\"bar1\", \"foo2\":\"bar2\"}")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"));
    }

    @Test
    public void testPatch() {
        given()
                .contentType("application/json")
                .body("{\"foo1\":\"bar1\", \"foo2\":\"bar2\"}")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"));
    }

    @Test
    public void testDelete() {
        when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json", nullValue()); // тело ответа пустое, проверяем на null
    }
}

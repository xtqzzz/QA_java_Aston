package src.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.codehaus.groovy.ast.tools.GeneralUtils.param;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
//    @Test
//    public void echoGetTest() {
//        RestAssured.
//                when().get("https://postman-echo.com/get?foo1=bar1&foo2=bar2").
//                then().assertThat().statusCode(200).
//                body("args.foo1", equalTo("bar1")).
//                body("args.foo2", is("bar2")).
//                body("headers.host", equalTo("postman-echo.com")).
//                body("headers.x-request-start", notNullValue()).
//                body("headers.connection", equalTo("close")).
//                body("headers.x-forwarded-proto", equalTo("https")).
//                body("headers.x-forwarded-port", equalTo("443")).
//                body("headers.x-amzn-trace-id", notNullValue()).
//                body("headers.user-agent", notNullValue()).
//                body("headers.accept", equalTo("*/*")).
//                body("headers.accept-encoding", containsString("gzip,deflate"));
//    }
    @Test
    public void echoPostTest() {
        String URL = "https://postman-echo.com/post";
        given()
                .post(URL)
                .then().log().body()
//                .body("arg", equalTo("{}"))
//                .body("files", equalTo("{}"))
//                .body("form", equalTo("{}"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.content-length", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept-encoding", containsString("gzip,deflate"))
                .body("json", equalTo(null))
                .body("url", equalTo("https://postman-echo.com/post"));
    }
}
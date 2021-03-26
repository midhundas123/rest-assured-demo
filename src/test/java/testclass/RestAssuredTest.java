package testclass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTest {
    final static String ROOT_URI = "http://localhost:9080/v1";

    @Test
    public void simple_get_test() {
        Response response = get(ROOT_URI + "/books");
        System.out.println(response.asString());
        response.then().body("author", hasItems("Margaret Wise Brown"));
    }

    //@Test
    public void simple_get_test1() {

        when().
                get("/lotto/{id}", 5).
        then().
                statusCode(200).
                body("lotto.lottoId", equalTo(5),
                        "lotto.winners.winnerId", hasItems(23, 54));
    }

    @Test
    public void simple_get_test2() {

        when().
                get("http://localhost:9080/v1/books").
        then().
                statusCode(200).
                body("author", hasItems("Margaret Wise Brown"));
    }

    @Test
    public void simple_get_test3() {
        String payload = "{\n" +
                "  \"id\": \"102\",\n" +
                "  \"title\": \"abcd\",\n" +
                "  \"author\": \"abcd\",\n" +
                "  \"description\": \"abcd\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(payload);
        when().
                post("http://localhost:9080/v1/books").
        then().
                statusCode(415);

    }



}

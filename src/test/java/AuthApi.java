import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi{
    private final static String EMAIL = "test123@ya.ru";
    private final static String PASSWORD = "testtest13";


    @Step("Get new session Cookie")
    public Map<String, String> cookies() {

        return given()
                .filter(new AllureRestAssured())  //.filter(filters().withCustomTemplates()) Для логов
                //.accept(ContentType.ANY) //Принимаем любой контент
                .contentType("application/x-www-form-urlencoded")

                .body(getData())
                //.basePath("/login")
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(302)
                .extract().cookies();


    }

    private String getData() {
        return "email=" + EMAIL + "&password=" + PASSWORD; //потому что = application/x-www-form-urlencoded
    }
}











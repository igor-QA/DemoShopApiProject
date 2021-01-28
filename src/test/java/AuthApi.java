import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi{

    @Step("Get new session Cookie")
    public Map<String, String> cookies() {
/** .filter(filters().withCustomTemplates()) Для удобно читаемых логов
                .accept(ContentType.ANY), Принимаем любой контент */

        return given()
                .filter(new AllureRestAssured())
                .contentType("application/x-www-form-urlencoded")
                .formParam("email","test123@ya.ru")
                .formParam("password", "testtest13")
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(302)
                .extract().cookies();
    }
}

/** потому что = application/x-www-form-urlencoded
 * private String getData() {
 return "email=" EMAIL + "&password=" + PWD;
 * Если испоьзовать через body = body(getData())
 }
 */
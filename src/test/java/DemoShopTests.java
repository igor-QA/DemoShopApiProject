import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Owner("Igor Pavlov")
@Feature("Login via API")
@Tag("api")

public class DemoShopTests {

    @BeforeAll
    static void beforeEach() {
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
    }

    @Test
    void openUrlWithAuthCookies() {

        //Передаем куки для авторизации
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        AuthApi api = new AuthApi();
        CookieManager cookieManager = new CookieManager(api.cookies());
        cookieManager.addCookiesToSite();

        //Делаем дейсвия на сайте уже авторизованные
        open("http://demowebshop.tricentis.com");
        $(By.className("account")).click();
        $("div.listbox ul li:nth-child(2) a").click();
        $("div.add-button").click();
        $("h1").shouldHave(text("My account - Add new address"));

    }
}
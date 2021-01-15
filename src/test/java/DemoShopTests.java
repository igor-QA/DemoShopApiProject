import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.Cookie;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Owner("Igor Pavlov")
@Feature("Login via API")
@Tag("api")
    public class DemoShopTests {

    @Test
    void accountAddNewAddresses() {

        //Передаем куки для авторизации
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        Cookie cookie = new Cookie("login", "password");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);

        //Делаем дейсвия на сайте уже авторизованные
        open("http://demowebshop.tricentis.com");
        $(By.className(".account")).click();
        $(By.className(".active")).click();
        $(By.className("button-1 add-address-button")).click();
        $("h1").shouldHave(text("My account - Add new address"));

    }

}
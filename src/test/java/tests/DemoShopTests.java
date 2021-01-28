package tests;

import api.client.APIClient;
import api.spec.Request;
import cookie.SiteCookies;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("Igor Pavlov")
@Feature("Login via API")
public class DemoShopTests {
    private Map<String, String> cookies;
    private SiteCookies siteCookies;
    private APIClient client;

    @Test
    public void openSiteWithCookies() {
        step("Получение cookie", () -> {
            step("Инициализация api-клиента", () -> {
                client = new APIClient(Request.specification());
            });
            step("Авторизация и получение cookie", () -> {
                cookies = client.login();
            });
            step("Переход на страницу в браузере и добавление cookie", () -> {
                step("Открыть /Themes/DefaultClean/Content/images/logo.png", () -> {
                    open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
                });
                step("Добавить cookie в сессию браузера", () -> {
                    siteCookies = new SiteCookies(cookies);
                    siteCookies.addCookiesToDriver();
                });
            });
        });
        step("Добавление нового адреса под авторизованным пользователем", () -> {
            step("Перейти на страницу http://demowebshop.tricentis.com", () -> {
                open("http://demowebshop.tricentis.com");
            });
            step("Перейти в личный кабинет", () -> {
                $(".account").as("Account").click();
            });
            step("Перейти на вкладку Addresses", () -> {
                $("div.listbox ul li:nth-child(2) a").as("Addresses").click();
            });
            step("Нажать кнопку 'Add new'", () -> {
                $("div.add-button").as("Add new").click();
            });
            step("Проверить что заголовок страницы содержит текст 'Add new address'", () -> {
                $("h1").as("Header").shouldHave(text("My account - Add new address"));
            });
        });
    }
}

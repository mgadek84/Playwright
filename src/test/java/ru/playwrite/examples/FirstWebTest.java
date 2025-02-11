package ru.playwrite.examples;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstWebTest {

    static Page page;

    @BeforeAll
    public static void setup() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @Test
    public void first_test() {
        // Переходим на страницу по url-адресу "http://playwright.dev"
        page.navigate("http://playwright.dev");

        // Проверяем, что заголовк страницы содержит подстроку "Playwright"
        assertThat(page).hasTitle(Pattern.compile("Playwright"));

        // Создаем локатор. Это ссылка, которая содержит текст "Get Started"
        Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));

        // Проверяем, что html атрибут href содержит адрес "/docs/intro"
        assertThat(getStarted).hasAttribute("href", "/docs/intro");

        // Кликаем по ссылке "Get Started"
        getStarted.click();

        // Проверяем, что заголовок - тег <h1></h1> содержащий текст "Installation" виден на странице
        assertThat(page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Installation"))).isVisible();
    }
}

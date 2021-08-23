package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cookies_zadanie3 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_85.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
    }
    @AfterEach
    public void closeAndQuit () {
        driver.close();
        driver.quit();
    }
    @Test
    public void CookiesFromWiki () {
        //1. Pobierz wszystkie ciasteczka i przy pomocy asercji sprawdź, czy jest ich tyle ile powinno.
        driver.manage().getCookies();
        Assertions.assertEquals(3, driver.manage().getCookies().size(), "Number of cookies is not what expected");
        //2. Dodaj swoje ciasteczko i potwierdź asercją, że się dodało.
        Cookie newCookie = new Cookie("testCookie", "testValue");
        driver.manage().addCookie(newCookie);
        Assertions.assertEquals(4, driver.manage().getCookies().size(), "Number of cookies is not what expected.");
        //3. Pobierz swoje ciasteczko i użyj asercji, żeby porównać, że nazwa ciasteczka jest taka, jakiej oczekujesz.
        Assertions.assertEquals(newCookie.getName(), driver.manage().getCookieNamed("testCookie").getName(), "Name of cookie is not correct.");
        //4. Usuń swoje ciasteczko używając obiektu typu Cookie jako parametru i potwierdź, że zostało usunięte.
        driver.manage().deleteCookie(newCookie);
        Assertions.assertEquals(3,driver.manage().getCookies().size(), "Number of cookies is not what expected.");
        //5. Usuń jakieś ciasteczko używając jego nazwy jako parametru i potwierdź, że zostało usunięte.
        driver.manage().deleteCookieNamed("GeoIP");
        Assertions.assertEquals(2, driver.manage().getCookies().size(), "Number of cookies is not what expected.");
        //6. Pobierz dowolne już istniejące ciasteczko i użyj asercji, żeby potwierdzić, że domena, ścieżka i ustawienie flagi HTTP jest takie, jak tego oczekujemy.
        Cookie cookie = driver.manage().getCookieNamed("WMF-Last-Access");
        Assertions.assertEquals("pl.wikipedia.org", cookie.getDomain(), "Cookie domain is not what expected.");
        Assertions.assertEquals("/" ,cookie.getPath(), "Cookie path is not what expected.");
        Assertions.assertTrue(cookie.isHttpOnly(), "Cookie is not HTTP only");
    }

}

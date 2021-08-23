package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class URL_zrodloStronyTytulStrony {

    WebDriver driver;

    @BeforeEach
    public void driverSetup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));

    }
    @AfterEach
    public void closeAndQuick() {
        //Zamknij okno przeglądarki.
        driver.close();
        //Zamknij sesję.
        driver.quit();
    }
    @Test
    public void ifLanguageChange (){
        //1.Wejdz na stronę http.wikipedia.pl
        driver.navigate().to("http://wikipedia.pl");
        //Napisz 3 asercje
        //a.porownaj tytul strony z oczekiwanym
        String expectedPolishTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(expectedPolishTitle, driver.getTitle(), "Page title is not: " + expectedPolishTitle);
        //b. porownaj URL strony z oczekiwanym
        String expectedPolishURL = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        Assertions.assertEquals(expectedPolishURL, driver.getCurrentUrl(), "Current URL is not: " + expectedPolishURL);
        //c. znajdz w konsoli w zakładce Elements jakis fragment, który mówi w jakim języku jest ta strona
        String polishLanguage = "lang=\"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(polishLanguage), "Page source does not contain: " + polishLanguage);
        //3. zmień język strony na hiszpański
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        //4. Napisz 3 asercje
        //a.Porównaj tytuł strony z oczekiwanym
        String expectedSpanishTitle = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(expectedSpanishTitle, driver.getTitle(), "Page title is not: " + expectedSpanishTitle);
        //b.Porównaj URL strony z oczekiwanym
        String expectedSpanishURL = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        Assertions.assertEquals(expectedSpanishURL, driver.getCurrentUrl(), "Current URL is not: " + expectedSpanishURL);
        //c.Znajdz w konsoli developerskiej Elements jakiś fragment źródła strony, który mówi o tym w jakiej wersji językowej jest strona; użyj tego fragmentu źródła do asercji.
        String spanishLanguage = "lang=\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(spanishLanguage), "Page source does not contain: " + spanishLanguage);

    }
}


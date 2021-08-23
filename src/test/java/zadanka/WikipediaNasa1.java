package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikipediaNasa1 {
    WebDriver driver;


    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @AfterEach
    public void closeAndQuick() {
        //Zamknij okno przeglądarki.
        driver.close();
        //Zamknij sesję.
        driver.quit();
    }

    @Test
    public void navigationTest() {
        //Otwiera stronę główną Wikipedii.
        driver.navigate().to("https://www.wikipedia.org/");
        //Otworzy stronę główną Nasa.
        driver.navigate().to("https://www.nasa.gov/");
        //Cofnie się do strony głównej Wikipedii - używając nawigacji Wstecz.
        driver.navigate().back();
        //Potwierdzi, że driver jest na stronie Wikipedii; porówna tytuł strony z oczekiwanym.
        String wikiTitle = "Wikipedia";
        Assertions.assertEquals(wikiTitle, driver.getTitle(), "The title of the page is not: " + wikiTitle);
        //Przechodzi do strony Nasa używając nawigacji naprzód.
        driver.navigate().forward();
        //Potwierdzi, że driver jest na stronie Nasa; porówna tytuł z oczekiwanym.
        String nasaTitle = "NASA";
        Assertions.assertEquals(nasaTitle, driver.getTitle(), "The title of the page is not: " + nasaTitle);

    }
}
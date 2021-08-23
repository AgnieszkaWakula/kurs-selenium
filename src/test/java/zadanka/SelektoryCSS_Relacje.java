package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelektoryCSS_Relacje {
    WebDriver driver;

    @BeforeEach
    public void driverSetup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/wyszukiwanie-elementow-poprzez-relacje/");
    }
    @AfterEach
    public void closeAndQuit () {
        driver.close();
        driver.quit();
    }
    @Test
    public void zadanie_SearchElementsByUsingRelations () {
//        1. Znajdź pole do wpisania imienia w pierwszym formularzu.
        driver.findElement(By.cssSelector("dd#name-element>input#name"));
//        2. Znajdź pole do wpisania adresu email w trzecim formularzu.
        driver.findElement(By.cssSelector("div.second-div>input#email"));
//        3. Znajdź pole do wpisania adresu email w trzecim formularzu używając selektorów pseudoklas.
        driver.findElement(By.cssSelector("div.second-div>input:nth-of-type(1)"));
        driver.findElement(By.cssSelector("div.second-div>input:first-of-type"));
        driver.findElement(By.cssSelector("div.second-div>input:nth-child(3)"));
//        4. Znajdź przycisk „Subscribe” w piątym formularzu.
        driver.findElement(By.cssSelector("div.second-div input#submit"));
//        5. Znajdź przycisk „Subscribe” w piątym formularzu używając selektorów pseudoklas.
        driver.findElement(By.cssSelector("div.second-div input#submit:last-child"));
        driver.findElement(By.cssSelector("div.second-div input#submit:last-of-type"));
//        6. Znajdź przycisk „Subscribe” w drugim formularzu.
        driver.findElement(By.cssSelector("div:not([class])>button#submit"));
        driver.findElement(By.cssSelector("div:not(.second-div)>button#submit"));
//        7. Znajdź przycisk „Subscribe” w drugim formularzu używając selektorów pseudoklas.
        driver.findElement(By.cssSelector("div:not([class])>button:only-of-type"));
        driver.findElement(By.cssSelector("div:not([class])>button#submit:last-of-type"));
    }
}

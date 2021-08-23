package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelektoryCSS_ProsteSelektory {

    WebDriver driver;

    @BeforeEach
    public void driverSetup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("http://fakestore.testelka.pl/moje-konto/");
    }
    @AfterEach
    public void closeAndQuit () {
        driver.close();
        driver.quit();
    }
    @Test
    public void zadanie_prosteSelektory () {
        //Szukajka
        driver.findElement(By.id("woocommerce-product-search-field-0"));
        driver.findElement(By.className("search-field"));
        driver.findElement(By.name("s"));
        //pole do wpisania nazwy użytkownika,
        driver.findElement(By.id("username"));
        driver.findElement(By.name("username"));
        driver.findElement(By.cssSelector("input[class='woocommerce-Input woocommerce-Input--text input-text']"));
        //pole do wpisania hasła,
        driver.findElement(By.id("password"));
        driver.findElement(By.name("password"));
        //przycisk logowania,
        driver.findElement(By.name("login"));
        driver.findElement(By.cssSelector("button[class='woocommerce-button button woocommerce-form-login__submit']"));
        //checkbox do zapamiętania hasła,
        driver.findElement(By.id("rememberme"));
        driver.findElement(By.name("rememberme"));
        //link do odzyskiwania hasła,
        driver.findElement(By.linkText("Nie pamiętasz hasła?"));
        driver.findElement(By.partialLinkText("hasła"));
        //link do kategorii “Żeglarstwo”.
        driver.findElement(By.linkText("Żeglarstwo"));
    }
}

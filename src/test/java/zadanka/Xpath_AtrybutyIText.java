package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Xpath_AtrybutyIText {
    WebDriver driver;

    @BeforeEach
    public void driverSetup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-atrybuty-w-xpath/");
    }
    @AfterEach
    public void closeAndQuit () {
        driver.close();
        driver.quit();
    }
    @Test
    public void selectorXpath_useAttributeAndText () {
//        Przyciski 1, 2, 5, 6.
        driver.findElement(By.xpath(".//*[text()='Button']"));
        driver.findElement(By.xpath(".//*[contains(@title, 'Button')]"));
//        Przyciski 3 i 7.
        driver.findElement(By.xpath(".//*[contains(@style, 'background-color: #db456f')]"));
//        Przyciski 3, 4, 7.
        driver.findElement(By.xpath(".//*[contains(text(), 'Btn')]"));
        driver.findElement(By.xpath(".//*[starts-with(@id, 'btn') and contains(@title, 'Btn')]"));
//        Przyciski 1, 2, 5.
        driver.findElement(By.xpath(".//*[starts-with(@id, 'button-')"));
//        Przyciski 2, 6, 7.
        driver.findElement(By.xpath(".//*[@class= 'button primary test']"));
//        Przyciski 1, 3, 4, 5.
        driver.findElement(By.xpath(".//*[contains(@class, 'accent')]"));
        driver.findElement(By.xpath(".//*[contains(@class, 'button accent')]"));
        driver.findElement(By.xpath(".//*[starts-with(@class, 'accent')]"));
//        Przyciski 1, 3, 5.
        driver.findElement(By.xpath(".//*[contains(@class, 'accent') and not(contains(@class, 'accent-test'))]"));
//        Przyciski 1 i 5.
        driver.findElement(By.xpath(".//*[contains(@class, 'button accent') and text()='Button']"));
        driver.findElement(By.xpath(".//*[contains(@class, 'button accent') and contains(@title, 'Button')]"));
    }
}

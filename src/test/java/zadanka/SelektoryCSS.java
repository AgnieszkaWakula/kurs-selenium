package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelektoryCSS {
    WebDriver driver;

    @BeforeEach
    public void driverSetup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-fragmenty-wartosci-atrybutow/");
    }
    @AfterEach
    public void closeAndQuit () {
        driver.close();
        driver.quit();
    }
    @Test
    public void zadanie_SearchElementsByUsingOneSelector () {
//        1. Button1, Button 2, Btn 3, Btn 4
        driver.findElements(By.cssSelector("a[class^='button']"));
        driver.findElements(By.cssSelector("a[title*='B']"));
        driver.findElements(By.cssSelector("a[id*='b']"));
//        2. Btn 3, Btn 4, Btn 7
        driver.findElements(By.cssSelector("div>[title*='Btn']"));
        driver.findElements(By.cssSelector("div>[id^='btn-']"));
        driver.findElements(By.cssSelector("[id|='btn']"));
//        3. Btn 3, Btn 7
        driver.findElements(By.cssSelector("[style='margin: 5px; background-color: #db456f'"));
        driver.findElements(By.cssSelector(("[style*='background-color: #db456f']")));
        driver.findElements(By.cssSelector("[style$='#db456f']"));
//        4. Button1, Button 2, Button 5
        driver.findElements(By.cssSelector("[id^='button-']"));
        driver.findElements(By.cssSelector("[id|=button]"));
//        5. Button1, Btn 3, Button 5
        driver.findElements(By.cssSelector(".accent"));
//        6. Button 2, Button6, Btn 7
        driver.findElements(By.cssSelector(".primary"));
        driver.findElements(By.cssSelector("[class*='primary test']"));
        driver.findElements(By.cssSelector("[class='button primary test']"));
//        7. Button1, Btn 3, Btn 4, Button 5
        driver.findElements(By.cssSelector("[class*='accent']"));
        driver.findElements(By.cssSelector("[class^='button accent']"));
    }
}

package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Xpath_relacje {
    WebDriver driver;

    @BeforeEach
    public void driverSetup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-xpath/");
    }
    @AfterEach
    public void closeAndQuit () {
        driver.close();
        driver.quit();
    }
    @Test
    public void selectorXpath_useRelations () {
        //Nabywca
        driver.findElement(By.xpath(".//strong[text()='Nabywca:']/.."));
        driver.findElement(By.xpath(".//strong[text()='Nabywca:']/parent::*"));
        driver.findElement(By.xpath(".//strong[text()='Nabywca:']/parent::td"));

        driver.findElement(By.xpath(".//div//td[@colspan='2'][2]"));
        //Ilość:100
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/following-sibling::td"));
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/../td[2]"));
        driver.findElement(By.xpath(".//tbody/tr[2]/td[2]"));
        driver.findElement(By.xpath(".//tbody/tr[2]//td[2]"));
        //Cena: 10:00
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/following-sibling::td[2]"));
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/../td[4]"));
        driver.findElement(By.xpath(".//tr[2]/td[3]"));
        //Koszt:1000.00
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/following-sibling::td[3]"));
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/../td[4]"));
        driver.findElement(By.xpath(".//tr[2]/td[4]"));

    }
}

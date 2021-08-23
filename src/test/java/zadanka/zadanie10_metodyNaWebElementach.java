package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class zadanie10_metodyNaWebElementach {
    WebDriver driver;
    WebDriverWait wait;
    By demoStoreBar = By.cssSelector("a.woocommerce-store-notice__dismiss-link");

    @BeforeEach
    public void setup () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(10,40));
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach/");
        wait = new WebDriverWait(driver, 10);
        driver.findElement(demoStoreBar).click();
    }
    @AfterEach
    public void driverQuit () {
        driver.close();
        driver.quit();
    }
    @Test
    public void checkElementsProperties () {
        WebElement homeButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement disabledSailingButton = driver.findElement(By.cssSelector("a[name='sailing']"));
        List<WebElement> yellowButtons = driver.findElements(By.cssSelector("a.button"));
        List<WebElement> selectedElements = driver.findElements(By.cssSelector("input[name^='selected']"));
        List<WebElement> notSelectedElements = driver.findElements(By.cssSelector("input[name^='not-selected']"));
        List<WebElement> elementsWithButtonClass = driver.findElements(By.cssSelector(".button"));


        Assertions.assertAll("Checking properties of elements",
                //        1. czy przycisk prowadzący do strony głównej jest nieaktywny?
                ()-> Assertions.assertFalse(homeButton.isEnabled(), "Home button is not disabled."),
                //        2. czy niewidoczny button (znajdziesz go w konsoli) rzeczywiście zwraca, że jest niewidoczny?
                ()-> Assertions.assertTrue(!disabledSailingButton.isDisplayed(), "Sailing button is displayed."),
                //        3. czy żółte buttony są w kolorze rgba(245, 233, 101, 1)?
                ()-> assertThatButtonsAreYellow(yellowButtons),
                //        4. czy zaznaczone checkboxy i radio buttony zwracają informację, że są zaznaczone?
                ()-> assertThatCheckboxesAreSelected(selectedElements),
                //        5. czy niezaznaczone checkboxy i radiobuttony zwracają informację, że nie są zaznaczone?
                ()-> assertThatCheckboxesAreUnchecked(notSelectedElements),
                //        6. potwierdź, że wszystkie elementy z klasą button, są elementami o tagu a;
                ()-> elementsHaveTagA(elementsWithButtonClass)
                );


    }
    public void assertThatButtonsAreYellow(List<WebElement> buttons) {
        for (WebElement button:buttons) {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)", color, "Button color is not what expected.");
        }
    }
    public void assertThatCheckboxesAreSelected(List<WebElement> selectedCheckboxes) {
        for (WebElement checkbox : selectedCheckboxes) {
            String status = checkbox.getAttribute("checked");
            Assertions.assertNotNull(status, "Checkbox is not selected.");
        }
    }
    public void assertThatCheckboxesAreUnchecked (List<WebElement> notSelectedCheckboxes) {
        for (WebElement checkbox: notSelectedCheckboxes) {
            String status = checkbox.getAttribute("checked");
            Assertions.assertNull(status, "Checkbox is selected.");

        }
    }
    public void elementsHaveTagA (List<WebElement> elements) {
        for (WebElement element: elements) {
            Assertions.assertEquals("a",  element.getTagName(), "Element has not \"a\" tag what expected.");
        }
    }

}







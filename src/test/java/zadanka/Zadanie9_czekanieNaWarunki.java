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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Zadanie9_czekanieNaWarunki {
    WebDriver driver;
    WebDriverWait wait;
    By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");
    By pilatesGroup = By.cssSelector("a[href*='yoga-i-pilates']");
    By product = By.cssSelector("li.post-64");
    By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    By goToCartButton = By.cssSelector("a.cart-contents");
    String correctCoupon = "10procent";
    String incorrectCoupon = "test";
    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10,40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl");
        wait = new WebDriverWait(driver, 10);
        driver.findElement(cookieConsentBar).click();
        driver.findElement(pilatesGroup).click();
        driver.findElement(product).click();
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
        driver.findElement(goToCartButton).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1[class='entry-title']"), "Koszyk"));

    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }
    @Test
    public void emptyCouponTest(){
        applyCoupon("");
        Assertions.assertEquals("Prosz?? wpisa?? kod kuponu.", getAlertText(), "Alert message was not what expected.");
    }
    @Test
    public void incorrectCouponTest(){
        applyCoupon(incorrectCoupon);
        Assertions.assertEquals("Kupon \"" + incorrectCoupon + "\" nie istnieje!", getAlertText(), "Alert message was not what expected.");
    }
    @Test
    public void correctCouponTest(){
        applyCoupon(correctCoupon);
        Assertions.assertEquals("Kupon zosta?? pomy??lnie u??yty.", getAlertText(), "Alert message was not what expected.");
    }
    @Test
    public void addingCouponWhenAlreadyAppliedTest(){
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        Assertions.assertEquals("Kupon zosta?? zastosowany!", getAlertText(), "Alert message was not what expected.");
    }
    @Test
    public void removingCouponTest(){
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        By removeLink = By.cssSelector("a.woocommerce-remove-coupon");
        wait.until(ExpectedConditions.elementToBeClickable(removeLink)).click();
        waitForProcessingEnd();
        Assertions.assertEquals("Kupon zosta?? usuni??ty.", getAlertText(), "Alert message was not what expected.");
    }
    private void applyCoupon(String coupon){
        By couponCodeField = By.cssSelector("input[name='coupon_code']");
        By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
        driver.findElement(couponCodeField).sendKeys(coupon);
        driver.findElement(applyCouponButton).click();
    }
    private void waitForProcessingEnd(){
        By blockedUI = By.cssSelector("div.blockUI");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(blockedUI, 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(blockedUI,0));
    }
    private String getAlertText(){
        By alert = By.cssSelector("[role='alert']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText();
    }
}


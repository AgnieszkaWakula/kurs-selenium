package zadanka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class zadanie7 {
    WebDriver driver;
    String userName = "agnieszka.wakula";
    String email = "agnieszka.wakula@gmail.com";
    String password = "KursSelenium";
    String wrongPassword = "wrong";
    String nonexistentUserName = "NonExistentUser";
    String nonexistentEmail = "notexistingemail@testelka.pl";
    String userFullName = "Pocahontas";
    String myAccountContent;
    String errorMessageText;
    String expectedMessage;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_85.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }
    @AfterEach
    public void closeAndQuit(){
        driver.close();
        driver.quit();
    }
    @Test
    public void existentUsernameCorrectPasswordTest(){
        logIn(userName, password);
        myAccountContent = getDisplayedName();
        Assertions.assertTrue(myAccountContent.contains(userFullName),
                "My Account page does not contain correct name. Expected name: " + userFullName + " was not found in a string: " + myAccountContent);
    }
    @Test
    public void existentEmailCorrectPasswordTest(){
        logIn(email, password);
        myAccountContent = getDisplayedName();
        Assertions.assertTrue(myAccountContent.contains(userFullName),
                "My Account page does not contain correct name. Expected name: " + userFullName + " was not found in a string: " + myAccountContent);
    }
    @Test
    public void existentUsernameIncorrectPasswordTest(){
        logIn(userName, wrongPassword);
        errorMessageText  = getErrorMessage();
        expectedMessage = "B????d: Wprowadzone has??o dla u??ytkownika " + userName + " jest niepoprawne. Nie pami??tasz has??a?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }
    @Test
    public void nonexistentUsernameIncorrectPasswordTest(){
        logIn(nonexistentUserName, wrongPassword);
        errorMessageText  = getErrorMessage();
        expectedMessage = "Nieznany u??ytkownik. Prosz?? spr??bowa?? ponownie lub u??y?? adresu email.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }
    @Test
    public void existentEmailIncorrectPasswordTest(){
        logIn(email, wrongPassword);
        errorMessageText  = getErrorMessage();
        expectedMessage = "B????D: Dla adresu email " + email +" podano nieprawid??owe has??o. Nie pami??tasz has??a?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }
    @Test
    public void nonexistentEmailIncorrectPasswordTest(){
        logIn(nonexistentEmail, wrongPassword);
        errorMessageText  = getErrorMessage();
        expectedMessage = "Nieznany adres email. Prosz?? sprawdzi?? ponownie lub wypr??bowa?? swoj?? nazw?? u??ytkownika.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }
    @Test
    public void existentEmailNoPasswordTest(){
        logIn(email, "");
        errorMessageText  = getErrorMessage();
        expectedMessage = "B????d: Has??o jest puste.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }
    @Test
    public void noUsernameDummyPasswordTest(){
        logIn("", wrongPassword);
        errorMessageText  = getErrorMessage();
        expectedMessage = "B????d: Nazwa u??ytkownika jest wymagana.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }
    private void logIn(String userName, String password){
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("a.woocommerce-store-notice__dismiss-link")).click();
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }
    private String getDisplayedName(){
        return driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
    }
    private String getErrorMessage(){
        return driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
    }
}

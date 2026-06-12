package Tests;

import Utilities.BaseDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameter extends BaseDriver {

    @Test
    @Parameters({"adminEmail", "adminPassword"})
    public void loginTest(String email, String password) {
        Elements elements = new Elements(driver);
        elements.email.sendKeys(email);
        elements.password.sendKeys(password);
        elements.loginButton.click();
    }
}


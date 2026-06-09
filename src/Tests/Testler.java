package Tests;

import Utilities.BaseDriver;
import Utilities.MyFunc;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Testler extends BaseDriver {



    @Test(priority = 1)
    public void LoginTest() {
        Elements elements = new Elements(driver);
        elements.loginButton.click();
        MyFunc.bekle(5); // sayfanın yüklenmesi için bekle, yoksa hata veriyor
        bekle.until(ExpectedConditions.visibilityOf(elements.dashboard));
        Assert.assertEquals(elements.dashboard.getText().trim(), "Dashboard", "login başarısız.");
//        BekleKapat();
//        KalanOncekileriKapat();
    }

    @Test(dependsOnMethods = {"LoginTest"})
    public void LeftNawMenuTest() {
        Elements elements = new Elements(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement e : elements.anaMenuList) {
            e.click();
            WebElement menuAdi= e.findElement(By.xpath("a/p"));
            try {
                bekle.until(ExpectedConditions.attributeContains(e,"class","menu-open"));
            }
            catch (TimeoutException ex){
                Assert.fail(menuAdi.getText()+" menüsünü açma başarısız oldu.");
            }
            js.executeScript("arguments[0].scrollIntoView(true);", e);
            List<WebElement> li= e.findElements(By.xpath("ul/li"));
            Assert.assertTrue(!li.isEmpty(),menuAdi.getText()+" altında menü bulunamadı.");
        }
    }


    Faker randomUreteci = new Faker();
    String firstName = randomUreteci.address().firstName();
    String lastName = randomUreteci.address().lastName();
    String email = randomUreteci.internet().emailAddress();
    String password = randomUreteci.internet().password();

    @Test()
    public void CreateCustomerTest() {
        Elements elements = new Elements(driver);
        elements.loginButton.click();
        MyFunc.bekle(10);
        elements.customers.click();
        elements.customerList.click();
        elements.addNewButton.click();
        elements.email.sendKeys(email);//faker kullanmamız lazım
        elements.password.sendKeys(password);
        elements.firstName.sendKeys(firstName);
        elements.lastName.sendKeys(lastName);
        elements.genderFemale.click();
        elements.companyName.sendKeys("easyLearn");
        elements.saveButton.click();
        String gelenMesaj = elements.customerSucces.getText();
        bekle.until(ExpectedConditions.urlContains("https://admin-demo.nopcommerce.com/Admin/Customer/List"));
        bekle.until(ExpectedConditions.visibilityOf(elements.customerSucces));
        Assert.assertTrue(gelenMesaj.contains("The new customer has been added successfully."),
                "Müşteri ekleme mesajı hatalı! Gelen Mesaj: " + gelenMesaj);
    }


    @Test(dependsOnMethods = {"CreateCustomerTest"})
    public void EditCustomerTest() { // yiğit


    }

    @Test(dependsOnMethods = {"EditCustomerTest"})
    public void DeleteCustomerTest() { // burak


    }

    @Test(dependsOnMethods = {"DeleteCustomerTest"})
    public void SearchTest() { // sevgi


    }
}

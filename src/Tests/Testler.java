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
    public void LeftNawMenuTest() { // tuğçe
        Elements elements = new Elements(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement e : elements.anaMenuList) {
            e.click();
            WebElement menuAdi = e.findElement(By.xpath("a/p"));
            try {
                bekle.until(ExpectedConditions.attributeContains(e, "class", "menu-open"));
            } catch (TimeoutException ex) {
                Assert.fail(menuAdi.getText() + " menüsünü açma başarısız oldu.");
            }
            js.executeScript("arguments[0].scrollIntoView(true);", e);
            List<WebElement> li = e.findElements(By.xpath("ul/li"));
            Assert.assertTrue(!li.isEmpty(), menuAdi.getText() + " altında menü bulunamadı.");
        }
    }


    public static String firstName;
    public static String lastName;
    public static String email;
    public static String password;

    @Test()// depends on method ekle
    public void CreateCustomerTest() { // zeynep
        Faker randomUreteci = new Faker();
        firstName = randomUreteci.name().firstName(); // address yerine name daha doğru olur
        lastName = randomUreteci.name().lastName();
        email = randomUreteci.internet().emailAddress();
        password = randomUreteci.internet().password();

        Elements elements = new Elements(driver);
        elements.loginButton.click(); // sonrada sil
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
    public void EditCustomerTest() { // sevgi
        Elements elements = new Elements(driver);
//        elements.customers.click();
//        elements.customerMenu.click();   // createtestinden dan açık kalıyor sayfa
        elements.searchEmail.sendKeys(email);
        elements.searchFirstName.sendKeys(firstName);
        elements.searchLastName.sendKeys(lastName);
        elements.searchButton.click();
        Actions actions = new Actions(driver);
        actions.scrollToElement(elements.editBolumu).perform();
        MyFunc.bekle(5);

        WebElement dogrulanacakElement = getSearchDogrulama(email);
        bekle.until(ExpectedConditions.visibilityOf(dogrulanacakElement));
        Assert.assertTrue(dogrulanacakElement.isDisplayed(), "Faker ile üretilen email tabloda görünmüyor!");
        Assert.assertEquals(dogrulanacakElement.getText(), email, "Tablodaki metin üretilen email ile eşleşmiyor!");
        System.out.println("Başarıyla Doğrulanan Faker Email'i: " + email);

        WebElement editButonu = getEditButtonByEmail(email);
        bekle.until(ExpectedConditions.elementToBeClickable(editButonu));
        editButonu.click();
        elements.firstName.clear();
        elements.firstName.sendKeys("Anabelle");
        elements.saveEditButton.click();

        bekle.until(ExpectedConditions.urlContains("https://admin-demo.nopcommerce.com/Admin/Customer/List"));
        bekle.until(ExpectedConditions.visibilityOf(elements.customerSucces));
        String gelenMesaj = elements.customerSucces.getText();
        Assert.assertTrue(gelenMesaj.contains("The customer has been updated successfully."),
                "Müşteri güncelleme mesajı hatalı! Gelen Mesaj: " + gelenMesaj);

        System.out.println("Müşteri Başarıyla Güncellendi ve Doğrulandı!");
    }

    public WebElement getSearchDogrulama(String email) { // Faker doğrulama için yapıldı.
        String dinamikXpath = String.format("//table[@id='customers-grid']//td[text()='%s']", email);
        return driver.findElement(By.xpath(dinamikXpath));
    }

    public WebElement getEditButtonByEmail(String email) {
        String dinamikXpath = String.format
                ("//table[@id='customers-grid']//td[text()='%s']/following-sibling::td[@class='button-column']/a", email);
        return driver.findElement(By.xpath(dinamikXpath));
    }


    @Test(dependsOnMethods = {"EditCustomerTest"})
    public void DeleteCustomerTest() { // burak
        Elements elements = new Elements(driver);
        elements.loginButton.click(); // sonradan sil
        MyFunc.bekle(10);
        elements.customers.click();
        elements.customerList.click();
        elements.customerEdit.click();
        elements.deleteCustomer.click();
        elements.alertDeleteBtn.click();

        bekle.until(ExpectedConditions.urlContains("https://admin-demo.nopcommerce.com/Admin/Customer/List"));
        bekle.until(ExpectedConditions.visibilityOf(elements.deleteSuccess));

        String deleteMesaj = elements.deleteSuccess.getText();
        Assert.assertTrue(deleteMesaj.contains("The customer has been deleted successfully."),
                "Müşteri silme işlemi başarısız oldu!.. Geçmiş olsun...  Gelen Mesaj: " + deleteMesaj);


    }

    @Test(dependsOnMethods = {"DeleteCustomerTest"})
    public void SearchTest() { // yiğit


    }
}

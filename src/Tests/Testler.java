package Tests;

import Utilities.BaseDriver;
import Utilities.MyFunc;
import org.openqa.selenium.By;
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
        Actions actions = new Actions(driver);

        for (int i = 0; i < elements.anaMenuler.size(); i++) {
            WebElement menuBlock = elements.anaMenuler.get(i);

            // Görünmeyen, boyutu sıfır olan gizli elementleri pas geçiyoruz (Hata almayı engeller)
            if (!menuBlock.isDisplayed()) {
                continue;
            }

            WebElement menuLink = menuBlock.findElement(By.tagName("a"));
            String menuName = menuLink.getText().trim();

            // Eğer başlık boş kalmışsa veya Dashboard ise atla
            if (menuName.isEmpty() || menuName.equalsIgnoreCase("Dashboard")) {
                continue;
            }

            System.out.println("Kontrol Edilen Menü: " + menuName);

            // Hover ve Tıklama aksiyonu
            actions.moveToElement(menuLink).click().build().perform();

            Assert.assertTrue(menuLink.isDisplayed(), menuName + " görünür değil!");

            // Sadece o ana menüye ait alt elemanları listeler
            List<WebElement> altMenuler = menuBlock.findElements(By.cssSelector("ul.nav-treeview > li"));

            System.out.println(menuName + " altındaki eleman sayısı: " + altMenuler.size());
            for (WebElement sub : altMenuler) {
                String subText = sub.getText().trim();
                // Eğer alt eleman metni boş değilse yazdır
                if (!subText.isEmpty()) {
                    System.out.println("  -> Alt Eleman: " + subText);
                }
            }

            Assert.assertTrue(altMenuler.size() > 0, menuName + " altında eleman bulunamadı!");
            System.out.println(menuName + " başarıyla doğrulandı.\n------------------");
        }
        BekleKapat();
        KalanOncekileriKapat();
    }



    int rastgeleSayi = (int) (Math.random() * 100000);
    String yeniEmail = "zeynep" + rastgeleSayi + "@gmail.com";
    @Test(dependsOnMethods = {"LeftNawMenuTest"})

    public void CreateCustomer() {
        Elements elements = new Elements(driver);
        elements.loginButton.click();
        MyFunc.bekle(10);//wait.until kullanmamız lazım
        elements.customers.click();
        elements.customerList.click();
        elements.addNewButton.click();
        elements.email.sendKeys(yeniEmail);//faker kullanmamız lazım
        elements.password.sendKeys("123456");
        elements.firstName.sendKeys("ben");
        elements.lastName.sendKeys("test değilim");
        elements.genderFemale.click();
        elements.companyName.sendKeys("easyLearn");
        elements.saveButton.click();
        String gelenMesaj = elements.customerSucces.getText();
        bekle.until(ExpectedConditions.urlContains("https://admin-demo.nopcommerce.com/Admin/Customer/List"));
        bekle.until(ExpectedConditions.visibilityOf(elements.customerSucces));
        Assert.assertTrue(gelenMesaj.contains("The new customer has been added successfully."),
                "Müşteri ekleme mesajı hatalı! Gelen Mesaj: " + gelenMesaj);
    }


    @Test(dependsOnMethods = {"CreateCustomerTest"})//yiğit
    public void EditCustomerTest() {
Elements elements=new Elements(driver);
elements.customers.click();


    }

    @Test(dependsOnMethods = {"EditCustomerTest"})
    public void DeleteCustomerTest() {


    }

    @Test(dependsOnMethods = {"DeleteCustomerTest"})
    public void SearchTest() {


    }
}

package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.List;

public class BaseDriver {
    public  WebDriver driver;

    public  WebDriverWait bekle;

//    public WebDriver getDriver() {              // 2. yöntem
//        return driver;
//    }

    @BeforeClass
    public void Setup() {
//        switch (browserTipi) {
//            case "chrome":
//                driver = new ChromeDriver();
//                break;
//            case "firefox":
//                driver = new FirefoxDriver();
//                break;
//            case "edge":
//                driver = new EdgeDriver();
//                break;
//        }
        // @Parameters("browserTipi")


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options); // hoca bot için ekledi bu 3 bölümü

        driver.manage().deleteAllCookies();     // hoca sonradan ekledi
        driver.manage().window().maximize(); // Ekranı max yapıyor.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // 20 sn mühlet: sayfayı yükleme mühlet
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // 5 sn mühlet: elementi bulma mühleti
        driver.get("https://admin-demo.nopcommerce.com/login");
        bekle = new WebDriverWait(driver, Duration.ofSeconds(20));

        // LoginTesti();
    }

    // hafızada kalmış, Selenium açtığı boştaki tarayıcıları temizler
    public static void KalanOncekileriKapat() {
        try {  // aga komuta bak.. cmd den taskkill yapıyu Runtime.getRuntime().exec("taskkill /f /im Kalanononcekileri");
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (Exception ignored) {
        }
    }

    public void Consent() {
        // Consent buttonu bulurken list<> web elements olarak buluyoruz.. Çünkü tek elemanı bulamayabilir. Bulamazsa hata bverir..
        List<WebElement> ConsentButton = driver.findElements(By.xpath("//*[text()='Consent']"));
        if (ConsentButton.size() > 0)     // Consent ekranda gözüktüyse
            ConsentButton.get(0).click();

    }


    @AfterClass
    public void BekleKapat()        // STASTİC OLMAYACAK
    {
        MyFunc.bekle(3);
        driver.quit();
    }


}



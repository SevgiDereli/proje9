package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Elements {
    public Elements(WebDriver driver) {
        PageFactory.initElements(driver, this);         // paralel ve parametrik...
    }

    @FindBy(id = "Email")
    public WebElement email;
    @FindBy(id = "Password")
    public WebElement password;
    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;

    // sevgi
    @FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
    public WebElement dashboard;

    // Sadece en dıştaki ana menü bloklarını seçer (İçteki alt menülerin li'lerini listeye karıştırmaz)
    @FindBy(css = "ul[role='menu'] > li.nav-item.has-treeview")
    public List<WebElement> anaMenuler;





}

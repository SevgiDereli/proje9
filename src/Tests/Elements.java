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

    //toktay
    @FindBy(xpath = "//ul[@class='nav nav-pills nav-sidebar flex-column nav-legacy']/li[@class='nav-item has-treeview']")
    public List<WebElement> anaMenuList;

    //zeynep (Create Customer)
    @FindBy(linkText = "Customers") // xpath--> //p[contains(text(),'Customers')] [1]
    public WebElement customers;
    @FindBy(xpath = "/html/body/div[3]/aside/div/nav/ul/li[4]/ul/li[1]/a")
    public WebElement customerList;
    @FindBy(linkText = "Add new")
    public WebElement addNewButton;
    @FindBy(id = "FirstName")
    public WebElement firstName;
    @FindBy(id = "LastName")
    public WebElement lastName;
    @FindBy(id = "Gender_Female")
    public WebElement genderFemale;
    @FindBy(id = "Company")
    public WebElement companyName;
    @FindBy(css = "[class=float-right]>button")
    public WebElement saveButton;
    @FindBy(xpath = "//*[@id='admin-notifications']/div")
    public WebElement customerSucces;

    // Edit Customer (Sevgi)
    @FindBy(id = "SearchEmail")
    public WebElement searchEmail;
    @FindBy(id = "SearchFirstName")
    public WebElement searchFirstName;
    @FindBy(id = "SearchLastName")
    public WebElement searchLastName;
    @FindBy(id = "search-customers")
    public WebElement searchButton;
    @FindBy(name = "save")
    public WebElement saveEditButton;
    @FindBy(xpath = "//div[@class='documentation-reference']//span[1]")
    public WebElement editBolumu;

    // Delete Customer <-- BUrak -->
    @FindBy(xpath = "//tbody/tr[1]/td[7]/a[1]") public WebElement customerEdit;
    @FindBy(className = "fa-trash-can") public WebElement deleteCustomer;
    @FindBy(css = ".btn-danger:nth-child(2)") public WebElement alertDeleteBtn;
    @FindBy(xpath = "//*[@id='admin-notifications']/div") public WebElement deleteSuccess;


}


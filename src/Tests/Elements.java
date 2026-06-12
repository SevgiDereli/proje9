package Tests;

import Utilities.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Elements {
    public Elements(WebDriver driver) {
        PageFactory.initElements(driver, this); }

    // LoginTest
    @FindBy(id = "Email")
    public WebElement email;
    @FindBy(id = "Password")
    public WebElement password;
    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;
    @FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
    public WebElement dashboard;

    // LawMenuTest (toktay)
    @FindBy(xpath = "//ul[@class='nav nav-pills nav-sidebar flex-column nav-legacy']/li[@class='nav-item has-treeview']")
    public List<WebElement> anaMenuList;

    // CreateCustomerTest (zeynep)
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

    //search Test
    @FindBy(xpath = "//input[@type='text']") public WebElement searchBox;
    @FindBy(xpath = "//*[@id='search-box']/span/div") public WebElement searchBox2;
    @FindBy(xpath = "//h1[normalize-space()='Shipments']") public WebElement shipmentsTitle;


}


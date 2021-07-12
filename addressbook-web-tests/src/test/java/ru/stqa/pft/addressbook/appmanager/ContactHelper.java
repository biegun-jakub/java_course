package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }
    public void returnToHomePage() {
        driver.findElement(By.linkText("home page")).click();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initNewContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void submitDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeleteContact() {
        driver.switchTo().alert().accept();
    }

    public void initEditContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitEditContact() {
        click(By.name("update"));
    }
}
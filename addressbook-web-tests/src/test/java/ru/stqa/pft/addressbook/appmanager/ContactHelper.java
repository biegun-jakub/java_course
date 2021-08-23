package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    private boolean creation;

    public ContactHelper(WebDriver driver) {
        super(driver);
    }
    public void returnToHomePage() {
        driver.findElement(By.linkText("home page")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
        //attach(By.name("photo"), contactData.getPhoto());

       if (creation) {
           if (contactData.getGroups().size() > 0) {
               Assert.assertTrue(contactData.getGroups().size() == 1);
               new Select(driver.findElement(By.name("new_group")))
                       .selectByVisibleText(contactData.getGroups().iterator().next().getName());
           }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initNewContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    private Contacts contactCache = null;

    public void selectContactById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeleteContact() {
        driver.switchTo().alert().accept();
    }

    public void initEditContact(int index) {
       driver.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitEditContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean creation) {
        initNewContactCreation();
        fillContactForm(contact, creation);
        submitContactForm();
        contactCache = null;
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() { return driver.findElements(By.name("selected[]")).size(); }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String allPhones = cells.get(5).getText();
            String allAddresses = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAllAddresses(allAddresses));
        }
        return contactCache;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        submitDeleteContact();
        contactCache = null;
        confirmDeleteContact();
    }

    public void modify(ContactData contact) {
        fillContactForm(contact, false);
        submitEditContact();
        contactCache = null;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String address2 = driver.findElement(By.name("address2")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email)
                .withEmail2(email2).withEmail3(email3).withAddress(address).withAddress2(address2);
    }

    public ContactData infoFromDetailsPage(ContactData contact) {
        initContactDetailsById(contact.getId());
        String name = driver.findElement(By.xpath("//div[@id='content']/b")).getAttribute("value");
        String address = driver.findElement(By.xpath("//div[@id='content']/text()[2]")).getText();
        String homePhone = driver.findElement(By.xpath("//div[@id='content']/text()[4]")).getText();
        String mobilePhone = driver.findElement(By.xpath("//div[@id='content']/text()[5]")).getText();
        String workPhone = driver.findElement(By.xpath("//div[@id='content']/text()[6]")).getText();
        String email = driver.findElement(By.xpath("//div[@id='content']/a[1]")).getAttribute("value");
        String email2 = driver.findElement(By.xpath("//div[@id='content']/a[2]")).getAttribute("value");
        String email3 = driver.findElement(By.xpath("//div[@id='content']/a[3]")).getAttribute("value");
        String address2 = driver.findElement(By.xpath("//*[@id='content']/text()[10]")).getText();

        return new ContactData().withId(contact.getId()).withName(name)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withEmail(email)
                .withEmail2(email2).withEmail3(email3).withAddress(address).withAddress2(address2);
    }

    private void initContactDetailsById(int id) {
        WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(6).findElement(By.tagName("a")).click();
    }

    public void initContactModificationById(int id) {
        WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void addContactToGroup(String group) {
        WebElement groupList = driver.findElement(By.name("to_group"));
        WebElement addButton = driver.findElement(By.name("add"));
        groupList.click();
        new Select(groupList).selectByVisibleText(group);
        addButton.click();
    }

    public void removeFromGroup(int id) {
        selectContactById(id);
        WebElement removeButton = driver.findElement(By.name("remove"));
        removeButton.click();
    }
}

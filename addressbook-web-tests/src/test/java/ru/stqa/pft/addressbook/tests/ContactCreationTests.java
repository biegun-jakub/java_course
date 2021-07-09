package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initNewContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Test", "Test Street", "test@test.test", "Tester", "111222333"));
        app.getContactHelper().submitContactForm();
        app.getContactHelper().returnToHomePage();
    }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initEditContact();
        app.getContactHelper().fillContactForm(new ContactData("Jakub", "Test Street 213",
                "testmail@test", "Test", "123456789", null), false);
        app.getContactHelper().submitEditContact();
    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Test", "Test Street",
                    "test@test.test", "Tester", "111222333", "test1"),
                    true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().submitDeleteContact();
        app.getContactHelper().confirmDeleteContact();
    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Jakub").withAddress("Test Street 213")
                    .withEmail("testmail@test").withLastName("Test").withPhoneNumber("123456789")
                    .withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification() {

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.contact().initContactModificationById(modifiedContact.getId());
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Jakub")
                .withAddress("Test Street 213").withEmail("testmail@test")
                .withLastName("Test").withPhoneNumber("123456789");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}

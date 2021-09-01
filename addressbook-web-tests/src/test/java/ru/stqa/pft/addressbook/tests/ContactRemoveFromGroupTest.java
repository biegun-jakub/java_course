package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactRemoveFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Jakub").withAddress("Test Street 213")
                    .withEmail("testmail@test").withLastName("Test").withPhoneNumber("123456789"), true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        if (app.db().chosenGroupForRemovingContact() == null) {
            app.contact().create(new ContactData().withFirstName("Jakub").withLastName("Test1234").withHomePhone("001")
                    .withEmail("test@test.test").withGroup(app.db().groups().iterator().next()), true);
        }
        Contacts beforeContacts = app.db().chosenGroupForRemovingContact().getContacts();
        int chosenGroupForRemovingContactId = app.db().chosenGroupForRemovingContact().getId();
        app.goTo().homePage();
        app.contact().filterByGroup(chosenGroupForRemovingContactId);
        ContactData selectedContact = beforeContacts.iterator().next();
        app.contact().selectContactById(selectedContact.getId());
        app.contact().removeFromGroup();
        app.goTo().homePage();
        Contacts afterContacts = app.db().getGroup(chosenGroupForRemovingContactId).getContacts();
        assertThat(afterContacts, equalTo(beforeContacts.without(selectedContact)));
    }
}

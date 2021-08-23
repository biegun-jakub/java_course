package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactRemoveFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();

        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Jakub").withAddress("Test Street 213")
                    .withEmail("testmail@test").withLastName("Test").withPhoneNumber("123456789"), true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (group.getContacts().size() < 1) {
            app.contact().selectContactById(contact.getId());
            app.contact().addContactToGroup(group.getName());
            app.goTo().homePage();
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        assertTrue(group.getContacts().size() > 0);
        ContactData contactToRemove = group.getContacts().iterator().next();
        app.group().filterGroup(group.getName());
        app.contact().removeFromGroup(contactToRemove.getId());
        app.goTo().homePage();
        Groups after = contactToRemove.getGroups();
        assertFalse(after.contains(group));
    }
}

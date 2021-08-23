package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

public class ContactAddToGroupTests extends TestBase {

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
    public void testAddingContactToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData modifiedContact = contacts.iterator().next();
        GroupData group = groups.iterator().next();
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().addContactToGroup(group.getName());
        app.goTo().homePage();
        Groups after = modifiedContact.getGroups();
        assertTrue(after.contains(group));
    }
}

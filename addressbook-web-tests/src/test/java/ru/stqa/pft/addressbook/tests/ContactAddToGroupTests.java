package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
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
        GroupData chosenGroup;
        Contacts contactsBefore;
        Contacts beforeContacts = app.db().contacts();
        Groups group = app.db().groups();
        ContactData selectedContact = beforeContacts.iterator().next();
        chosenGroup = app.db().chosenGroup(group, selectedContact);
        if (chosenGroup == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("new_group"));
            chosenGroup = app.db().getGroupsFromContactWithMaxId();
            contactsBefore = app.db().getGroup(chosenGroup.getId()).getContacts();
            app.goTo().homePage();
        } else {
            contactsBefore = app.db().getGroup(chosenGroup.getId()).getContacts();
        }
        app.contact().selectContactById(selectedContact.getId());
        app.contact().addContactToGroup(chosenGroup);
        Contacts contactsAfter = app.db().getGroup(chosenGroup.getId()).getContacts();
        assertThat(contactsAfter, equalTo(contactsBefore.withAdded(selectedContact)));
    }
}

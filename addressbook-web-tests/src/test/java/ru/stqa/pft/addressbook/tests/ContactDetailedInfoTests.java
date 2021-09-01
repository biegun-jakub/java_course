package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDetailedInfoTests extends TestBase {

    public void testDetailsPage() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);
        app.goTo().homePage();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    }
}

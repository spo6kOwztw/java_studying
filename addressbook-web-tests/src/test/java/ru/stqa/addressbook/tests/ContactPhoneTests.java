package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhone() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInformationForm = app.contact().infoFormEditForm(contact);
    }
}

package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() throws Exception {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("william", null, "burroughs", "+79999999999", "junkie@beat.com", "New1"), true);
        app.getContactHelper().submitContactCreation();
    }

}

package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdit() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("william", "seward", "burroughs", "+79999999999", "junkie@beat.com", "New1"));
        }
        app.getContactHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("billy", "", "b", "+79999999990", "junkie@beat.ru", "New1"), false);
        app.getContactHelper().submitContactEdit();
    }
}

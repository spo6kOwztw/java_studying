package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdit() {
        app.getContactHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("billy", "", "b", "+79999999990", "junkie@beat.ru"));
        app.getContactHelper().submitContactEdit();
    }
}

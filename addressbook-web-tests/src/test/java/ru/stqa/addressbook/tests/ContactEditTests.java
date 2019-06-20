package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import java.util.List;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdit() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("william", "seward", "burroughs", "+79999999999", "junkie@beat.com", "New1"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().initContactEdit();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().fillContactForm(new ContactData("billy", "", "b", "+79999999990", "junkie@beat.ru", "New1"), false);
        app.getContactHelper().submitContactEdit();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}

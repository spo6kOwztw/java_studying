package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdit() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("william", "seward", "burroughs", "+79999999999", "junkie@beat.com", "New1"));
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactEdit();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "billy", "", "b", "+79999999990", "junkie@beat.ru", "New1");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactEdit();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}

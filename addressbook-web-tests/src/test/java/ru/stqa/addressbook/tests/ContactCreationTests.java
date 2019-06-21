package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Collections.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("william", null, "burroughs", "+79999999999", "junkie@beat.com", "New1");
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);

        before.add (contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }
}
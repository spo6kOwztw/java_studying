package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstName("william").withLastName("Burroughs"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactEdit() {
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstName("billy").withLastName("b").withMiddleName("s");
        app.contact().edit(before, contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}

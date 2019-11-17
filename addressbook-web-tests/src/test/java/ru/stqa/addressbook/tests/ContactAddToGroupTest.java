package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {
    private ContactData contactToAdd;
    private GroupData groupToAdd;
    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData()
                        .withName("new1")
                        .withHeader("test")
                        .withFooter("test"));
                app.goTo().homePage();
            }
        }
    }
    @Test

    public void testContactAddToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = new Groups();
        for (ContactData contact : contacts) {
            groups = app.db().groups();
            int groupAmount = groups.size();
            if (contact.getGroups().size() < groupAmount) {
                contactToAdd = contact;
                break;}}
        Groups contactGroups = contactToAdd.getGroups();
        for (GroupData group : groups) {
            { if (!contactGroups.contains(group)) {
                groupToAdd = group;
                break;
            }}}
        app.goTo().homePage();
        app.contact().addContactToGroup(groupToAdd, contactToAdd);
        assertThat(contactToAdd.getGroups().withAdded(groupToAdd), equalTo(app.db().contactId(contactToAdd.getId()).getGroups()));}}
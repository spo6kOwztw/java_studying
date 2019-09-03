package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

    @BeforeMethod
    //   1. Для всех тестов: проверки предусловий наличия групп и контактов.
    public void ensurePreconditions() {


        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("new1"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData()
                    .withFirstName("william")
                    .withLastName("Burroughs")
                    .withMiddleName("X")
                    .withMobilePhone("0")
                    .withEmail1("0"));
                    }
        app.goTo().homePage();
    }

    // 2. Для первого теста надо проверять предусловие, что существуют контакты, которые можно добавить в группу (и создавать новую группу, если предусловие не выполняется).
    @Test
    public void testContactAddToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contact = contacts.iterator().next();
            int contactId = contact.getId();
            Groups contactGroupsBefore = contact.groups();
            while (groups.size() == 0){
                contact = contacts.iterator().next();
        }
        GroupData group = groups.stream().iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(contact, group);
        Contacts after = app.db().contacts();
        ContactData updatedContact = after.stream().filter(data -> Objects.equals(data.getId(), contactId)).findFirst().get();
        Groups newContactGroups = updatedContact.groups();
        assertThat(newContactGroups, equalTo(contactGroupsBefore.withAdded(group)));
    }

}
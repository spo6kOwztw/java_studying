package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

public class ContactRemovingFromGroupTest extends TestBase{

    @BeforeMethod
    //   1. Для всех тестов: проверки предусловий наличия групп и контактов.

    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("name"));
                app.goTo().homePage();
            }
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Bill").withLastName("B").withGroup(groups.iterator().next()));
        }
    }

    // 3.1 Для второго теста надо проверять предусловие, что существуют контакты, которые можно удалить из группы
    // 3.2 (и добавлять контакт в группу если предусловие не выполняется). (?)

    @Test
    public void testRemoveContactFromGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contact = contacts.iterator().next();
        GroupData group = groups.stream().iterator().next();
        int contactId = contact.getId();
        Groups contactGroupsBefore = contact.groups();

        if (contact.groups().size() == 0) {
            app.contact().selectContactById(contactId);
            app.contact().addContactToGroup(contact, group);
            app.goTo().homePage();
            Contacts after = app.db().contacts();
            contact = after.iterator().next().withId(contactId);
            contactGroupsBefore = contact.groups();

        }

        GroupData removedGroup = contactGroupsBefore.iterator().next();

        app.contact().removeContactFromGroup(contact,removedGroup);
        app.goTo().homePage();

        //5. Перед сравнением списков необходимо получать актуальную информацию о группах этого контакта из базы данных.???

    }
}

package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends BaseHelper {


    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contact, boolean creation) {
        type(By.name("firstname"), contact.getFirstName());
        type(By.name("lastname"), contact.getLastName());
        type(By.name("address"), contact.getAddress());
        type(By.name("home"), contact.getHomePhone());
        type(By.name("mobile"), contact.getMobilePhone());
        type(By.name("work"), contact.getWorkPhone());
        type(By.name("email"), contact.getEmail1());
        type(By.name("email2"), contact.getEmail2());
        type(By.name("email3"), contact.getEmail3());
        //attach(By.name("photo"), contact.getPhoto());

        if (creation) {
            if (contact.groups().size() > 0) {
                Assert.assertTrue(contact.groups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.groups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int id) {
        wd.findElement(By.id(Integer.toString(id))).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactDeletion() {

        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void initContactEdit(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactEdit() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstname)
                    .withLastName(lastname)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails)
                    .withAddress(address));

        }
        return contacts;
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        contactCache = null;
        submitContactCreation();
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        submitContactDeletion();
    }

    public void edit(ContactData contact) {
        initContactEdit(contact.getId());
        fillContactForm(contact, false);
        contactCache = null;
        submitContactEdit();
    }

    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

<<<<<<< Updated upstream
    public void addContactToGroup(ContactData contact) {
        //? нужен выбор конкретной группы
        selectContactById(contact.getId());
        click(By.name("add"));
    }

    public void removeContactFromGroup(ContactData removedContact, GroupData removedGroup) {
        selectGroupInFilter(removedGroup);
        selectContactById(removedContact.getId());
        wd.findElement(By.name("remove"));
=======
    public void addContactToGroup(ContactData contact, GroupData groupForAdd) {
        selectContactById(contact.getId());
        selectGroup(groupForAdd);
        click(By.name("add"));
    }

    public void initContactRemovingFromGroup() {
        click(By.name("remove"));
    }
    public void removeContactFromGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        filterByGroup(group);
        initContactRemovingFromGroup();
>>>>>>> Stashed changes
    }

    public void selectGroupInFilter(GroupData group) {
        wd.findElement(By.name("group")).click();
        wd.findElement(By.xpath(".//select[@name='group']/option[@value='"+ group.getId() +"']")).click();
    }

<<<<<<< Updated upstream

    public void selectGroup(int id) {
        wd.findElement(By.name("to_group")).click();
        wd.findElement(By.xpath(".//select[@name='to_group']/option[@value='"+ id +"']")).click();
    }

=======
    public void selectGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    }

    public void filterByGroup(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }
>>>>>>> Stashed changes

            public ContactData infoFormEditForm (ContactData contact){
            initContactEditById(contact.getId());
            String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
            String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
            String home = wd.findElement(By.name("home")).getAttribute("value");
            String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
            String work = wd.findElement(By.name("work")).getAttribute("value");
            String email1 = wd.findElement(By.name("email")).getAttribute("value");
            String email2 = wd.findElement(By.name("email2")).getAttribute("value");
            String email3 = wd.findElement(By.name("email3")).getAttribute("value");
            String address = wd.findElement(By.name("address")).getAttribute("value");
            wd.navigate().back();
            return new ContactData()
                    .withId(contact.getId())
                    .withFirstName(firstname)
                    .withLastName(lastname)
                    .withMobilePhone(mobile)
                    .withHomePhone(home)
                    .withWorkPhone(work)
                    .withEmail1(email1)
                    .withEmail2(email2)
                    .withEmail3(email3)
                    .withAddress(address);
        }

        private void initContactEditById (int id){
            WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']", id)));
            WebElement row = checkbox.findElement(By.xpath("./../.."));
            List<WebElement> cells = row.findElements(By.tagName("td"));
            cells.get(7).findElement(By.tagName("a")).click();

        }

}
package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.addressbook.model.ContactData;

public class ContactHelper  extends BaseHelper {


    public ContactHelper(WebDriver wd){
        super(wd);
    }
    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
    }
    public void initContactCreation() {click(By.linkText("add new"));}
    public void selectContact() {click(By.id("2"));}
    public void deleteSelectedContact() {click(By.xpath("//input[@value='Delete']"));}
    public void submitContactDeletion() {wd.switchTo().alert().accept();}
    public void initContactEdit() {click(By.xpath("//img[@alt='Edit']"));}
    public void submitContactEdit() {click(By.name("update"));}





}

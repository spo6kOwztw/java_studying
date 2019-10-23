package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends BaseHelper {

    public LoginHelper(ApplicationManager app) {
        super (app);
        wd = app.getDriver();

    }

    public void user(String username) {
        type(By.name("username"), username);
    }

    public void password(String password) {
        type(By.name("password"), password);
    }

    public void submitLogin() {
        wd.findElement(By.xpath("//input[@type='submit']")).click();
    }

}
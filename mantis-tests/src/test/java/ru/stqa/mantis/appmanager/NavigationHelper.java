package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void loginPage() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }
    public void mainPage() {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }

    public void managePage() {
        click(By.xpath("//a[contains(text(),'Manage')]"));
    }

    public void manageUsers() {
        click(By.xpath("//a[contains(text(),'Manage Users')]"));
    }


    public void userManager() {
        managePage();
        manageUsers();
    }

    public void logout() {
        click(By.xpath("//a[contains(text(),'Logout')]"));
    }

}
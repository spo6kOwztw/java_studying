package ru.stqa.mantis.appmanager;
import org.openqa.selenium.By;

public class UserHelper extends BaseHelper {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void initModificationById(int id) {
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));

    }

    public void changePassword(String resetPasswordLink, String password) {
        wd.get(resetPasswordLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase{
    private ApplicationManager app;

    public AdminHelper(ApplicationManager app) {
        super(app);
        this.app = app;
        app.getDriver();
    }

    public void login(String username, String password) {
        driver.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void goToUserMgmt() {
        click(By.xpath("//a[@href='/mantisbt-1.2.20/manage_user_page.php']"));
    }

    public void selectUser() {
        click(By.xpath("//tr[@class='row-1']/td/a"));
    }

    public void sendResetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void resetPassword(String resetLink, String newPassword) {
        driver.get(resetLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }

    public String getUser() {
        return driver.findElement(By.name("username")).getAttribute("value");
    }
}

package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GistPage;
import pages.LoginPage;
import utils.ConfigReader;

public class GistTest extends BaseTest {

    @Test
    public void testFullGistCRUD() {
        // Config
        String email = ConfigReader.getProperty("email");
        String username = ConfigReader.getProperty("username");
        String pass = ConfigReader.getProperty("password");
        String desc = ConfigReader.getProperty("gistDescription");
        String filename = ConfigReader.getProperty("gistFileName");
        String initContent = ConfigReader.getProperty("gistContentInitial");
        String updatedContent = ConfigReader.getProperty("gistContentUpdated");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, pass);
        driver.get(ConfigReader.getProperty("gistUrl"));

        // Create
        GistPage gistPage = new GistPage(driver);
        gistPage.createGist(desc, filename, initContent);
        wait.until(ExpectedConditions.urlContains(username));
        Assert.assertTrue(driver.getCurrentUrl().contains(username), "URL tidak mengandung username setelah Create!");
        System.out.println("Create Berhasil. URL: " + driver.getCurrentUrl());

        // Update
        gistPage.clickEdit();
        gistPage.updateGist(desc + " Updated", updatedContent);
        Assert.assertTrue(driver.getPageSource().contains(updatedContent), "Konten Gist tidak terupdate!");
        System.out.println("Update Berhasil.");

        // Delete
        gistPage.deleteGist();
        wait.until(ExpectedConditions.urlToBe("https://gist.github.com/" + username));
        Assert.assertEquals(driver.getCurrentUrl(), "https://gist.github.com/" + username);
        System.out.println("Delete Berhasil. Kembali ke halaman list Gist milik " + username);
    }
}
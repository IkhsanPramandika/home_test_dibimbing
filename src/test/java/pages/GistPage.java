package pages;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GistPage extends BasePage {

    @FindBy(name = "gist[description]")
    private WebElement descriptionField;

    @FindBy(name = "gist[contents][][name]")
    private WebElement filenameField;

    @FindBy(xpath = "//textarea[@aria-label='Enter file contents here']")
    private WebElement contentTextArea;

    @FindBy(css = "button.hx_create-pr-button")
    private WebElement createButton;

    @FindBy(css = "a.Button--secondary[href*='/edit']")
    private WebElement editButton;

    @FindBy(xpath = "//button[contains(text(),'Update')]")
    private WebElement updateButton;

    @FindBy(css = "button.Button--danger")
    private WebElement deleteButton;

    public GistPage(WebDriver driver) {
        super(driver);
    }

    public void createGist(String desc, String file, String content) {
        waitForElementToBeVisible(descriptionField);
        descriptionField.sendKeys(desc);
        filenameField.sendKeys(file);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", contentTextArea, content);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", contentTextArea);

        createButton.click();
    }

    public void clickEdit() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();
    }

    public void updateGist(String newDesc, String newContent) {
        waitForElementToBeVisible(descriptionField);
        descriptionField.clear();
        descriptionField.sendKeys(newDesc);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", contentTextArea, newContent);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", contentTextArea);

        updateButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/edit")));
    }

    public void deleteGist() {
        waitForElementToBeVisible(deleteButton);
        deleteButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
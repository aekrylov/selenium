package com.github.aekrylov.itis.sem05.selenium;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 10/30/17 9:37 AM
 */
public class TestBase {

    protected WebDriver driver;
    protected String baseUrl;

    protected long now;

    private static AccountData account = new AccountData("test879454", "ajsdbd7aolad09");

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","/home/anth/prog/lib/selenium/geckodriver"); // TODO

        driver = new FirefoxDriver();
        baseUrl = "https://gist.github.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        now = System.currentTimeMillis();
    }

    protected void login() {
        driver.get(baseUrl);

        driver.findElement(By.xpath("//a[contains(text(), 'Sign in')]")).click();

        wainUntil(10, ExpectedConditions.titleContains("Sign in"));

        driver.switchTo().activeElement().sendKeys(account.getUsername() + Keys.TAB);
        driver.switchTo().activeElement().sendKeys(account.getPassword());
        driver.switchTo().activeElement().submit();

        wainUntil(10, ExpectedConditions.titleContains("Create a new Gist"));
    }

    protected void wainUntil(int seconds, Function<? super WebDriver, ?> conditions) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(conditions);
    }

    protected void fillGistFile(Gist.GistFile file, WebElement el) {
        WebElement elFileName = el.findElement(By.xpath(".//input[contains(@placeholder, 'Filename')]"));

        if(file.filename != null)
            elFileName.sendKeys(file.filename);

        el.findElement(By.cssSelector(".commit-create pre")).click();
        driver.switchTo().activeElement().sendKeys(file.fileContents);
    }

}

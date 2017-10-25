package com.github.aekrylov.itis.sem05.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 10/24/17 10:11 PM
 */
public class Authorization {

    private WebDriver driver;
    private String baseUrl;

    private static final String USERNAME = "test879454";
    private static final String PASSWORD = "ajsdbd7aolad09";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","/home/anth/prog/lib/selenium/geckodriver"); // TODO

        driver = new FirefoxDriver();
        baseUrl = "https://gist.github.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin() {
        login();
    }

    @Test
    public void testPublishSimple() {
        login();
        
        long now = System.currentTimeMillis();

        String gistDesc = "Test gist " + now;
        String gistContents = "Lorem ipsum " + now;

        //write description
        driver.findElement(By.xpath("//input[contains(@placeholder, 'description')]")).sendKeys(gistDesc);

        //write contents
        driver.findElement(By.cssSelector(".commit-create pre")).click();
        driver.switchTo().activeElement().sendKeys(gistContents);

        //hit create button
        driver.findElement(By.xpath("//*[contains(text(), 'Create secret gist')]")).click();

        assertEquals(gistDesc, driver.findElement(By.cssSelector(".gist-content .Details")).getText().trim());
        assertEquals(gistContents, driver.findElement(By.cssSelector(".data")).getText().trim());
    }

    private void login() {
        driver.get(baseUrl);

        driver.findElement(By.xpath("//a[contains(text(), 'Sign in')]")).click();

        wainUntil(10, ExpectedConditions.titleContains("Sign in"));

        driver.switchTo().activeElement().sendKeys(USERNAME + Keys.TAB);
        driver.switchTo().activeElement().sendKeys(PASSWORD);
        driver.switchTo().activeElement().submit();

        wainUntil(10, ExpectedConditions.titleContains("Create a new Gist"));
    }

    private void wainUntil(int seconds, Function<? super WebDriver, ?> conditions) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(conditions);
    }

}

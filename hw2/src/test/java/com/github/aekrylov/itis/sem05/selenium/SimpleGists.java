package com.github.aekrylov.itis.sem05.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 10/31/17 10:52 PM
 */
public class SimpleGists extends TestBase {

    @Test
    public void basicGist() {
        Gist gist = new Gist("Test gist " + now, Collections.singletonList(new Gist.GistFile(null, "Lorem ipsum " + now)));

        login();

        driver.findElement(By.xpath("//input[contains(@placeholder, 'description')]")).sendKeys(gist.getDescription());

        //write contents
        fillGistFile(gist.files.get(0), driver.findElement(By.cssSelector(".file.js-code-editor")));
        
        //hit create button
        driver.findElement(By.xpath("//*[contains(text(), 'Create secret gist')]")).click();

        assertEquals(gist.getDescription(), driver.findElement(By.cssSelector(".gist-content .Details")).getText().trim());
        assertEquals(gist.getFiles().get(0).fileContents, driver.findElement(By.cssSelector(".data")).getText().trim());
    }

    @Test
    public void gistWithMultipleFiles() {
        login();

        Gist gist = new Gist();
        gist.description = "Test gist " + now;

        gist.files.add(new Gist.GistFile("file1.txt", "File1 contents " + now));
        gist.files.add(new Gist.GistFile("file2.txt", "File2 contents " + now));

        driver.findElement(By.xpath("//button[contains(text(), 'Add file')]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector(".file.js-code-editor"));

        fillGistFile(gist.files.get(0), elements.get(0));
        fillGistFile(gist.files.get(1), elements.get(1));

        //hit create button
        driver.findElement(By.xpath("//*[contains(text(), 'Create secret gist')]")).click();
    }
}

package pl.kwisniewski.integration_tests;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*; 

import pl.kwisniewski.test.abstr.AbstractDBUnitTestIntg;


public class PageTest extends AbstractDBUnitTestIntg{
	
	@Test 
	public void run() throws Exception {
		
		createTransaction();
		executeDataFile("dbunit/input.xml");
		closeTransaction();
		
		WebDriver driver;
		Wait wait;
		String text;
		String rowId = null;
		Integer size = null;
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 120);
		
        driver.get("http://localhost:8080/HelloWorldDbIntegrationTests");
        
        
        // Step 1
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Table"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Table", text);        
        String title = driver.getTitle();
        Assert.assertEquals("Hello", title);
        // actions
        driver.findElement(By.id("create")).click();
        
        
        // Step 2
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Hello"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Hello", text);
        // actions
        driver.findElement(By.id("userName")).sendKeys("Chris");
        driver.findElement(By.id("submit")).click();
        
        
        // Step 3
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Welcome"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Welcome", text);
        text = driver.findElement(By.cssSelector("tr > td > b")).getText();
        Assert.assertEquals("Chris", text);
        // actions
        driver.findElement(By.id("submit")).click();
        
        
        // Step 4
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Hello"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Hello", text);
        // actions
        driver.findElement(By.id("back")).click();
        
        
        // Step 5
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Table"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Table", text);
        size = getActionTableSize(driver);
        Assert.assertEquals((Integer)4, size);
        //Get row id
        rowId = getRowId(driver);       
        text = driver.findElement(By.id(rowId + "_name")).getText();
        Assert.assertEquals("Chris", text);
        // actions
        driver.findElement(By.id(rowId + "_edit")).findElement(By.partialLinkText("Edit")).click();
        
        
        // Step 6
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Edit"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Edit", text);
        // actions
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys("Jacek");
        driver.findElement(By.id("submit")).click();
        
        
        // Step 7
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Table"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Table", text);
        text = driver.findElement(By.id(rowId + "_name")).getText();
        Assert.assertEquals("Jacek", text);
        // actions
        driver.findElement(By.id(rowId + "_delete")).findElement(By.partialLinkText("Delete")).click();
        
        
        // Step 8
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Delete"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Delete", text);
        // actions
        driver.findElement(By.id("submit")).click();
        
        
        // Step 9
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("site_title"), "Table"));
        // conditions
        text = driver.findElement(By.id("site_title")).getText();
        Assert.assertEquals("Table", text);
        size = getActionTableSize(driver);
        Assert.assertEquals((Integer)3, size);

	} 
	
	private String getRowId(WebDriver driver){
		
		String rowId = null;
		
		WebElement table = driver.findElement(By.id("action_table"));
        WebElement tbody = table.findElement(By.tagName("tbody"));
        List<WebElement> allRows = tbody.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
        	rowId = row.getAttribute("id");
        	if(!"row_1".equals(rowId) && !"row_2".equals(rowId) && !"row_3".equals(rowId)){
        		break;
        	}
        }
        
        return rowId;
		
	}
	
	private Integer getActionTableSize(WebDriver driver){
		
		Integer size = 0;
		
		WebElement table = driver.findElement(By.id("action_table"));
        WebElement tbody = table.findElement(By.tagName("tbody"));
        List<WebElement> allRows = tbody.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
        	size++;
        }
        
        return size;
		
	}
	

}

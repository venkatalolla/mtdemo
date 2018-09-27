package org.jboss.as.quickstarts.tasksrs.service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {

  private WebDriver driver;
  private static final String CHROME_PATH = "/Users/suryalolla/Documents/GitHub/openshift-tasks/chromedriver";
  private static ChromeDriverService service;

  //@BeforeClass
  //public void beforeClass() {
      //driver = new FirefoxDriver();  
  //}
  
  @BeforeClass
	public static void createAndStartService() throws IOException
	{
		service = new ChromeDriverService.Builder().usingDriverExecutable(new File(CHROME_PATH)).usingAnyFreePort().build();
		service.start();
	}

	@BeforeClass
	public void testSetUp()
	{
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(service, chromeOptions);
	}

  @AfterClass
  public void afterClass() {
      driver.quit();
  }

  @Test
  public void verifySearchButton() {

      //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      driver.get("http://www.google.com");

      String search_text = "Google Search";
      WebElement search_button = driver.findElement(By.name("btnK"));

      String text = search_button.getAttribute("value");

      Assert.assertEquals(text, search_text, "Text not found!");
  }
}


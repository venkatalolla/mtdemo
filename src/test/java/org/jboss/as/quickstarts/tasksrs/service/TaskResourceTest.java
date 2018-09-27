package org.jboss.as.quickstarts.tasksrs.service;

import java.io.File;
import java.io.IOException;

import org.jboss.as.quickstarts.tasksrs.category.UnitTest;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {

	private static final String CHROME_PATH = "/usr/bin/chromedriver";
	//private static final String CHROME_PATH = "/Users/suryalolla/Documents/GitHub/openshift-tasks/chromedriver";
	private static ChromeDriverService service;
	private WebDriver driver;
	String appURL = "http://tasks-tasks-dev.oseapps.levvel-labs.io/";

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

	@Test
	public void verifyOpenShiftTasksPageTittle()
	{
		driver.navigate().to(appURL);
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "OpenShift Demo Tasks");
	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}

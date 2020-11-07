package com;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	public WebDriver driver;
	public String result1, result2, Testcase1, Testcase2, Testcase3, Testcase4, result = "Pass";
	public HashMap<String, String> hm1 = new HashMap<String, String>();
	public HashMap<String, String> hm2 = new HashMap<String, String>();

	public WebDriver browserInitialize() {
		System.setProperty("webdriver.chromedriver.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.grootan.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	public void screenshot1(WebDriver driver, String TempVar) throws IOException {
		File Src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\TestOneFolder\\" + TempVar + ".png");
		FileUtils.copyFile(Src, dest);
	}

	public void screenshot2(WebDriver driver, String TempVar) throws IOException {
		File Src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Src, new File(".\\TestTwoFolder\\" + TempVar + ".png"));
	}

}

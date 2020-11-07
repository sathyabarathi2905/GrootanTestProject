package com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObjectRepository {

	public WebDriver driver;
	public ObjectRepository(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By sections= By.tagName("a");
	public List<WebElement> section()
	{
		return driver.findElements(sections);
	}
	
	By Team= By.xpath("//a[contains(text(),'Team')]");
	public WebElement teamSection()
	{
		return driver.findElement(Team);
	}
	
	By TotalSections= By.xpath("//a[@class='st-root-link nav-link']");
	public List<WebElement> totalSections()
	{
		return driver.findElements(TotalSections);
	}
	
	By Juniorsname= By.xpath("//h5[contains(text(),'Junior Engineer')]//preceding-sibling::h3");
	public List<WebElement> Juniors_name()
	{
		return driver.findElements(Juniorsname);
	}
	
	By CTOxpath= By.xpath("//*[@id=\"root\"]/div/section[2]/div/div/div/div/div/div[1]/div[1]/img");
	public WebElement imageOfCTO()
	{
		return driver.findElement(CTOxpath);
	}
	
	By HRManagerXpath= By.xpath("//*[@id=\"root\"]/div/section[2]/div/div/div/div/div/div[1]/div[2]/img");
	public WebElement imageOfHRManager()
	{
		return driver.findElement(HRManagerXpath);
	}
	
}

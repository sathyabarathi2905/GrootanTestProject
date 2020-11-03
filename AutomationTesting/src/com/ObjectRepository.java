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
	public WebElement allSections(int i)
	{
		By all_Sections= By.xpath("//body/div[@id='root']/div[1]/nav[1]/div[1]/ul[1]/li[2]/a["+i+"]");
		return driver.findElement(all_Sections);
	}
	
	By Blog= By.xpath("//a[contains(text(),'Blog')]");
	public WebElement Button4()
	{
		return driver.findElement(Blog);
	}
	
	By Team= By.xpath("//a[contains(text(),'Team')]");
	public WebElement Button5()
	{
		return driver.findElement(Team);
	}
	By buttons= By.xpath("//a[@class='st-root-link nav-link']");
	public List<WebElement> Buttons()
	{
		return driver.findElements(buttons);
	}
	
	By Juniors= By.xpath("//h5[contains(text(),'Junior Engineer')]");
	public List<WebElement> Juniors_Engineer()
	{
		return driver.findElements(Juniors);
	}
	By Juniorsname= By.xpath("//h5[contains(text(),'Junior Engineer')]//preceding-sibling::h3");
	public List<WebElement> Juniors_name()
	{
		return driver.findElements(Juniorsname);
	}
	By CTOxpath= By.xpath("//*[@id=\"root\"]/div/section[2]/div/div/div/div/div/div[1]/div[1]/img");
	public WebElement CTO_xpath()
	{
		return driver.findElement(CTOxpath);
	}
	By HRManagerXpath= By.xpath("//*[@id=\"root\"]/div/section[2]/div/div/div/div/div/div[1]/div[2]/img");
	public WebElement HRManager_xpath()
	{
		return driver.findElement(HRManagerXpath);
	}
	
	
	
}

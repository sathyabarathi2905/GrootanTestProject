package com;

import static org.testng.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class GrootanTest extends TestBase {
	public WebDriver driver;

	@BeforeSuite
	public void InitializeDriver() {
		driver = browserInitialize();
	}

	@Test(priority = 1)
	public void clickingAllSections1() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, InterruptedException {

		try {
			result = "Pass";
			ObjectRepository OR = new ObjectRepository(driver);
			for (int j = 1; j <= 7; j++) {
				OR.section().get(j).click();
				if (j == 4) {
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOf(driver
							.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div/ul/li[2]/a[5]"))));
				}
				screenshot1(driver, OR.section().get(j).getText());
			}
			assertEquals(new File(".\\TestOneFolder").list().length, OR.totalSections().size() + 1);

		} catch (AssertionError E) {
			result = "Fail";
		}

		Testcase1 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm1.put("Testcase1", result);
		hm2.put("Testcase1", Testcase1);
	}

	@Test(priority = 2)
	public void clickingAllSections2() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, InterruptedException {
		try {
			ObjectRepository OR = new ObjectRepository(driver);
			for (int j = 1; j <= 7; j++) {
				OR.section().get(j).click();
				if (j == 4) {
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOf(driver
							.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div/ul/li[2]/a[5]"))));
				}
				screenshot2(driver, OR.section().get(j).getText());
			}
			assertEquals(new File(".\\TestTwoFolder").list().length, OR.totalSections().size() + 1);

		} catch (AssertionError E) {
			result = "Fail";
		}
		
		String Testcase2 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm2.put("Testcase2", Testcase2);
		hm1.put("Testcase2", result);	
	}
	@Test(priority = 3)
	public void comparingAllSections() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, InterruptedException {
		for (int j = 1; j <= 7; j++) {
			result = "Pass";
			ObjectRepository OR = new ObjectRepository(driver);
			try {
		        BufferedImage biA = ImageIO.read(new File(System.getProperty("user.dir") + "\\TestOneFolder\\"
						+ OR.section().get(j).getText() + ".png"));
		        DataBuffer dbA = biA.getData().getDataBuffer();
		        int sizeA = dbA.getSize();                      
		        BufferedImage biB = ImageIO.read(new File(System.getProperty("user.dir") + "\\TestOneFolder\\"
						+ OR.section().get(j).getText() + ".png"));
		        DataBuffer dbB = biB.getData().getDataBuffer();
		        int sizeB = dbB.getSize();
		        if(sizeA == sizeB) {
		            for(int i=0; i<sizeA; i++) { 
		                if(dbA.getElem(i) != dbB.getElem(i)) {
		                    result="Fail";
		                }
		            }
		            result="Pass";
		        }
		        else {
		        	result="Fail";
		        }
		    } 
		    catch (Exception e) { 
		        result="Fail";
		    }
			}
		String Testcase3 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm2.put("Testcase3", Testcase3);
		hm1.put("Testcase3", result);
	}

	@Test(priority = 5)
	public void juniorEngineersList() throws IOException {
		try {
			result = "Pass";
			File file = new File(".\\Results\\TestResults.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sh1 = wb.createSheet("JuniorEngineers");
			ObjectRepository OR = new ObjectRepository(driver);
			OR.teamSection().click();
			for (int j = 0; j < OR.Juniors_name().size(); j++) {
				sh1.createRow(j).createCell(0).setCellValue(OR.Juniors_name().get(j).getText());
			}

			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);

			assertEquals(sh1.getLastRowNum() + 1, OR.Juniors_name().size());

		} catch (AssertionError E) {
			result = "Fail";
		}

		String Testcase4 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm1.put("Testcase4", result);
		hm2.put("Testcase4", Testcase4);
	}

	@Test(priority = 4)
	public void compareTwoPersonsimage() throws IOException, InterruptedException {
		try {

			result = "Pass";
			ObjectRepository OR = new ObjectRepository(driver);
			OR.teamSection().click();
			
			File CTO_Image = OR.imageOfCTO().getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(CTO_Image, new File(".\\ImageComparison\\CTO_Image.png"));
			File HRManager_Image = OR.imageOfHRManager().getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(HRManager_Image, new File(".\\ImageComparison\\HRManager_Image.png"));

			ImageDiffer imgDiff = new ImageDiffer();
			BufferedImage image1 = ImageIO
					.read(new File(System.getProperty("user.dir") + "\\ImageComparison\\CTO_Image.png"));
			BufferedImage image2 = ImageIO
					.read(new File(System.getProperty("user.dir") + "\\ImageComparison\\HRManager_Image.png"));
			ImageDiff diff = imgDiff.makeDiff(image1, image2);
			assertEquals(diff.hasDiff(), true);
		} catch (AssertionError E) {
			result = "Fail";
		}
		String Testcase5 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm1.put("Testcase5", result);
		hm2.put("Testcase5", Testcase5);
	}
	@AfterTest
	public void testResultsCreation() throws IOException {

		FileInputStream fis = new FileInputStream(".\\Results\\TestResults.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh2 = wb.createSheet("TSR");
		XSSFRow row1 = sh2.createRow(0);
		for (int j = 0; j < 3; j++) {
			String Headings[] = { "Testcases", "Status", "Screenshot" };
			XSSFCell cellsinrow1 = row1.createCell(j);
			cellsinrow1.setCellValue(Headings[j]);
		}
		for (int i = 1; i <= 5; i++) {
			XSSFRow row2 = sh2.createRow(i);

			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					XSSFCell cells = row2.createCell(j);
					cells.setCellValue(hm2.get("Testcase" + i));
				}
				if (j == 1) {
					XSSFCell cells = row2.createCell(j);
					cells.setCellValue(hm1.get("Testcase" + i));
				}
			}
		}
		FileOutputStream fos = new FileOutputStream(".\\Results\\TestResults.xlsx");
		wb.write(fos);
	}
	@AfterSuite
	public void closebrowser() {
		driver.quit();
	}
}

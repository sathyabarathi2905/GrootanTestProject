package com;

import static org.testng.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class GrootanTest extends TestBase {
	public WebDriver driver;

	@BeforeTest
	public void InitializeDriver() {
		driver = BrowserInitialize();
	}

	@Test(priority=1)
	public void clickingAllSections1() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, InterruptedException {

		try {
			result = "Pass";
			ObjectRepository OR = new ObjectRepository(driver);
			for (int j = 1; j <= 3; j++) {
				OR.allSections(j).click();
				screenshot1(driver, OR.allSections(j).getText());
			}
			OR.Button4().click();
			screenshot1(driver, OR.Button4().getText());
			driver.get("https://www.grootan.com/");
			for (int j = 5; j <= 7; j++) {
				OR.allSections(j).click();
				screenshot1(driver, OR.allSections(j).getText());
			}
			assertEquals(new File(".\\TestOneFolder").list().length, OR.Buttons().size() + 1);

		} catch (AssertionError E) {
			result = "Fail";
		}

		Testcase1 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm1.put("Testcase1", result);
		hm2.put("Testcase1", Testcase1);
		// System.out.println(hm1.get("Testcase1"));
		// System.out.println(hm2.get("Testcase1"));
	}

	@Test(priority=2)
	public void clickingAllSections2() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, InterruptedException {
		try {
			ObjectRepository OR = new ObjectRepository(driver);
			for (int j = 1; j <= 3; j++) {
				OR.allSections(j).click();
				screenshot2(driver, OR.allSections(j).getText());
			}
			OR.Button4().click();
			screenshot2(driver, OR.Button4().getText());
			driver.get("https://www.grootan.com/");
			for (int j = 5; j <= 7; j++) {
				OR.allSections(j).click();
				screenshot2(driver, OR.allSections(j).getText());
			}
			assertEquals(new File(".\\TestTwoFolder").list().length, OR.Buttons().size() + 1);

		} catch (AssertionError E) {
			result = "Fail";
		}

		String Testcase2 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm2.put("Testcase2", Testcase2);
		hm1.put("Testcase2", result);
		// System.out.println(hm1.get("Testcase2"));
		// System.out.println(hm2.get("Testcase2"));
	}

	@Test(priority=3)
	public void comparingAllSections() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException, InterruptedException {
		int j = 1;
		for (int i = 1; i <= 7; i++) {
			result1 = "Pass";
			if (i != 4) {
				try {
					ObjectRepository OR = new ObjectRepository(driver);
					ImageDiffer imgDiff = new ImageDiffer();
					BufferedImage image1 = ImageIO.read(new File(System.getProperty("user.dir") + "\\TestOneFolder\\"
							+ OR.allSections(j).getText() + ".png"));
					BufferedImage image2 = ImageIO.read(new File(System.getProperty("user.dir") + "\\TestTwoFolder\\"
							+ OR.allSections(j).getText() + ".png"));
					ImageDiff diff = imgDiff.makeDiff(image1, image2);

					assertEquals(diff.hasDiff(), false);

				} catch (AssertionError E) {
					result1 = "Fail";
				}
			}
		}
		try {
			result2 = "Pass";
			ImageDiffer imgDiff = new ImageDiffer();
			BufferedImage image1 = ImageIO.read(new File(System.getProperty("user.dir") + "\\TestOneFolder\\Blog.png"));
			BufferedImage image2 = ImageIO.read(new File(System.getProperty("user.dir") + "\\TestTwoFolder\\Blog.png"));
			ImageDiff diff = imgDiff.makeDiff(image1, image2);
			assertEquals(diff.hasDiff(), false);

		} catch (AssertionError E) {
			result2 = "Fail";
		}

		if (result1.equalsIgnoreCase("Pass") && result2.equalsIgnoreCase("Pass")) {
			result = "Pass";
		} else {
			result = "Fail";
		}
		String Testcase3 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm2.put("Testcase3", Testcase3);
		hm1.put("Testcase3", result);
		// System.out.println(hm1.get("Testcase3"));
		// System.out.println(hm2.get("Testcase3"));

	}

	@Test(priority=5)
	public void juniorEngineersList() throws IOException {
		try {
			result = "Pass";
			File file = new File(".\\Results\\TestResults.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sh1 = wb.createSheet("JuniorEngineers");
			ObjectRepository OR = new ObjectRepository(driver);
			OR.Button5().click();
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
		// System.out.println(hm1.get("Testcase4"));
		// System.out.println(hm2.get("Testcase4"));
	}

	@Test(priority=4)
	public void compareTwoPersonsimage() throws IOException, InterruptedException {
		try {

			result = "Pass";
			ObjectRepository OR = new ObjectRepository(driver);
			OR.Button5().click();
			Thread.sleep(3000);
			File CTO_Image = OR.CTO_xpath().getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(CTO_Image, new File(".\\ImageComparison\\CTO_Image.png"));
			File HRManager_Image = OR.HRManager_xpath().getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(HRManager_Image, new File(".\\ImageComparison\\HRManager_Image.png"));

			ImageDiffer imgDiff = new ImageDiffer();
			BufferedImage image1 = ImageIO
					.read(new File(System.getProperty("user.dir") + "\\ImageComparison\\CTO_Image.png"));
			BufferedImage image2 = ImageIO
					.read(new File(System.getProperty("user.dir") + "\\ImageComparison\\HRManager_Image.png"));
			ImageDiff diff = imgDiff.makeDiff(image1, image2);

			assertEquals(diff.hasDiff(), false);

		} catch (AssertionError E) {
			result = "Fail";
		}

		String Testcase5 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		hm1.put("Testcase5", result);
		hm2.put("Testcase5", Testcase5);
		// System.out.println(hm1.get("Testcase5"));
		// System.out.println(hm2.get("Testcase5"));
	}

	@Test(priority=6)
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
				/*if (j==2)
				{
					InputStream inputStream = new FileInputStream(".\\ImageComparison\\CTO_Image.png");
					   byte[] bytes = IOUtils.toByteArray(inputStream);
					   int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
					   inputStream.close();
					   CreationHelper helper = wb.getCreationHelper();
					   Drawing drawing = sh2.createDrawingPatriarch();
					   ClientAnchor anchor = helper.createClientAnchor();
					   anchor.setCol1(2); //Column B
					   anchor.setRow1(1); //Row 3
					   anchor.setCol2(3); //Column C
					   anchor.setRow2(2); //Row 4
					   Picture pict = drawing.createPicture(anchor, pictureIdx);
					XSSFCell cells = row2.createCell(j);
					
				}*/

			}
		}
		
		FileOutputStream fos = new FileOutputStream(".\\Results\\TestResults.xlsx");
		wb.write(fos);
	}


	@AfterTest
	public void closebrowser() {
		driver.quit();
	}
}

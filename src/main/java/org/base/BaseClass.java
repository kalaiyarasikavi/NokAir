package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	// Browser Launch
//	public static WebDriver browserLaunch() {
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		return driver;
//	}

	public static WebDriver browserLaunch(String browserName) {

//		if(browserName.equals("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		}
//		else if(browserName.equals("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//		}
//		
//		else if(browserName.equals("edge")) {
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//		}
//		return driver;

		
		switch (browserName) {
		
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			break;
		}
		return driver;

	}

	// Url Launch
	public static void launchUrl(String url) {
		driver.get(url);
	}

	// Maximize
	public static void maximize() {
		driver.manage().window().maximize();
	}

	// Implicitwait
	public static void implicitWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	// Current url
	public static String currentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	// Title
	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	// quit
	public static void browerQuit() {
		driver.quit();
	}

	// sendkeys
	public static void sendKeys(WebElement e, String name) {

		e.sendKeys(name);
	}

	// click
	public static void btnClick(WebElement e) {
		e.click();
	}

	// getText
	public static String getText(WebElement e) {
		String text = e.getText();
		return text;
	}

	// getAttributes

	public static String getAttributes(WebElement e) {
		String name = e.getAttribute("value");
		return name;
	}

	// Actions
	// movetoelement
	public static void movetoElement(WebElement e) {
		Actions a = new Actions(driver);
		a.moveToElement(e).perform();

	}

	// drag&drop
	public static void dragAndDrop(WebElement src, WebElement des) {
		Actions a = new Actions(driver);
		a.dragAndDrop(src, des).perform();
	}

	// Select
	// SelectByIndex
	public static void selectByIndex(WebElement e, int index) {
		Select s = new Select(e);
		s.selectByIndex(index);
	}

	// deselect by value
	public static void deselectByValue(WebElement e, String value) {
		Select s = new Select(e);
		s.deselectByValue(value);
	}

	// findElement
	public static WebElement findElements(String locator, String locatorName) {
		WebElement e = null;
		if (locator.equals("id")) {
			e = driver.findElement(By.id(locatorName));
		}
		if (locator.equals("name")) {
			e = driver.findElement(By.name(locatorName));
		}
		if (locator.equals("xpath")) {
			e = driver.findElement(By.xpath(locatorName));
		}
		return e;

	}

	// screenshot

	public static void screenShot(String filename) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des = new File(
				"C:\\Users\\WELCOME\\eclipse-workspace\\Selenium5.30PMDecember\\Screenshot\\" + filename + "fb.png");
		FileUtils.copyFile(src, des);
	}

	// js
	public static void js(WebElement e,String name) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+name+"')",e);

	}
	public static String getJsAttributes(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object obj = js.executeScript("return arguments[0].getAttribute('value')", e);
		String st = obj.toString();
		return st;
	}
	
	
	public static String getExcel(String fileName,String sheetName,int rowNo,int cellNo) throws IOException {
		File loc = new File("D:\\KalaiWorkspace\\MavenProject5.30PM\\src\\test\\resources\\Datas\\"+fileName+".xlsx");
		FileInputStream fi = new FileInputStream(loc);

		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet(sheetName);
		Row row = s.getRow(rowNo);
		Cell cell = row.getCell(cellNo);

		int type = cell.getCellType();
		String value=null;
		if(type==1) {
			 value = cell.getStringCellValue();
		}else {
			if(DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat si=new SimpleDateFormat("dd/MMM/yyyy");
				 value = si.format(date);
			}
			else {
				double db = cell.getNumericCellValue();
				long l=(long)db;
				 value = String.valueOf(l);
			}
		}
		return value;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

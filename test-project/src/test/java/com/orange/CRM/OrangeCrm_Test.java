package com.orange.CRM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrangeCrm_Test {
	
	public static WebDriver driver;
	public static String userName=null;
	public static String password=null;
	@BeforeClass
	public void beforeClass()
	{
		userName="admin";
		password="admin123";
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\BrowserDriver\\chromedriver.exe");	
	driver=new ChromeDriver();
	Reporter.log("Brower is up and running");
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
  @Test(priority=0)
  public void Tc1_ValidateLogin() {
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
	  WebElement ele=driver.findElement(By.xpath("//a[text()='Welcome Admin']"));
	  WebDriverWait wait=new WebDriverWait(driver, 120);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Welcome Admin']")));
	  Assert.assertTrue(ele.isDisplayed(), ele.toString()+"   is not displaying");
	  Reporter.log("validation of Home page is done");
  }
@Test(priority=1)
public void validateGeneral_Information()
{
	WebDriverWait wait=new WebDriverWait(driver, 120);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[text()='Admin']")));
	  driver.findElement(By.xpath("//b[text()='Admin']")).click();
	  WebElement organisation_menu=driver.findElement(By.xpath("//a[text()='Organization']"));
	  Actions act=new Actions(driver);
	  act.moveToElement(organisation_menu).perform();
	  WebElement gInformation_menu=driver.findElement(By.xpath("//a[contains(text(),'General Information')]"));
	  Actions act1=new Actions(driver);
	  act1.moveToElement(gInformation_menu).perform();
	  gInformation_menu.click();
	  WebDriverWait wait1=new WebDriverWait(driver, 120);
	  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='General Information']")));
	  WebElement generalInfoTab=driver.findElement(By.xpath("//h1[text()='General Information']"));
	  Assert.assertTrue(generalInfoTab.isDisplayed(), "general Infomation tab is not displaying");
	  Reporter.log("general Infomation tab validation done");
}

@Test(priority=2)
public void validate_GeneralInformation_Editable()
{
	String phoneNo="123456789";
	String emailId="abcdegf@gmail.com";
	String adrStr1="13 street G";
	String city="austin";
	String zipCode="560017";
	String adrStr2="mark Lane";
	String state="austin";
	String country="United States";
	WebElement editBtn=driver.findElement(By.xpath("//input[@value='Edit']"));
	WebDriverWait wait=new WebDriverWait(driver, 120);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Edit']")));
	  editBtn.click();
	  WebDriverWait wait1=new WebDriverWait(driver, 120);
	  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='organization_phone']")));
	  driver.findElement(By.xpath("//input[@id='organization_phone']")).clear();
	  driver.findElement(By.xpath("//input[@id='organization_phone']")).sendKeys(phoneNo);
	  driver.findElement(By.xpath("//input[@id='organization_email']")).clear();
	  driver.findElement(By.xpath("//input[@id='organization_email']")).sendKeys(emailId);
	  driver.findElement(By.xpath("//input[@id='organization_street1']")).clear();
	  driver.findElement(By.xpath("//input[@id='organization_street1']")).sendKeys(adrStr1);
	  driver.findElement(By.xpath("//input[@id='organization_city']")).clear();
	  driver.findElement(By.xpath("//input[@id='organization_city']")).sendKeys(city);
	  driver.findElement(By.xpath("//input[@id='organization_zipCode']")).clear();
	  driver.findElement(By.xpath("//input[@id='organization_zipCode']")).sendKeys(zipCode);
	  driver.findElement(By.xpath("//input[@id='organization_province']")).clear();
	  driver.findElement(By.xpath("//input[@id='organization_province']")).sendKeys(state);
	  driver.findElement(By.xpath("//input[@id='organization_street2']")).clear();
	  driver.findElement(By.xpath("//input[@id='organization_street2']")).sendKeys(adrStr2);
	 WebElement country_code=driver.findElement(By.xpath("//select[@id='organization_country']"));
	  Select sel=new Select(country_code);
	  sel.selectByVisibleText(country);
	  driver.findElement(By.xpath("//input[@value='Save']")).click();
	  WebDriverWait wait2=new WebDriverWait(driver, 120);
	  wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'message')]")));
	  try{
	 Assert.assertTrue( validate_WebElement("//*[contains(@value,'"+phoneNo+"')]"));
	 Assert.assertTrue(validate_WebElement("//*[contains(@value,'"+emailId+"')]"));
	 Assert.assertTrue(validate_WebElement("//*[contains(@value,'"+adrStr1+"')]"));
	 Assert.assertTrue(validate_WebElement("//*[contains(@value,'"+city+"')]"));
	 Assert.assertTrue( validate_WebElement("//*[contains(@value,'"+zipCode+"')]"));
	 Assert.assertTrue( validate_WebElement("//*[contains(@value,'"+adrStr2+"')]"));
	 Assert.assertTrue(validate_WebElement("//*[contains(@value,'"+state+"')]"));
	  }
	  catch(Throwable e){
		  Reporter.log(e.getMessage());
	 }	  
}

@Test(priority=3)
public void createAndValidateEmp()
{
	String fName="auto_User";
	String lName="user Test";
	String mName="user Test Middle";
	String empId="";
	
	driver.findElement(By.xpath("//b[text()='PIM']")).click();
	driver.findElement(By.xpath("//a[text()='Employee List']")).click();
	driver.findElement(By.xpath("//input[@value='Add']")).click();
	WebDriverWait wait=new WebDriverWait(driver, 120);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Add Employee']")));
	driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(fName);
	driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lName);
	driver.findElement(By.xpath("//input[@id='middleName']")).sendKeys(mName);
	driver.findElement(By.xpath("//input[@id='employeeId']")).sendKeys(empId);
	//driver.findElement(By.xpath("//input[@id='chkLogin']")).click();
	driver.findElement(By.xpath("//input[@id='photofile']")).sendKeys("C:\\Users\\Admin\\workspace\\SampleProject\\src\\main\\resources\\imageFile\\Chrysanthemum.jpg");
	driver.findElement(By.xpath("//input[@value='Save']")).click();
	WebDriverWait wait1=new WebDriverWait(driver, 120);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'"+fName+"')]")));
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}














public static boolean validate_WebElement(String path)
{
	boolean flag=false;
	try{
		driver.findElement(By.xpath(path)).isDisplayed();
		flag=true;
	}
	catch(Throwable e)
	{
		Reporter.log(e.getMessage());
		
	}
	return flag;
}


/*@AfterMethod
public void afterMethod()
{
	driver.findElement(By.xpath("")).click();
	driver.findElement(By.xpath("")).click();
}*/


/*@AfterClass
public void afterClass()
{
	
}
*/




}


package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
import pageObjects.MyAccountPage;
import pageObjects.SigninPage;
import utilities.DataReader;

public class Steps {
	
	WebDriver driver;
	HomePage hp;
	SigninPage sp;
	MyAccountPage macct;
	
	List<HashMap<String, String>> datamap; //Data driven
	
	Logger log;     //for logging
	ResourceBundle rb; //teading config.properties files
	String br;  //to stor browser name
	
	@Before  //Junit hook -execute once before starting
	public void setup()
	{
		//for logging
		log=LogManager.getLogger(this.getClass());
		
		//reading config.properties (for browser)
		rb=ResourceBundle.getBundle("config");
		br=rb.getString("browser");
		
	}
	
	@After
	public void tearDown(Scenario scenario)//scenario is a predefined class in java
	{
		System.out.println("Scenario Status"+"===>"+scenario.getStatus());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot,"image/png",scenario.getName());
		
		driver.quit();
	}
	
	@Given("User Launch browser")
	public void user_launch_browser() {

		if(br.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("opens URL {string}")
	public void opens_url(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	   
	}
	
	@When("Click on Signin")
	public void click_on_signin() {
		hp=new HomePage(driver);
		hp.clickSignIn();
	    log.info("Clicked on Signin link");
	}
	
    @When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pswd) {
	
		sp=new SigninPage(driver);
		
		log.info("Providing signin details");
		sp.setEmail(email);
		sp.setPassword(pswd);
		
	}

	@When("Click on Signin button")
	public void click_on_signin_button() {
		sp=new SigninPage(driver);
		sp.clickSignIn();
		log.info("Clicked on Signin button");
		
	}

	@Then("User navigate to MyAccount Page")
	public void user_navigate_to_my_account_page() {
		
		macct=new MyAccountPage(driver);
		macct.clickDrpMyAcct();
	    boolean targetpage=	macct.isMyAccountPageExists();
	    
	    if(targetpage)
	    {
	    	log.info("Signin success");
	    	Assert.assertTrue(true);
	    }
	    else {
	    	log.info("Signin Failed");
	    	Assert.assertTrue(false);
	    }
	}
				
	
				//********DATA DRIVEN WITH EXCEL********
	
	@Then("check User navigates to MyAccount Page by passing Email and Password with excel row {string}")
	public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_row(String row) throws InterruptedException {
	   
			datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Magento_SigninData.xlsx", "Sheet1");
			
	        int index=Integer.parseInt(row)-1;
	        String email= datamap.get(index).get("username");
	        String pwd= datamap.get(index).get("password");
	        String exp_res= datamap.get(index).get("result");
	       
	        sp=new SigninPage(driver);
	        sp.setEmail(email);
	        sp.setPassword(pwd);
	       
	        sp.clickSignIn();
	
	        try {
	        	
		        macct=new MyAccountPage(driver);
			    boolean targetpage=	macct.isMyAccountPageExists();
			    
		        if(exp_res.equals("valid"))
		        {
		        	if(targetpage==true)
		        	{
		        		MyAccountPage macc=new MyAccountPage(driver);//v should create an objct here also	
			        	macc.clickDrpMyAcct();//again click dropdown from myacct page to view signout link
						macc.clickSignout();//if it is signin must signout
						Assert.assertTrue(true);
		        	}
		        	else
		        	{
		        		Assert.assertTrue(false);
		        		//Assert.fail();
		        	}
			
		        }
		        if(exp_res.equals("invalid"))
		        {
					if(targetpage==false)
					{
				
						macct.clickDrpMyAcct();//again click dropdown from myacct page to view signout link
						macct.clickSignout();
						Assert.assertTrue(false);
					}
					else
					{
						Assert.assertTrue(true);
					}
		         }
	            }
	        	catch(Exception e) {
				Assert.fail();
	        	}
	        
	}
}



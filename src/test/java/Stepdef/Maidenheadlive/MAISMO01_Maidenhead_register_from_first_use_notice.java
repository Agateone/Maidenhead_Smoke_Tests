package Stepdef.Maidenheadlive;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Elements.Finish_Notice_elements;
import Elements.Popbitch_First_Use_Notice_Elements;
import Elements.Wallet_Elements;
import Elements.Register_Page_Elements1;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MAISMO01_Maidenhead_register_from_first_use_notice {
	//Initiate driver
	WebDriver driver;	
	
	//--------------------POPSMO01-----------------------
	//Given I am a user of Axate and I am on the registration page through Maidenhead FUN 
	@Given("^I am a user of Axate and I am on the registration page through Maidenhead FUN \"([^\"]*)\"$")
	@Test(priority=1)
	@Parameters("browser")
	public void i_am_a_user_of_Axate_and_I_am_on_the_registration_page_through_Maidenhead_FUN(String browser) throws Throwable {
		System.out.println("Executing CRISMO01_Maidenhead_register_from_first_use_notice");
		
				//chrome
		if (browser.equalsIgnoreCase("chrome")) { 
			System.setProperty("webdriver.chrome.driver","C:/Users/Administrator/Desktop/chromedriver.exe");				
			driver= new ChromeDriver();				
			driver.get("https://www.maidenhead-advertiser.co.uk/news/remember-when/152676/remember-when-neighbours-star-draws-a-crowd-and-protests-over-m4-widening.html");
			//driver.findElement(By.linkText("Ok")).click();
			Thread.sleep(10000);
			Popbitch_First_Use_Notice_Elements popbitch_first_use_elements= new Popbitch_First_Use_Notice_Elements(driver);
			Thread.sleep(6000);
			popbitch_first_use_elements.Click_On_Popbitch_First_Use_Notice_Create_Wallet();
		    Thread.sleep(20000);
			String reg_Page_url= driver.getCurrentUrl();
			System.out.println(reg_Page_url);
			if(reg_Page_url.contains("https://account.agate.io/my-agate/sign-up?"))
			{
				System.out.println("Clicking on create wallet opened registration page");
			}
	} 	
				
	}

	
	
	@When("^I enter all the required details on step one and click on continue$")
	@Test(priority=2)
	public void i_enter_all_the_required_details_on_step_one_and_click_on_continue() throws Throwable {
	
		Register_Page_Elements1 Reg_page_elements = new Register_Page_Elements1(driver);
		Reg_page_elements.Registration_Step1();
		System.out.println("2 of 6");
		
	}

	
	@When("^top up with a valid card in steptwo with one pound and click on continue$")
	@Test(priority=3)
	public void top_up_with_a_valid_card_in_steptwo_with_one_pound_and_click_on_continue() throws Throwable {
		Register_Page_Elements1 Reg_page_elements = new Register_Page_Elements1(driver);
		Thread.sleep(5000);
		Reg_page_elements.voucher_process();
		Thread.sleep(5000);
		Reg_page_elements.click_continue_on_reg_page2();
		System.out.println("3 of 6");
	}
	
	
	@Then("^I get a funded axate wallet with one pound in it$")
	@Test(priority=4)
	public void i_get_a_funded_axate_wallet_with_one_pound_in_it() throws Throwable {
		Wallet_Elements w1 = new Wallet_Elements(driver);
		w1.Click_On_popbitch_staging_agate_poster();
		String actual_current_balance=w1.current_balance();
		String expected_current_balance="10.00";
		Assert.assertEquals(actual_current_balance, expected_current_balance);
		System.out.println("4 of 6");
	}

	
	@Then("^navigated to the same article$")
	@Test(priority=5)
	public void navigated_to_the_same_article() throws Throwable {
	    String actual_url= driver.getCurrentUrl();
	    String expected_url = "https://www.maidenhead-advertiser.co.uk/news/remember-when/152676/remember-when-neighbours-star-draws-a-crowd-and-protests-over-m4-widening.html";
	    Assert.assertEquals(actual_url, expected_url);
	    System.out.println("5 of 6");
	}
	
	
	@Then("^finish notice appears$")
	@Test(priority=6)
	public void finish_notice_appears() throws Throwable {
		Finish_Notice_elements finish_notice = new Finish_Notice_elements(driver);
		Boolean Actual_result = finish_notice.Verify_finish_notice_appears();
		Boolean Expected_result= true;
		Assert.assertEquals(Actual_result, Expected_result);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println("6 of 6");
		driver.quit();
	}
	

}
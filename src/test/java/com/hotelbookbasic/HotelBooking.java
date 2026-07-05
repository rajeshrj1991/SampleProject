package com.hotelbookbasic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
public class HotelBooking {
	WebDriver driver;
	String orderId;
	
	public void login() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://www.omrbranch.com/");
		driver.manage().window().maximize();
		WebElement txtUser = driver.findElement(By.id("email"));
		txtUser.sendKeys("rajcscservice@gmail.com");
		WebElement txtPass = driver.findElement(By.id("pass"));
		txtPass.sendKeys("Rajesh@1991");
		driver.findElement(By.xpath("//button[@value='login']")).click();
		Thread.sleep(3000);
		WebElement message = driver.findElement(By.xpath("//a[contains(text(),'Welcome Rajesh')] "));
		String wel = message.getText();
		System.out.println(wel);
		}
	
	
	public void exploreHotel() throws InterruptedException {
		
		WebElement Txtstate = driver.findElement(By.xpath("//select[@id='state']"));
		Select select=new Select(Txtstate);
		select.selectByVisibleText("Tamil Nadu");
		WebElement selCity = driver.findElement(By.xpath("//select[@id='city']"));
		Thread.sleep(3000);
		Select select1 = new Select(selCity);
		select1.selectByVisibleText("Chennai");
		WebElement selRoom = driver.findElement(By.xpath("//select[@id='room_type']"));
		Select select2 = new Select(selRoom);
		select2.selectByVisibleText("Deluxe");
		select2.selectByVisibleText("Standard");
		select2.selectByVisibleText("Suite");
		select2.selectByVisibleText("Luxury");
		select2.selectByVisibleText("Studio");
		driver.findElement(By.name("check_in")).click();

		WebElement date1 = driver.findElement(By.name("check_in"));

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript(
		    "arguments[0].value='2026-06-26';",
		    date1);

		WebElement date = driver.findElement(By.name("check_out"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
		    "arguments[0].value='2026-06-30';",
		    date);
	
	WebElement drpRoom = driver.findElement(By.xpath("//select[@id='no_rooms']"));
	Select select3 = new Select(drpRoom);
	select3.selectByVisibleText("3-Three");
	WebElement drpAdults = driver.findElement(By.id("no_adults"));
	Select select4 = new Select(drpAdults);
	select4.selectByVisibleText("4-Four");
	WebElement txtChild = driver.findElement(By.id("no_child"));
	txtChild.sendKeys("2");
	WebElement frame = driver.findElement(By.id("hotelsearch_iframe"));
	driver.switchTo().frame(frame);
	driver.findElement(By.id("searchBtn")).click();
	driver.switchTo().defaultContent();
	
	}
	public void selectHotelPage() throws InterruptedException {
		WebElement txtSelect = driver.findElement(By.xpath("//h5[text()='Select Hotel']"));
		String txtsel = txtSelect.getText();
		System.out.println(txtsel);
		System.out.println("------------------------");
		System.out.println("Hotel List");
		List<WebElement> hotels = driver.findElements(
		        By.xpath("//div[@id='hotellist']//div[contains(@class,'hotel-suites')]/h5"));

		System.out.println("Total Hotels: " + hotels.size());

		for(WebElement hotel : hotels) {
		    System.out.println(hotel.getText().trim());
		}
		System.out.println("---------------------");
		System.out.println("Hotel Price Without Tax");
		List<WebElement> prize = driver.findElements(By.xpath("//div[@class='col-md-4']//div[@class='prize']/h2"));
		System.out.println("Prizes"+prize.size());
		for (WebElement prizes : prize) {
			System.out.println(prizes.getText().trim());
			
		}
		System.out.println("---------------------");
		System.out.println("Hotel Price With Tax");
		driver.findElement(By.xpath("(//a[@class='btn filter_btn'])[3]")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public void bookHotel() throws InterruptedException {
		String txtBooking = driver.findElement(By.xpath("//h2[contains(text(),'Book Hotel')]")).getText();
		System.out.println(txtBooking);
		Thread.sleep(3000);
		driver.findElement(By.id("own")).click();
		Thread.sleep(3000);
		WebElement selSal = driver.findElement(By.id("user_title"));
		Select select = new Select(selSal);
		select.selectByVisibleText("Mr.");
		driver.findElement(By.id("first_name")).sendKeys("Rajesh");
		driver.findElement(By.id("last_name")).sendKeys("Rajendran");
		driver.findElement(By.id("user_phone")).sendKeys("9094096933");
		driver.findElement(By.id("user_email")).sendKeys("rajcscservice@gmail.com");
		driver.findElement(By.id("gst")).click();
		driver.findElement(By.id("gst_registration")).sendKeys("9043592058");
		driver.findElement(By.id("company_name")).sendKeys("Greens Tech OMR Branch");
		driver.findElement(By.id("company_address")).sendKeys("Thoraipakkam");
		driver.findElement(By.id("step1next")).click();
		System.out.println("Add Special Request");
		Thread.sleep(3000);
		driver.findElement(By.id("bed")).click();
		driver.findElement(By.id("step2next")).click();
		driver.findElement(By.xpath("//h5[text()='Credit/Debit/ATM Card']")).click();
		WebElement clkPayemnt = driver.findElement(By.id("payment_type"));
		Select select1 = new Select(clkPayemnt);
		select1.selectByVisibleText("Debit Card");
		WebElement clkCard = driver.findElement(By.id("card_type"));
		Select select2 = new Select(clkCard);
		select2.selectByContainsVisibleText("Visa");
		driver.findElement(By.id("card_no")).sendKeys("5555555555552222");
		driver.findElement(By.id("card_name")).sendKeys("Rajesh");
		WebElement clkMonth = driver.findElement(By.id("card_month"));
		Select select3 = new Select(clkMonth);
		select3.selectByVisibleText("February");
		WebElement slkYear = driver.findElement(By.id("card_year"));
		Select select4 = new Select(slkYear);
		select4.selectByVisibleText("2036");
		driver.findElement(By.id("cvv")).sendKeys("123");
		driver.findElement(By.id("submitBtn")).click();

	}
	public void bookingConfirmation() {
		WebElement bookingId = driver.findElement(By.xpath("//h2[@class='couppon-code']"));
		orderId = bookingId.getText().replace("#", "").replace("Booking is Confirmed", "").trim();
		System.out.println("Order Id:"+orderId);
		WebElement bookHotel = driver.findElement(By.xpath("//p[contains(text(),'Hotel')]"));
		String bookedHotel = bookHotel.getText().replace("Hotel", "").replace("is booked!", "").trim();
		System.out.println("Booked Hotel:"+bookedHotel);
		driver.findElement(By.xpath("//button[text()='My Booking']")).click();
		System.out.println("----------------------------------");
		System.out.println("Booking Confirmation Completed");

	}
	public void bookingPage() {
		System.out.println("Order ID"+orderId);
		driver.findElement(By.name("search")).sendKeys(orderId);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Value Entered");
		String text = driver.findElement(By.xpath("//li[@class='alertMsg']")).getText();
		System.out.println(text);
		driver.findElement(By.name("search")).sendKeys(orderId);
		driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
		System.out.println("----------------------------");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String text1 = driver.findElement(By.xpath("//li[@class='alertMsg']")).getText();
		System.out.println(text1);


	}
public static void main(String[] args) throws InterruptedException {
	HotelBooking hotel = new HotelBooking();
	hotel.login();
	hotel.exploreHotel();
	hotel.selectHotelPage();
	hotel.bookHotel();
	hotel.bookingConfirmation();
	hotel.bookingPage();
}
}

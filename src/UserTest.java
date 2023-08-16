//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class UserTest {

	public static void main(String[] args) {
		
		UserTest test=new UserTest();
		test.callBrowser();
		test.passingUsernameAndPassword("admin@gmail.com", "admin@12345");
		test.adminLogin();
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		test.clickUserAndURLValidation();
		test.entriesDropdown();
		test.searchUser("Ad");
		test.addNewUser("ram@gmail.com","ram@12345","Ram","Gopal");
		test.updateUsers("User");
		test.deleteUser();
		test.logOut();

	}
	static WebDriver driver=new ChromeDriver();
	String url="http://localhost/EmployeeUser/admin/";
	//A method to open the browser
	public void callBrowser() {
		System.setProperty("webdriver.driver.chrome", "chromedriver.exe");
		driver.get(url);
		driver.manage().window().maximize();
	}
	//A method to pass username and password
	String userName;
	String userPassword;
	public void passingUsernameAndPassword(String userName,String userPassword) {
		driver.findElement(By.xpath("//*[@id=\"login-frm\"]/div[1]/input")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id=\"login-frm\"]/div[2]/input")).sendKeys(userPassword);
		
	}
	//A method to check username and password are empty or not
	public void adminLogin() {
		WebElement userName=driver.findElement(By.xpath("//*[@id=\"login-frm\"]/div[1]/input"));
		WebElement userPassword=driver.findElement(By.xpath("//*[@id=\"login-frm\"]/div[2]/input"));
		
		if(userName.getAttribute("value").isEmpty()) {
			System.out.println("userName is Empty");
		}
		else if(userPassword.getAttribute("value").isEmpty()) {
			System.out.println("userPassword is Empty");
		}
		else {
			WebElement login =driver.findElement(By.xpath("//*[@id=\"login-frm\"]/button[1]"));
			login.click();
		}
		
	}
	
	//A method to URL validation
	
	public void clickUserAndURLValidation() {
		
		WebElement usersidemenu=driver.findElement(By.xpath("/html/body/div[1]/div[4]/a"));//By.xpath("//a[@href='user.php']");
		usersidemenu.click();
		String currentURL=driver.getCurrentUrl();
		
		System.out.println("Current URL is "+currentURL);
		
		String expectedURL="http://localhost/EmployeeUser/admin/users.php";
		if(currentURL.equals(expectedURL)) {
			System.out.println("URL Validation  Success!");
		}
		else {
			System.out.println("URL Validation  Fail!");
		}

	}
	//to click the show entries dropdown and print the selected value in the console
	
	public void entriesDropdown() {
		WebElement showentries=driver.findElement(By.xpath("//*[@id=\"table_length\"]/label/select"));
		showentries.sendKeys("100");
		showentries.click();
		String printshowentries=driver.findElement(By.xpath("//*[@id=\"table_length\"]/label/select/option[4]")).getText();
		System.out.println("The Selected option is :"+printshowentries);
	}
	//method to search the user
	public void searchUser(String name) {
		WebElement search=driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys(name);
	}
	//a method to add a new user
	public void addNewUser(String uname,String pawd,String firstname,String lastname) {
		WebElement addnewuser=driver.findElement(By.xpath("//*[@id=\"new_user\"]"));
		addnewuser.click();
		
		WebElement uname1=driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[1]/input"));
		uname1.sendKeys(uname);
		
		WebElement pawd1=driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[2]/input"));
		pawd1.sendKeys(pawd);
		
		WebElement fname=driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[3]/input"));
		fname.sendKeys(firstname);
		
		WebElement lname=driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input"));
		lname.sendKeys(lastname);
		
		WebElement save=driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[2]/button"));
		save.click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String alertMessage= driver.switchTo().alert().getText();
		System.out.println("Your saving alert message is : "+alertMessage);
		driver.switchTo().alert().accept();
		
	}
	// method to update the user
	public void updateUsers(String lastname) {
		WebElement updateusername=driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[3]/td[4]/center/button[1]"));
		updateusername.click();
		
		
		//driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input")).clear();
		WebElement changelastname=driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[1]/div[4]/input"));
		changelastname.clear();
		changelastname.sendKeys(lastname);
		
		WebElement savelastname=driver.findElement(By.xpath("//*[@id=\"user-frm\"]/div[2]/button"));
		savelastname.click();
		
		
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String alertMessage= driver.switchTo().alert().getText();
		System.out.println("Your Updating alert message is : "+alertMessage);
		driver.switchTo().alert().accept();
		
	}
	//method to delete the user
	public void deleteUser() {
		WebElement delete=driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[3]/td[4]/center/button[2]"));
		delete.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String alertMessage= driver.switchTo().alert().getText();
		System.out.println("Your Deleting first alert message is : "+alertMessage);
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().alert().accept();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		String alertMessage1= driver.switchTo().alert().getText();
		System.out.println("Your Deleting second alert message is : "+alertMessage1);
		driver.switchTo().alert().accept();
		
	}
	//method to logout from website
	public void logOut() {
		WebElement logout=driver.findElement(By.xpath("//a[@href='logout.php']"));
		logout.click();
	}
	
	

}
package irda;


import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class PanCheck {

	public static WebDriver driver;
	public static String path="D:\\Softwares\\chromedriver_win32\\chromedriver.exe";
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", path);
		driver= new ChromeDriver();
		
		//driver.get("http://www.irdaonline.org/PublicAccess/LookUpPAN.aspx");
		
		driver.get("file:///C:/Users/abhishek.sharma4/Desktop/Max%20Documents/page.htm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		

		System.out.println("Pan Number:");
		Scanner sc=new Scanner(System.in);
		String inputPan=sc.nextLine();
		System.out.println(inputPan);
		WebElement PANtext=driver.findElement(By.name("pan"));
	    PANtext.sendKeys(inputPan);
	    WebElement button=driver.findElement(By.id("val"));
	    button.click();
	    driver.switchTo().frame("iframe_a");
	    WebElement iframePan=driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$PAN_Details']"));
	    iframePan.sendKeys(inputPan);
	    final JDialog dialog = new JDialog();
	    dialog.setAlwaysOnTop(true);
	    String captchaVal = JOptionPane.showInputDialog(dialog,"Please enter the captcha value:");
		//Type the entered captcha to the text box
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtcaptcha")).sendKeys(captchaVal);
		WebElement submitbtn=driver.findElement(By.xpath("//input[@value='Submit']"));
		submitbtn.click();
		System.out.println("successful click");
	    
		List<WebElement> allRows = driver.findElements(By.xpath("//div[@id='ctl00_ContentPlaceHolder1_divPan']/div/table/tbody/tr")); 

		// And iterate over them and get all the cells 
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td")); 

		    // Print the contents of each cell
		    for (WebElement cell : cells) {         

		    System.out.println(cell.getText());
	    
	    
		    }
	    
		/*WebElement PANtext=driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$PAN_Details']"));
		PANtext.sendKeys("BRJPR4203H");
		String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
		//Type the entered captcha to the text box
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtcaptcha")).sendKeys(captchaVal);
		WebElement submitbtn=driver.findElement(By.xpath("//input[@value='Submit']"));
		submitbtn.click();
		System.out.println("successful click");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<WebElement> allRows = driver.findElements(By.xpath("//div[@id='ctl00_ContentPlaceHolder1_divPan']/div/table/tbody/tr")); 

		// And iterate over them and get all the cells 
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td")); 

		    // Print the contents of each cell
		    for (WebElement cell : cells) {         

		    System.out.println(cell.getText());
		    //Or try below code 
		    //System.out.println(cell.getAttribute("innerHTML"));*/
		
		sc.close();
	

	
		
	}
		
		}
}



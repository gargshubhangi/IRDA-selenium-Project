package irda;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PanCheckupdate1 {
	
	public static WebDriver driver;
	public static String path="D:\\Softwares\\chromedriver_win32\\chromedriver.exe";

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", path);
		driver= new ChromeDriver();
		driver.get("C:\\Users\\abhishek.sharma4\\Desktop\\Max Documents\\IRDA website\\page.htm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(By.name("pan"))).click().build().perform();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver d) {
                return (d.findElement(By.name("pan")).getAttribute("value").length()>=10);
            }});
	    WebElement button=driver.findElement(By.id("val"));
	    button.click();
	    String paninput=driver.findElement(By.name("pan")).getAttribute("value");
	    driver.switchTo().frame("iframe_a");
	    WebElement iframePan=driver.findElement(By.xpath("//input[@name='ctl00$ContentPlaceHolder1$PAN_Details']"));
	    iframePan.sendKeys(paninput);
	    final JDialog dialog = new JDialog();
	    dialog.setAlwaysOnTop(true);
	    String captchaVal = JOptionPane.showInputDialog(dialog,"Please enter the captcha value:");
		//Type the entered captcha to the text box
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtcaptcha")).sendKeys(captchaVal);
		WebElement submitbtn=driver.findElement(By.xpath("//input[@value='Submit']"));
		submitbtn.click();
		String data="";
	   
	    
		List<WebElement> allRows = driver.findElements(By.xpath("//div[@id='ctl00_ContentPlaceHolder1_divPan']/div/table/tbody/tr")); 
		
		// And iterate over them and get all the cells 
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td")); 

		    // Print the contents of each cell
		    for (WebElement cell : cells) {         

		    data=data +"\n"+ cell.getText();
	        
		    }
}
		driver.switchTo().parentFrame();
		WebElement textarea=driver.findElement(By.name("description"));
		
		textarea.sendKeys(data);
		System.out.println("Data fetched successfully");
	}
}


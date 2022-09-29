package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneDemo2 {

	public static void main(String[] args) throws InterruptedException {

		String name="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("amartharun@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Secret@12");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);

		//		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		//		WebElement prod = products.stream().filter(product-> 
		//		product.findElement(By.xpath("//b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		//		Thread.sleep(2000);
		//		prod.findElement(By.xpath("//div[@class='card-body']//button[text()=' Add To Cart']")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(name)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait =new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		String s= driver.findElement(By.xpath("//div[@class='cartSection']//h3")).getText();
		System.out.println(s);
		s.equals(name);

		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'ta-item')]")));

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')]/span[text()=' India']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
		//driver.findElement(By.cssSelector(".action__submit")).click();

		String txt = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(txt.equalsIgnoreCase(" Thankyou for the order. "));

	}

}

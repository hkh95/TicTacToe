package is.teamgoomba.TicTacToe.Selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumTest {
	private HtmlUnitDriver driver;
	private String baseUrl;
	private String baseScript;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver(true);
		baseUrl = "http://goomba-developmentbranch.herokuapp.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testClearBoard() throws Exception {
		driver.get(baseUrl + "");
		resetBoard();
		for(int i = 0; i < 9; i++) {
			WebElement box = driver.findElement(By.id("box_" + i));
			String classAttribute = box.getAttribute("class");
			assertEquals("button_box empty", classAttribute);
		}
	}
	
	@Test
	public void testStartPlayerChanges() throws Exception {
		driver.get(baseUrl + "");
		Thread.sleep(4000);
		String player1 = driver.findElement(By.id("player")).getText();
		resetBoard();
		Thread.sleep(4000);
		String player2 = driver.findElement(By.id("player")).getText();
		assertTrue(player1.equals(player2));
	}
	
	@Test
	public void testClickBox() throws Exception {
		driver.get(baseUrl + "");
		Thread.sleep(4000);
		driver.get(baseUrl + "");
		WebElement newGameButton = driver.findElement(By.id("restart"));
		newGameButton.click();
		Thread.sleep(4000);
		WebElement player = driver.findElement(By.id("player"));
		WebElement box0 = driver.findElement(By.id("box_0"));
		String expectedvalue;
		if(player.getText().equals("1")) {
			expectedvalue = "button_box player-X";
		} else {
			expectedvalue = "button_box player-O";
		}
		try {
			box0.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(classIs(By.id("box_0"), expectedvalue));
		} catch(Exception e) {
			fail("Could not click box!");
		}
	}
	
	@Test
	public void testMultipleClicksBox() throws Exception {
		driver.get(baseUrl + "");
		resetBoard();
		WebElement player = driver.findElement(By.id("player"));
		WebElement box0 = driver.findElement(By.id("box_0"));
		String expectedvalue;
		if(player.getText().equals("1")) {
			expectedvalue = "button_box player-X";
		} else {
			expectedvalue = "button_box player-O";
		}
		try {
			box0.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(classIs(By.id("box_0"), expectedvalue));
			driver.findElement(By.id("box_0")).click();
			wait.until(classIs(By.id("box_0"), expectedvalue));
		} catch(Exception e) {
			fail("Could not click box!");
		}
	}
	
	@Test
	public void testVictory() throws Exception {
		driver.get(baseUrl + "");
		resetBoard();
		WebElement player = driver.findElement(By.id("player"));
		WebElement box0 = driver.findElement(By.id("box_0"));
		String expectedvalue = "player " + player.getText() + " is the winner!";
		click(By.id("box_0"));
		click(By.id("box_1"));
		click(By.id("box_4"));
		click(By.id("box_2"));
		click(By.id("box_8"));
		String actual = driver.findElement(By.id("winner")).getText();
		assertTrue(expectedvalue.equals(actual));
	}
	
	private void resetBoard() throws Exception {
		WebElement newGameButton = driver.findElement(By.id("restart"));
		newGameButton.click();
		Thread.sleep(4000);
	}
	
	private void click(final By locator) throws Exception {
		driver.findElement(locator).click();
		Thread.sleep(4000);
	}
	
	public static ExpectedCondition<Boolean> classIs(final By locator, final String classname) {
		return new ExpectedCondition<Boolean>() {
			private String currentClass = "";
			@Override
			public Boolean apply(WebDriver driver) {
				WebElement element = driver.findElement(locator);
				if(element == null) {
					return false;
				}
				currentClass = element.getAttribute("class");
				if(currentClass == null) {
					return false;
				}
				return currentClass.equals(classname);
			}

			@Override
			public String toString() {
				return "classIs";
			}
		};
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}

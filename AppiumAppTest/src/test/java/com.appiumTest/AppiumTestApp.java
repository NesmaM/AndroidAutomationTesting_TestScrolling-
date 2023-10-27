package com.appiumTest;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;


public class AppiumTestApp {
	
	public static AppiumDriver<MobileElement> driver;

	
	@Before
	
	public void setUp() throws Exception{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "android");
		caps.setCapability("automationName", "UIAutomator2");
		caps.setCapability("platformVersion", "11");
		caps.setCapability("deviceName", "pixel-4");
		caps.setCapability("appPackage", "com.appiumpro.the_app");
		caps.setCapability("appActivity", "com.appiumpro.the_app.MainActivity");

		
		// hide keyboard
		caps.setCapability("unicodeKeyboard", true);
		caps.setCapability("resetKeyboard", true);
		
		URL url;
		url = new URL("http://127.0.0.1:4723/wd/hub/");
		driver = new AppiumDriver<MobileElement>(url, caps); 

	}
	
	public void swipeToScroll() {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 520, 1530);
		Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
		Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 520, 490);
		Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
		
		Sequence swipe = new Sequence(finger, 0);
		swipe.addAction(moveToStart);
		swipe.addAction(pressDown);
		swipe.addAction(moveToEnd);
		swipe.addAction(pressUp);
		
		driver.perform(Arrays.asList(swipe));
		
		
	}
	@After
	public void tearDown() {
		if(driver != null)
			driver.quit();
	}
	
	
	@Test
	public void appiumAppTest1() {
	    WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement echoBoxbutton = driver.findElementByXPath("(//android.view.ViewGroup[@resource-id=\"RNE__LISTITEM__padView\"])[1]");
		echoBoxbutton.click();
		
		// add some wait
		WebElement inputEditBox = driver.findElementByAccessibilityId("messageInput");
	    wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("messageInput")));
		inputEditBox.sendKeys("Hello World");
		WebElement saveButton = driver.findElementByXPath("//android.widget.TextView[@text=\"Save\"]");
		saveButton.click();
		
		WebElement successMsg = driver.findElementByAccessibilityId("Hello World");
		assert successMsg.getText().contains("Hello World");
	
	}
	
	//test scroll down and up
	@Test
	public void appiumAppTest2() {
	    WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement listDemoButton = driver.findElement(MobileBy.AccessibilityId("List Demo"));
		listDemoButton.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Altocumulus")));
		
		swipeToScroll();
		
		WebElement cloudSelection = driver.findElement(MobileBy.AccessibilityId("IBM Cloud"));
		cloudSelection.click();
		
		
}
		
		
	
	
	

}


package com.obs.seleniumbasics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class SeleniumCommands {
    public WebDriver driver;
    public void testInitialize(String browser, String url) {
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }else if(browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }else {
            try {
                throw new Exception("Browser not define");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //driver.get(url);
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }
    @BeforeMethod
    public void setup() {
        testInitialize("firefox","http://demowebshop.tricentis.com/");
    }
    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }

    //Verify home page title- first testcase
    @Test(enabled=false)
    public void verifyHomePageTitle() {
        String expTitle="Demo Web Shop";
        String actTitle=driver.getTitle();
        Assert.assertEquals(actTitle, expTitle,"ERROR:Invalid home page Title found");
    }
    @Test (enabled=false)
    public void verifyWindowHandle() {
        driver.get("https://demo.guru99.com/popup.php");
        String parentWindow=driver.getWindowHandle();
        //System.out.println(parentWindow);
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();
        Set<String> handleIds=driver.getWindowHandles();
        //System.out.println(handleIds);
        Iterator<String> itr=handleIds.iterator();
        while(itr.hasNext()) {
            String child=itr.next();
            if(!parentWindow.equals(child)) {
                driver.switchTo().window(child);
                driver.manage().window().maximize();
                driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("Test@gmail.com");
                driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
            }
            driver.switchTo().window(parentWindow);
        }
    }
    @Test (enabled=true)
    public void verifyFileUpload() {
        driver.get("https://demo.guru99.com/test/upload/");
        driver.findElement(By.xpath("//input[@id='uploadfile_0']")).sendKeys("F:\\Automation-Testing\\Sample_File.txt");
    }
    @Test
    public void verifyLogin(){
        System.out.println("hi");
    }
    @Test
    public void verifySearch(){
        System.out.println("hi");
}
}

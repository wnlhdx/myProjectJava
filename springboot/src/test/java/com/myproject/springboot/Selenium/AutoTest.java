package com.myproject.springboot.Selenium;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoTest {
    @Test
    public  void init(){
        String windriver="./driver-win/chromdriver.exe";
        String androiddriver="./driver-linux/chromdriver";
        String path = System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS") ?windriver:androiddriver;
        System.setProperty("webdriver.chrome.driver", path);
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.itest.info");
        String title = driver.getTitle();
        System.out.printf(title);
        driver.close();
    }
}

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class SeleniumMain {
    public static WebDriver driver;

    @BeforeAll
    public  static  void setupDriver(){
        driver = ShareDriver.getWebDriver();
        driver.get(Variables.FIRST_URL); // opens youtube
    }

    @AfterAll
    public static void CloseDriver() throws InterruptedException {
        System.out.println("Close The Tab When It's Done Downloading");
    }

  @Test
    public void findElement() throws InterruptedException {
      WebElement YtSearchBar = driver.findElement(By.xpath("//input[@id='search']")); // looks the search bar
      YtSearchBar.click(); // clicks it
      YtSearchBar.sendKeys(Variables.SONG_NAME, Keys.ENTER); // pastes the song name from Variables

      Thread.sleep(500); // waits half a second
      WebElement Video = driver.findElement(By.xpath("//yt-formatted-string[text()='" + Variables.SONG_NAME + "']")); // looks for the song/vid
      Video.click(); // clicks it
      Variables.SONG_URL = driver.getCurrentUrl(); // copy's the url to SONG_URL from Variables

      Thread.sleep(500); // waits half a second
      driver.get(Variables.SECOND_URL);

      if (Variables.MP4 == false) // if MP4 == false in Variables, then downloads in mp3
      {
          WebElement input = driver.findElement(By.id("input")); // looks for input
          input.sendKeys(Variables.SONG_URL, Keys.ENTER); // paste's the song url

          Thread.sleep(500); // wait's half a second
          WebElement download = driver.findElement(By.xpath("//a[@id='download']")); // looks for download button
          download.click(); // clicks it
      } else // else if MP4 = true in Variables, then downloads in mp4
      {
          WebElement mp4 = driver.findElement(By.xpath("//a[@id='mp4']")); // looks for mp4 button
          mp4.click(); // clicks it

          Thread.sleep(500); // wait's half a second
          WebElement input = driver.findElement(By.id("input")); // looks for input
          input.click(); // clicks
          input.sendKeys(Variables.SONG_URL, Keys.ENTER); // pastes the song url

          Thread.sleep(500); // wait's half a second
          WebElement download = driver.findElement(By.xpath("//a[@id='download']")); // looks for download button
          download.click(); // clicks it
      }
  }
}
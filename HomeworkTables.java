package homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomeworkTables {
    WebDriver driver;
    Map<Integer, String> data;
    Select select;
    List<Integer> key;
    List<String> value;

    @BeforeClass // runs once for all tests
    public void setUp() {
  data = new HashMap<>();
  key = new ArrayList<>();
  value = new ArrayList<>();
  WebDriverManager.chromedriver().setup();
  driver = new ChromeDriver();
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  // driver.manage().window().fullscreen();
    }

    @Test
    public void homeWorkTable() throws InterruptedException {
  driver.get("https://forms.zohopublic.com/murodil/report/Applicants/reportperma/"
    + "DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");
  select = new Select(driver.findElement(By.id("recPerPage")));
  select.selectByIndex(select.getOptions().size() - 1);

  int count = (Integer.parseInt(driver.findElement(By.id("total")).getText()) / 100) + 1;
  
  for (int k = 0; k < count; k++) {
      Thread.sleep(2000);

      printTableData("reportTab");
      for (int i = 0; i < key.size(); i++) {
    data.put(key.get(i), value.get(i));
      }
      
      driver.findElement(By.id("showNext")).click();
  }

  
//  ======================ITERATE THROUGH HASHMAP====================
   Set<Entry<Integer, String>> entries = data.entrySet();  
   for( Entry<Integer, String> each : entries) {  
   System.out.println(each);
   }

    }

    public void printTableData(String id) {
  int rowsCount = driver.findElements(By.xpath("//table[@id='" + id + "']/tbody/tr")).size();
  int colsCount = driver.findElements(By.xpath("//table[@id='" + id + "']/thead/tr/th")).size();

  System.out.println("===============");

  for (int rowNum = 1; rowNum <= rowsCount; rowNum++) {
      StringBuilder sb = new StringBuilder();
      for (int col = 1; col <= 1; col++) {
    String xpath = "//table[@id='" + id + "']/tbody/tr[" + rowNum + "]/td[" + col + "]";
    String tdData = driver.findElement(By.xpath(xpath)).getText();

    key.add(Integer.valueOf(tdData));
      }

      // =====================DATA========================
      for (int col = 2; col <= colsCount; col++) {
    String xpath = "//table[@id='" + id + "']/tbody/tr[" + rowNum + "]/td[" + col + "]";
    String tdData = driver.findElement(By.xpath(xpath)).getText();
    tdData = tdData + " ";
    sb.append(tdData);
      }
      value.add(sb.toString());
  }

    }
}

//1) goto https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8
//2) Create a HashMap
//3) change row number to 100, read all data on first page and put uniquID as a KEY 
//and Applicant info as a Value to a map. 
//
//applicants.put(29,"Amer, Sal-all@dsfdsf.com-554-434-4324-130000")
//
//4) Click on next page , repeat step 3
//5) Repeat step 4 for all pages 
//6) print count of items in a map. and assert it is matching
//with a number at the buttom
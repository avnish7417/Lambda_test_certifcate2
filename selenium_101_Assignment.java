import com.google.common.annotations.VisibleForTesting;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class selenium_101_Assignment {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Test_scenarios1(driver);
        Test_scenaios2(driver);
        Test_scenarios3(driver);
    }

    static void Test_scenarios1(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.lambdatest.com/selenium-playground");

        WebElement click = driver.findElement(By.linkText("Simple Form Demo"));
        click.click();
        String url = driver.getCurrentUrl();

        if (url.contains("simple-form-demo")) {
            System.out.println("Url contains simple-form-demo");
        } else {
            System.out.println("Url not contains simple-form-demo");
        }

        String variable = "Welcome to LambdaTest";
        driver.findElement(By.xpath("//input[@placeholder='Please enter your Message']")).sendKeys(variable);

        driver.findElement(By.xpath("//button[@class='mt-20 mb-10 bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white p-10 rounded focus:outline-none w-180']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='mt-20 mb-10 bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white p-10 rounded focus:outline-none w-180']")));
        String validate2 = driver.findElement(By.xpath("//p[@id='message']")).getText();
        System.out.println(validate2);
        if (variable.equals(validate2)) {
            System.out.println("The string is Same " + variable);
        } else {
            System.out.println("The string is not same " + variable);
        }


    }

    static void Test_scenaios2(WebDriver driver) {
        driver.get("https://www.lambdatest.com/selenium-playground");
        WebElement source = driver.findElement(By.linkText("Drag & Drop Sliders"));
        source.click();
        WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));
        int initialX = slider.getLocation().getX();
        System.out.println(initialX);
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, 188, 0).perform();
//        int finalX = slider.getLocation().getX();
        Assert.assertEquals(188, initialX + 158, "not match");


    }

    static void Test_scenarios3(WebDriver driver) throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
        WebElement alert = driver.findElement(By.xpath("//button[@class='bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white rounded p-10 focus:outline-none w-180 selenium_btn py-5 px-10 rounded']"));
        alert.click();

        HashMap<String, String> values_of_form = new HashMap<String, String>();
        values_of_form.put("name", "Avnish Kumar");
        values_of_form.put("inputEmail4", "avnish@gmail.com");
        values_of_form.put("inputPassword4", "Binmile@123");
        values_of_form.put("company", "Binmile");
        values_of_form.put("websitename", "www.binmile.com");
        values_of_form.put("inputCity", "Dehradun");
        values_of_form.put("inputAddress1", "dehradun vikasnagaar");
        values_of_form.put("inputAddress2", "dehradun Noida");
        values_of_form.put("inputState", "Uttarakhand");
        values_of_form.put("inputZip", "248198");
        String[] array = {"name", "inputEmail4", "inputPassword4", "company", "websitename", "inputCity", "inputAddress1", "inputAddress2", "inputState", "inputZip"};

        for (String str : array) {

            WebElement formelements = driver.findElement(By.xpath("//input[@id='" + str + "']"));
            formelements.sendKeys(values_of_form.get(str));
        }
        WebElement dropdown = driver.findElement(By.xpath("//select[@name='country']"));
        dropdown.click();
        driver.findElement(By.xpath("//option[@value='US']")).click();
        driver.findElement(By.xpath("//button[@class='bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white rounded p-10 focus:outline-none w-180 selenium_btn py-5 px-10 rounded']")).click();
        String check = "Thanks for contacting us, we will get back to you shortly.";
        String validation = driver.findElement(By.xpath("//*[text()='Thanks for contacting us, we will get back to you shortly.']")).getText();


        if (check.equals(validation)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        driver.quit();
    }

    @Test
    public void testRequiredFieldsValidation(WebDriver driver) {
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='name']"));
        driver.findElement(By.xpath("//button[@class='bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white rounded p-10 focus:outline-none w-180 selenium_btn py-5 px-10 rounded']")).click();

        // Validation
        String firstNameValue = firstNameField.getAttribute("value");


        Assert.assertTrue(firstNameValue.length() > 0, "First Name is required.");

    }
}






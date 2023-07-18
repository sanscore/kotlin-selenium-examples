import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

fun main(args: Array<String>) {
    val options = ChromeOptions()
    // options.setCapability("browserVersion", "114")
    // options.setCapability("platformName", "Linux")

    val driver: WebDriver = RemoteWebDriver(URL("http://localhost:4444/"), options)

    try {
        driver.get("https://google.com/search?q=${ args.joinToString("+") }")

        val results: List<WebElement> = driver.findElements(By.xpath("//*[@id=\"rso\"]/div"))

        for (result in results) {
            val link = result.findElement(By.xpath(".//div/div/div[1]/a"))
            val href = link.getAttribute("href")

            val h3 = link.findElement(By.tagName("h3"))
            val title = h3.text

            if (!href.isEmpty() && !title.isEmpty()) {
                println("${ title }: ${ href }")
            }
        }
    } finally {
        driver.quit()
    }

}
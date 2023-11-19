package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final By TOP_ORDER_BUTTON = By.xpath(".//div[starts-with(@class, 'Header')]/button[text()='Заказать']");
    private By BOTTOM_ORDER_BUTTON = By.xpath(".//div[starts-with(@class, 'Home')]/button[text()='Заказать']");
    private By FAQ_BUTTONS = By.xpath(".//div[@class='accordion__button']");
    private By FAQ_ANSWERS = By.cssSelector(".accordion__panel > p");
    private By ORDER_STATUS_BUTTON = By.xpath(".//button[text()='Статус заказа']");


    private By faqAnswer(int answerNumber) {
        // Возвращает локатор параграфа с ответом FAQ (нумеерация сверху вниз).
        // Сами тексты ответов находятся в
        return By.xpath(".//div[@class='accordion__panel' and @id='accordion__panel-{"+answerNumber+"}']/p");
    }

    public HomePage(WebDriver driver) {
        super(driver);

    }


    public void clickBottomOrderButton() {
        driver.findElement(BOTTOM_ORDER_BUTTON).click();
    }
    public void clickTopOrderButton() {
        driver.findElement(TOP_ORDER_BUTTON).click();
    }

     public void clickFaqQuestion(int questionNumber) {
         driver.findElements(FAQ_BUTTONS).get(questionNumber).click();
    }
    public boolean getFaqAnswer(int answerNumber) {
        return driver.findElements(FAQ_ANSWERS).get(answerNumber).isDisplayed();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderPage extends BasePage {
    public static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";
    private final By FIRST_NAME_INPUT = By.xpath(".//input[contains(@placeholder,'Имя')]");
    private final By INCORRECT_FIRST_NAME_MESSAGE = By.xpath(".//input[contains(@placeholder,'Имя')]/parent::div/div");
    private final By LAST_NAME_INPUT = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    private final By INCORRECT_LAST_NAME_MESSAGE = By.xpath(".//input[contains(@placeholder,'Фамилия')]/parent::div/div");
    private final By ADDRESS_INPUT = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    private final By INCORRECT_ADDRESS_MESSAGE = By.xpath(".//input[contains(@placeholder,'Адрес')]/parent::div/div");
    private final By SUBWAY_FIELD = By.xpath(".//input[contains(@placeholder,'метро')]")  ;
    private final By INCORRECT_SUBWAY_MESSAGE = By.xpath(".//input[contains(@placeholder,'метро')]/parent::div/parent::div/parent::div/div[@class!='select-search']") ;
    // возвращает локатор кнопки с подсказкой наименования Метро
    private By subwayHintButton(String subwayName) {
        // Возвращает локатор кнопки с вопросом FAQ (нумерация сверху вниз)
        return By.xpath(".//div[text()='" + subwayName + "']/parent::button");
    }
    private final By TELEPHONE_NUMBER_FIELD = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    private final By INCORRECT_TELEPHONE_NUMBER_MESSAGE = By.xpath(".//input[contains(@placeholder,'Телефон')]/parent::div/div");

    private final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");
    private final By BACK_BUTTON = By.xpath(".//button[text()='Назад']");
    private final By DATE_FIELD = By.xpath(".//input[contains(@placeholder,'Когда')]");
    private final By RENTAL_PERIOD_FIELD = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By RENTAL_PERIOD_LIST = By.xpath(".//div[@class='Dropdown-option']");
    private final By COLOR_CHECKBOXES = By.xpath(".//div[contains(text(),'Цвет')]/parent::div//input");
    private final By COMMENT_FOR_COURIER_FIELD = By.xpath(".//input[contains(@placeholder,'Комментарий для курьера')]");
    private final By ORDER_BUTTON = By.xpath(".//button[text()='Назад']/parent::div/button[text()='Заказать']");
    private final By ACCEPT_ORDER_BUTTON = By.xpath(".//button[text()='Да']");
    private final By ORDER_COMPLETED_INFO = By.xpath(".//div[contains(text(),'Номер заказа')]");
    private final By SHOW_STATUS_BUTTON = By.xpath(".//button[text()='Посмотреть статус']");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void inputLastName(String lastName) {
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    }
    public void inputFirstName(String firstName) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    }

    public void inputAddress(String address) {
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
    }
    public void chooseSubway(String subway){
        click(SUBWAY_FIELD);
        // ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", subwayHintButton(subway));
        click(subwayHintButton(subway));
    }
    public void inputTelephoneNumber(String telephoneNumber) {
        driver.findElement(TELEPHONE_NUMBER_FIELD).sendKeys(telephoneNumber);
    }

    public void goToScooterParams() {
        click(NEXT_BUTTON);
    }
    public void inputDate(String date) {
        driver.findElement(DATE_FIELD).sendKeys(date);
    }
    public void chooseRentalPeriod(int option) {
        click(RENTAL_PERIOD_FIELD);
        driver.findElements(RENTAL_PERIOD_LIST).get(option).click();
    }

    public void chooseColor(int option){
        driver.findElements(COLOR_CHECKBOXES).get(option).click();
    }
    public void inputComment(String comment){
        driver.findElement(COMMENT_FOR_COURIER_FIELD).sendKeys(comment);
    }

    public void acceptOrder() {
        click(ORDER_BUTTON);
        click(ACCEPT_ORDER_BUTTON);
    }

    // Метод для получения числа из текста информации о заказе
    private static List<String> findNumbersInText(String text) {
        List<String> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        return numbers;
    }
    // Вывести номер заказа
    public String getOrderNumber(){
        String aboutOrderText = driver.findElement(ORDER_COMPLETED_INFO).getText();
        return findNumbersInText(aboutOrderText).get(0);
    }

    public void fillUserData(String firstName, String lastName, String address, String subway, String telephoneNumber) {
        inputFirstName(firstName);
        inputLastName(lastName);
        inputAddress(address);
        chooseSubway(subway);
        inputTelephoneNumber(telephoneNumber);
    }

    public void fillRentData(String date, int rentalPeriod, int colorOption, String comment){
        inputDate(date);
        chooseRentalPeriod(rentalPeriod);
        chooseColor(colorOption);
        inputComment(comment);
    }

    public boolean isOrderInfoVisible() {
        return driver.findElement(ORDER_COMPLETED_INFO).isDisplayed();
    }

    public boolean isIncorrectFirstNameMessageDisplayed() {
        return driver.findElement(INCORRECT_FIRST_NAME_MESSAGE).isDisplayed();
    }
    public boolean isIncorrectLastNameMessageDisplayed() {
        return driver.findElement(INCORRECT_LAST_NAME_MESSAGE).isDisplayed();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    // Содержит локаторы и методы, доступные со всех страниц
    public final static String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    protected final WebDriver driver;
    private final static By YANDEX_LOGO_BUTTON  = By.cssSelector("a.Header_LogoYandex__3TSOI");;
    private final static By SCOOTER_LOGO_BUTTON  = By.xpath(".//img[@alt='Yandex']/parent::a");
    private final static By COOKIE_ACCEPT_BUTTON  = By.xpath(".//button[text()='да все привыкли']");
    private final static By ORDER_STATUS_BUTTON  = By.xpath(".//button[text()='Статус заказа']");

    public BasePage(WebDriver driver){
        this.driver=driver;
    }

    // Открыть Url
    public void openUrl(String url) {
        driver.get(url);
    }
    // Нажать на эелемент
    public void click(By elementLocator) {
        driver.findElement(elementLocator).click();
    }

    // Нажать на логотип Яндекса и перейти на новую вкладку
    public void clickYandexLogoAndSwitchToWindow() {
        click(YANDEX_LOGO_BUTTON);
        String handle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(handle);
    }
    public void clickScooterLogo() {
        click(SCOOTER_LOGO_BUTTON);
    }

    // Принять куки
    public void acceptCoockie() {
        click(COOKIE_ACCEPT_BUTTON);
    }

    // Нажать на кнопку "Статус заказа"
    public void clickOrderStatus(){
        click(ORDER_STATUS_BUTTON);
    }


    // Явное ожидание кликабельности данного элемента
    public void waitElementToBeClicable(WebElement element) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    // Явное ожидание перехода на URL главной страницы
    public void waitUrlToBe () {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
    }

    // Явное ожидание отображения элемента с данным локатором
    public void waitElementToBeVisible(By elementLocator) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }
}

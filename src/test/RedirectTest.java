import org.hamcrest.MatcherAssert;
import org.junit.Test;
import pages.BasePage;

import static org.hamcrest.CoreMatchers.containsString;

// Класс для  тестирования перехода на страницу Яндекса (Дзена) или Главную страницу самоката
public class RedirectTest extends BaseTest {

    // Конструктор
    public RedirectTest() {
        super();
    }


    // Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса
    @Test
    public void checkClickLogoYandex_expectGoToYandexMainPage() {
        super.implicitlyWait(50); // Неявное ожидание

        // Создаем экземпляр класса верхней части станицы c логотипом
        BasePage basePage = new BasePage(driver);
        basePage.openUrl(BasePage.MAIN_PAGE_URL); // Открываем страницу в браузере
        basePage.clickYandexLogoAndSwitchToWindow(); // Кликаем на логотип Яндекса и переходим в новое окно

        // Проверяем, что URL в новом окне совпадает с URL на главной странице Яндекса
        String expected = "dzen.ru"; // Ожидаемый URL
        String actual = driver.getCurrentUrl(); // Фактический URL
        // ассерт по вхождени, так как может открыться капча дзена
        MatcherAssert.assertThat(  actual, containsString(expected));
    }
}

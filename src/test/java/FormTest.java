import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @Test
    public void testSuccessfulForm() {
        TestDataGenerator.TestData testData = TestDataGenerator.generateTestData();

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(testData.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String firstDate = TestDataGenerator.generateDate(4);
        $("[data-test-id=date] input").setValue(firstDate);
        $("[data-test-id=name] input").setValue(testData.getName());
        $("[data-test-id=phone] input").setValue(testData.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно запланирована на " + firstDate));

        $("[data-test-id=date]").click();
        String secondDate = TestDataGenerator.generateDate(7);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(secondDate);
        $(byText("Запланировать")).click();

        $(withText("Перепланировать")).shouldBe(visible, Duration.ofSeconds(15)).click();

        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно запланирована на " + secondDate));
    }
}
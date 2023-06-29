import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
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
        $$(".menu").find(exactText(testData.getCity())).click();
        $("[data-test-id=date]").click();
        $$(".calendar__day").find(exactText(testData.getDayOfMonth())).click();
        $("[data-test-id=name] input").setValue(testData.getName());
        $("[data-test-id=phone] input").setValue(testData.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        $("[data-test-id=date]").click();
        $$(".calendar__day").find(exactText(String.valueOf(Integer.parseInt(testData.getDayOfMonth()) + 1))).click();
        $(byText("Запланировать")).click();

        $(withText("Перепланировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Перепланировать")).click();

        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
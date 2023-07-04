import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class TestDataGenerator {

    private TestDataGenerator() {
    }

    @Value
    public static class TestData {
        String city;
        String name;
        String phone;
    }

    public static TestData generateTestData() {
        Faker faker = new Faker(new Locale("ru"));
        return new TestData(
                faker.address().city(),
                faker.name().fullName(),
                faker.phoneNumber().subscriberNumber(10)
        );
    }
}
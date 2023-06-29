import com.github.javafaker.Faker;
import lombok.Data;

import java.util.Locale;

public class TestDataGenerator {

    @Data
    public static class TestData {
        private String city;
        private String dayOfMonth;
        private String name;
        private String phone;

        public void setCity(String city) {
            this.city = city;
        }

        public void setDayOfMonth(String dayOfMonth) {
            this.dayOfMonth = dayOfMonth;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static TestData generateTestData() {
        TestData testData = new TestData();
        Faker faker = new Faker(new Locale("ru"));
        testData.setCity(faker.address().city());
        testData.setDayOfMonth(faker.number().numberBetween(1, 28) + "");
        testData.setName(faker.name().fullName());
        testData.setPhone(faker.phoneNumber().subscriberNumber(10));
        return testData;
    }
}
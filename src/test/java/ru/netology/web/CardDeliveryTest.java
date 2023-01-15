package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegisterDateOfMeeting() {
        $("[data-test-id=\"city\"] input").setValue("Краснодар");
        $("[data-test-id=\"date\"] input").setValue(generateDate(4));
        $("[data-test-id=\"name\"] input").setValue("Иван Сергеев-Петров");
        $("[data-test-id=\"phone\"] input").setValue("+79265876523");
        $("[data-test-id=\"agreement\"] span").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=\"notification\"]").shouldHave(text("Встреча успешно забронирована на " + generateDate(4)), Duration.ofSeconds(15));


    }


}

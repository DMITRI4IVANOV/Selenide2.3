package test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTestForm {
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate nextDate = date.plusDays(3);

    @Test
    void shouldFillInAllTheFields() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        $("[data-test-id='city'] input").setValue("Самара");
        $("[data-test-id='date'] input").doubleClick().sendKeys(formatter.format(nextDate));
        $("[data-test-id='name'] input").setValue("Петров Виталий");
        $("[data-test-id='phone'] input").setValue("+79179778855");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(13));
        $("[data-test-id='notification'] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + formatter.format(nextDate)));
    }
}

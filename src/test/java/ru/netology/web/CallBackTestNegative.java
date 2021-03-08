package ru.netology.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallBackTestNegative {

    @BeforeEach
    void shouldOpenWeb() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRejectRequestIfNameInvalid() {
        $("[data-test-id=name] input").setValue("Ivan");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldRejectRequestIfNameEmpty() {
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldRejectRequestIfPhoneInvalid() {
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+792700000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] [class=input__sub]")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldRejectRequestIfPhoneEmpty() {
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] [class=input__sub]")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldRejectRequestByCheckbox() {
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных " +
                        "и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

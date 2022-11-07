package ru.netology.login.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.login.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement code = $("[data-test-id='code'] input");
    private SelenideElement actionButton = $("[data-test-id='action-verify']");
    private SelenideElement notification = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage() {
    }

    public DashboardPage verify(DataHelper.VerificationCode verificationCode) {
        code.setValue(verificationCode.getVerificationCode());
        actionButton.click();
        return new DashboardPage();
    }

    public void verifyRandomCode(DataHelper.VerificationCode verificationCode) {
        code.setValue(verificationCode.getVerificationCode());
        actionButton.click();
        verificationVisibility();
    }

    public void verificationVisibility() {
        notification.shouldBe(Condition.visible).shouldHave(text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}

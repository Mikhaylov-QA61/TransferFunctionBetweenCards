package steps;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import page.DashboardPage;
import page.LoginPage;
import page.TransferPage;
import page.VerificationPage;

public class TemplateSteps {
    public static DataHelper dataHelper;
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    public static TransferPage transferPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginUser(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        var user = new DataHelper.RegisteredUser(login, password);
        verificationPage = loginPage.validLogin(user);
        var verificationCode = new DataHelper.VerificationCode("12345");
        dashboardPage = verificationPage.validVerify(verificationCode);
    }
    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void makeTransfer(int amount, String numberCard, int indexCard) {
        int amountTransfer = amount;
        var firstCard = DataHelper.getFirstCard();
        var transferPage = dashboardPage.selectCardTotransfer(firstCard);
        var card = new DataHelper.InfoCard(numberCard, "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        transferPage.makeTransfer(String.valueOf(amountTransfer), card);
    }
    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int}")
    public void ActualBalance(int index, int balance) {
        int actualBalance = dashboardPage.getCardBalance(index);
        int expectedBalance = balance;
    }
}

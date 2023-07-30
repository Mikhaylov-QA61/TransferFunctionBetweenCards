package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    public SelenideElement heading = $("[data-test-id=dashboard]");
    public ElementsCollection listCard = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public int getCardBalance(DataHelper.InfoCard infoCard) {
        var text = listCard.findBy(text(infoCard.getNumberCard().substring(15))).getText();
        return extractBalance(text);
    }
     public int getCardBalance(int index){
        var text = listCard.get(index).getText();
        return extractBalance(text);
     }
    public TransferPage selectCardTotransfer(DataHelper.InfoCard infoCard) {
        listCard.findBy(attribute("data-test-id",infoCard.getTestId())).$(".button").click();
        return new TransferPage();
    }
}

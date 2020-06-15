package scapauto;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import scapauto.controller.TradeMarkController;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class App
{
    // Const Field

    /**
     * Site web of where we extract information
     */
    private static final String URL_OBJECTIVE = "https://www.autoevolution.com/";

    public static final Browser browser = new Browser();

    // Field

    private final List<TradeMark> tradeMarks = new ArrayList<>();

    // Construct

    App()
    {
        List<String> nameTradeMarks = TradeMarkController.getNameTradeMarks(URL_OBJECTIVE + "cars/");

        for (String tradeMark : nameTradeMarks) {
            // Format of URL for the page: https://www.autoevolution.com/$trade-mark$/
            extractInformationOfAutos(tradeMark);
            // Wait a small time for avoid will be blocked for the page or the hosting
            Clockmaker.waitFor(3, TimeUnit.SECONDS);
        }
    }

    // Methods

    private void extractInformationOfAutos(final String nameTradeMark)
    {
        try {
            // Format of URL for the page: https://www.autoevolution.com/$trade-mark$/
            HtmlPage page = browser.getPageWithGenerationsOfURL(nameTradeMark.replace(" ", "-") + "/");
            HtmlElement pageWrapper = page.getHtmlElementById("newscol2");
            List<HtmlElement> modelsInProduction = pageWrapper.getByXPath("//div[@class='carmod clearfix ']");
            List<HtmlElement> modelsDiscontinue = pageWrapper.getByXPath("//div[@class='carmod clearfix disc']");

            TradeMark tradeMark = new TradeMark(nameTradeMark.toLowerCase());
            tradeMark.fillFromHtml(modelsInProduction);
            tradeMark.fillFromHtml(modelsDiscontinue);

            Playwright.writeFileJSON(tradeMark, tradeMark.getName());
            tradeMarks.add(tradeMark);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main

    public static void main(String[] args)
    {
        new App();
    }
}

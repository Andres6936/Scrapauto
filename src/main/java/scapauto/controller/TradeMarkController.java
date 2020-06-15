package scapauto.controller;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import scapauto.App;

import java.util.ArrayList;
import java.util.List;

public final class TradeMarkController
{
    public static ArrayList<String> getNameTradeMarks(final String url)
    {
        ArrayList<String> tradeMarks = new ArrayList<>();

        try {
            final HtmlPage page = App.browser.getPage(url);
            final HtmlElement pageWrapper = page.getHtmlElementById("pagewrapper");
            final List<HtmlElement> tradeMarksElement = pageWrapper.getByXPath(
                    "//div[@class='col2width fl bcol-white carman'] " +
                            "[@itemscope] [@itemtype='https://schema.org/Brand']");

            for (final var element : tradeMarksElement) {
                final HtmlElement tradeMarkH5 = element.getFirstByXPath("./h5/a[@*]/span[@itemprop='name']");
                tradeMarks.add(tradeMarkH5.asText().toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tradeMarks;
    }
}

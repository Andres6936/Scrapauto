package scapauto;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App
{
    // Const Field

    /**
     * Site web of where we extract information
     */
    private static final String URL_OBJECTIVE = "https://www.autoevolution.com/";

    private static final WebClient CLIENT = new WebClient();

    // Field

    private final List<String> nameTradeMarks = new ArrayList<>();
    private final List<TradeMark> tradeMarks = new ArrayList<>();

    // Construct

    App()
    {
        CLIENT.getOptions().setCssEnabled(false);
        CLIENT.getOptions().setJavaScriptEnabled(false);

        fillTradeMarks();

        for (String tradeMark : nameTradeMarks) {
            // Format of URL for the page: https://www.autoevolution.com/$trade-mark$/
            extractInformationOfAutos(URL_OBJECTIVE, tradeMark);
            // Wait a small time for avoid will be blocked for the page or the hosting
            waitFor(10, TimeUnit.SECONDS);
        }
    }

    // Methods

    private void extractInformationOfAutos(final String url, final String nameTradeMark)
    {
        try {
            // Format of URL for the page: https://www.autoevolution.com/$trade-mark$/
            HtmlPage page = CLIENT.getPage(url + nameTradeMark.replace(" ", "-") + "/");
            HtmlElement pageWrapper = page.getHtmlElementById("newscol2");
            List<HtmlElement> modelsInProduction = pageWrapper.getByXPath("//div[@class='carmod clearfix ']");
            List<HtmlElement> modelsDiscontinue = pageWrapper.getByXPath("//div[@class='carmod clearfix disc']");

            TradeMark tradeMark = new TradeMark(nameTradeMark.toLowerCase());
            tradeMark.fillFromHtml(modelsInProduction);
            tradeMark.fillFromHtml(modelsDiscontinue);

            writeFileJSON(tradeMark);
            tradeMarks.add(tradeMark);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTradeMarks()
    {
        try {
            HtmlPage page = CLIENT.getPage(URL_OBJECTIVE + "cars/");
            HtmlElement pageWrapper = page.getHtmlElementById("pagewrapper");
            List<HtmlElement> tradeMarksElement = pageWrapper.getByXPath(
                    "//div[@class='col2width fl bcol-white carman'] " +
                            "[@itemscope] [@itemtype='https://schema.org/Brand']");

            for (var element : tradeMarksElement) {
                HtmlElement tradeMarkH5 = element.getFirstByXPath("./h5/a[@*]/span[@itemprop='name']");
                nameTradeMarks.add(tradeMarkH5.asText().toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFileJSON(final TradeMark _tradeMark)
    {
        String formatJSON = JSON.toJSONString(_tradeMark);

        try {
            PrintWriter printWriter = new PrintWriter(_tradeMark.getName() + ".json");
            printWriter.println(formatJSON);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is licensed under The MIT License (MIT) Copyright (c) 2015 Michael Gantman
     * <br><br>
     * <p>
     * This method is a convenience method for sleep that "swallows" {@link InterruptedException}  and
     * has {@link TimeUnit} parameter in addition to time period so it makes it very convenient. So with
     * this method there is no need to convert the time into milliseconds. Just simply write
     * <br><br>
     *
     * <p>{@code waitFor(10, TimeUnit.SECONDS);}</p>
     * <br> No Exception catching needed
     *
     * @param period   time period to sleep
     * @param timeUnit {@link TimeUnit} that specifies in which units the time period is measured
     */
    private void waitFor(final int period, TimeUnit timeUnit)
    {
        try {
            timeUnit.sleep(period);
        } catch (InterruptedException ignored) {
        }
    }

    // Main

    public static void main(String[] args)
    {
        new App();
    }
}

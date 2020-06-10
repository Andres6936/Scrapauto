package scapauto;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Browser extends WebClient
{
    // Final Fields

    /**
     * Site web of where we extract information
     */
    private static final String URL_OBJECTIVE = "https://www.autoevolution.com/";

    // Construct

    public Browser()
    {
        super();

        getOptions().setCssEnabled(false);
        getOptions().setJavaScriptEnabled(false);
    }

    // Methods

    public final Generation getGenerationInformationFrom(final HtmlElement element)
    {
        final String year = element.asText();
        final Generation generation = new Generation(year);

        final String url = element.getAttribute("href");

        try {
            final HtmlPage pageWithInformation = getPage(url);
            final HtmlElement pageWrapper = pageWithInformation.getHtmlElementById("pagewrapper");
            final List<HtmlElement> information = pageWrapper.getByXPath(
                    "//div[@class='enginedata engine-inline']");

            for (final var tags : information) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return generation;
    }

    public final ArrayList<String> getNameTradeMarks(final String url)
    {
        ArrayList<String> tradeMarks = new ArrayList<>();

        try {
            final HtmlPage page = getPage(url);
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

    public HtmlPage getPageWithGenerationsOfURL(final String partialUrl) throws IOException
    {
        return getPage(URL_OBJECTIVE + partialUrl);
    }
}

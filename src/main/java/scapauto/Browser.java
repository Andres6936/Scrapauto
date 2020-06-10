package scapauto;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

    private Engine getEngineInformation(final HtmlElement element)
    {
        final Engine engine = new Engine();
        HtmlElement tag = element.getFirstByXPath("//div[@class='title']");
        engine.setName(tag.asText());
        final List<HtmlElement> information = tag.getByXPath("//dl//dd");
        final Iterator<HtmlElement> it = information.iterator();

        engine.setCylinders(it.next().asText());

        if (verifyIfExistInformationOf("Displacement", tag)) {
            engine.setDisplacement(it.next().asText());
        } else {
            engine.setDisplacement("None");
        }

        if (verifyIfExistInformationOf("Power", tag)) {
            engine.setPower(it.next().asText());
        } else {
            engine.setPower("None");
        }

        engine.setTorque(it.next().asText());
        engine.setFuelSystem(it.next().asText());
        engine.setFuel(it.next().asText());

        return engine;
    }

    private boolean verifyIfExistInformationOf(final String of, final HtmlElement in)
    {
        List<HtmlElement> tags = in.getByXPath("//dl[@title='General Specs']//dt");

        for (final var tag : tags) {
            if (tag.asText().contains(of)) {
                return true;
            }
        }

        return false;
    }

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

            for (final var engineInfo : information) {
                generation.addEngine(getEngineInformation(engineInfo));
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

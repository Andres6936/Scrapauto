package scapauto.controller;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import scapauto.App;
import scapauto.Browser;
import scapauto.Generation;

import java.io.IOException;
import java.util.List;

public final class GenerationController
{
    public static Generation getGenerationInformationFrom(final HtmlElement element)
    {
        final String year = element.asText();
        final Generation generation = new Generation(year);

        final String url = element.getAttribute("href");

        try {
            final HtmlPage pageWithInformation = App.browser.getPage(url);
            final HtmlElement pageWrapper = pageWithInformation.getHtmlElementById("pagewrapper");
            final List<HtmlElement> information = pageWrapper.getByXPath(
                    "//div[@class='enginedata engine-inline']");

            for (final var engineInfo : information) {
                generation.addEngine(EngineController.getEngineInformation(engineInfo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return generation;
    }
}

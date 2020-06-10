package scapauto;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradeMark extends ArrayList<Auto>
{
    // Static Field

    private static final Browser browser = new Browser();

    // Const Field

    private final String name;

    // Constructor

    TradeMark(final String _name)
    {
        name = _name;
    }

    // Methods

    private void extractGenerationsOfAutos()
    {
        for (final var auto : this) {
            final String partialUrl = name + "/" + auto.getAbbreviation();

            try {
                final HtmlPage pageGenerations = browser.getPageWithGenerationsOfURL(partialUrl);
                final HtmlElement pageWrapper = pageGenerations.getHtmlElementById("pagewrapper");
                final List<HtmlElement> divsGenerations = pageWrapper.getByXPath(
                        "//div [@class='container carmodel clearfix']");

                for (final var element : divsGenerations) {
                    final HtmlElement elementYears = element.getFirstByXPath("//p [@class='years']/a");
                    auto.addGeneration(browser.getGenerationInformationFrom(elementYears));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillFromHtml(List<HtmlElement> elements)
    {
        for (var element : elements) {
            HtmlElement nameElement = element.getFirstByXPath(".//h4");
            String nameModel = nameElement.asText();

            HtmlElement typeElement = element.getFirstByXPath(".//p[@class='body']");
            String typeModel = typeElement.asText();

            List<HtmlElement> enginesElement = element.getByXPath(".//p[@class='eng']/span");
            StringBuilder typeEngines = new StringBuilder(40);

            if (enginesElement.size() > 1) {
                for (var engine : enginesElement) {
                    typeEngines.append(engine.asText()).append(" & ");
                }

                // Delete the " & " extra that was added.
                typeEngines.delete(typeEngines.length() - 3, typeEngines.length());
            } else {
                typeEngines.append(enginesElement.get(0).asText());
            }

            this.add(new Auto(nameModel, typeModel, typeEngines.toString()));
        }

        extractGenerationsOfAutos();
    }

    // Getters

    public String getName()
    {
        return name;
    }
}

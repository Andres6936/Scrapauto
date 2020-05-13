package scapauto;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

import java.util.ArrayList;
import java.util.List;

public class TradeMark extends ArrayList<Auto>
{
    // Const Field

    private final String name;

    // Constructor

    TradeMark(final String _name)
    {
        name = _name;
    }

    // Methods

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
    }

    // Getters

    public String getName()
    {
        return name;
    }
}

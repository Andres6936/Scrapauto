package scapauto.controller;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import scapauto.Engine;

import java.util.Iterator;
import java.util.List;

public final class EngineController
{
    private static boolean verifyIfExistInformationOf(final String of, final HtmlElement in)
    {
        List<HtmlElement> tags = in.getByXPath("//dl[@title='General Specs']//dt");

        for (final var tag : tags) {
            if (tag.asText().contains(of)) {
                return true;
            }
        }

        return false;
    }

    public static Engine getEngineInformation(final HtmlElement element)
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
}

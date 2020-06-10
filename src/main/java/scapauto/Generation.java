package scapauto;

import java.util.ArrayList;

public final class Generation
{
    // Final Fields

    private final String years;

    // Fields

    private ArrayList<Engine> engine = new ArrayList<>();

    // Construct

    Generation(final String years)
    {
        this.years = years;
    }

    // Getters

    public String getYears()
    {
        return years;
    }

    public ArrayList<Engine> getEngine()
    {
        return engine;
    }


    // Setters

    public void addEngine(Engine engine)
    {
        this.engine.add(engine);
    }
}

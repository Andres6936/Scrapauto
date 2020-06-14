package scapauto;

import java.util.ArrayList;
import java.util.List;

public final class Auto
{
    // Fields

    private final String model;

    private final String type;

    private final String engine;

    private final ArrayList<Generation> generations = new ArrayList<>();

    // Construct

    Auto(String _model, String _type, String _engine)
    {
        model = _model;
        type = _type;
        engine = _engine;
    }

    // Methods

    public void addGeneration(final Generation generation)
    {
        generations.add(generation);
    }

    @Override
    public String toString()
    {
        return "Auto{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }

    // Getters

    public String getModel()
    {
        return model;
    }

    public String getType()
    {
        return type;
    }

    public String getEngine()
    {
        return engine;
    }

    public ArrayList<Generation> getGenerations()
    {
        return generations;
    }

    public String getAbbreviation()
    {
        // Remember, the model in this point have
        // the trademark is the first string, example:
        // Buggati Divo | Caterhman Seven 620 |
        // Chevrolet Corvette Convertible
        // The method return a string without
        // the initial string of trademark
        // Example: Divo | Seven-620 |
        // Covette Convertible
        final String abbr = model.replace(" ", "-");
        final int firstPositionOfHyphenMinus = abbr.indexOf("-");

        // Sum one for skip the whitespace initial
        return abbr.substring(firstPositionOfHyphenMinus + 1);
    }
}

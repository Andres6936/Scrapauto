package scapauto;

import java.util.List;

public final class Auto
{
    // Fields

    private final String model;

    private final String type;

    private final String engine;

    // Construct

    Auto(String _model, String _type, String _engine)
    {
        model = _model;
        type = _type;
        engine = _engine;
    }

    // Methods

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
        final int firstPositionOfHyphenMinus =
                model.replace(" ", "-").indexOf("-");

        // Sum one for skip the whitespace initial
        return model.substring(firstPositionOfHyphenMinus + 1);
    }
}

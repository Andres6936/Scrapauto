package scapauto;

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
}

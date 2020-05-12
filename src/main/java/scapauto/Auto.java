package scapauto;

public final class Auto
{
    // Fields

    String model;

    String type;

    String engine;

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
}

package scapauto;

public final class Generation
{
    // Final Fields

    private final String years;

    // Fields

    private Engine engine;

    // Construct

    Generation(final String years)
    {
        this.years = years;
    }

    // Getters

    // Setters

    public void setEngine(Engine engine)
    {
        this.engine = engine;
    }
}

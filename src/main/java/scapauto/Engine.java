package scapauto;

public final class Engine
{
    // Fields

    private String name;

    private String cylinders;

    private String displacement;

    private String power;

    private String torque;

    private String fuelSystem;

    private String fuel;

    // Getters

    public String getName()
    {
        return name;
    }

    public String getCylinders()
    {
        return cylinders;
    }

    public String getDisplacement()
    {
        return displacement;
    }

    public String getPower()
    {
        return power;
    }

    public String getTorque()
    {
        return torque;
    }

    public String getFuelSystem()
    {
        return fuelSystem;
    }

    public String getFuel()
    {
        return fuel;
    }


    // Setters


    public void setName(String name)
    {
        this.name = name;
    }

    public void setCylinders(String cylinders)
    {
        this.cylinders = cylinders;
    }

    public void setDisplacement(String displacement)
    {
        this.displacement = displacement;
    }

    public void setPower(String power)
    {
        this.power = power;
    }

    public void setTorque(String torque)
    {
        this.torque = torque;
    }

    public void setFuelSystem(String fuelSystem)
    {
        this.fuelSystem = fuelSystem;
    }

    public void setFuel(String fuel)
    {
        this.fuel = fuel;
    }
}

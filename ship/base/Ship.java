package ship.base;

public class Ship
{
    private String number;
    private Integer height;
    private Double weight;
    private Boolean hasVehicle;
    private Boolean isSpecial;

    public Ship(String shipNumber, Integer shipHeight, Double shipWeight, Boolean shipHasVehicle)
    {
        number = shipNumber;
        height = shipHeight;
        weight = shipWeight;
        hasVehicle = shipHasVehicle;
        isSpecial = false;
    }

    //=========================================================================

    public void setIsSpecial()
    {
        isSpecial = true;
    }

    //=========================================================================

    public String getNumber()
    {
        return number;
    }

    public Integer getHeight()
    {
        return height;
    }

    public Boolean isSpecial()
    {
        return isSpecial;
    }

    public Boolean hasVehicle()
    {
        return hasVehicle;
    }

    //=========================================================================

    Double getWeight()
    {
        return weight;
    }

    //=========================================================================

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Судно с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}
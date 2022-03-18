package ship.base;

import java.util.HashMap;

public class FixSpeed
{
    public static HashMap<String, Integer> shipsSpeed = new HashMap<>();

    public static Ship getNextShip()
    {
        String randomNumber = Double.toString(Math.random()).substring(2, 5);
        Integer randomHeight = (int) (1000 + 10000. * Math.random());
        Double randomWeight = 600 + 10000 * Math.random();
        Ship ship = new Ship(randomNumber, randomHeight, randomWeight, Math.random() > 0.5);
        if(Math.random() < 0.15) {
            ship.setIsSpecial();
        }
        Police.resetCalled();

        return ship;
    }

    public static Integer getShipSpeed(Ship ship)
    {
        String shipNumber = ship.getNumber();
        if(!shipsSpeed.containsKey(shipNumber)) {
            shipsSpeed.put(shipNumber, (int) (80 * Math.random()));
        }
        return shipsSpeed.get(shipNumber);
    }
}
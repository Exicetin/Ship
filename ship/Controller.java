package ship;

import ship.base.FixSpeed;
import ship.base.Police;
import ship.base.Ship;
import ship.base.WeightMeter;

public class Controller
{
	public static Double lightBoatMaxWeight = 10000.0; //макс. вес легкого судна
    public static Integer lightBoatMaxHeight = 7000; // макс. допустимая высота судна
    public static Integer stationMaxHeight = 5000; // макс. высота контр. пункта

    public static Integer lightBoatPrice = 100; // RUB
    public static Integer cargoBoatPrice = 250; // грузовое судно, плата за проезд
    public static Integer AdditionalPrice = 200; // если вес превышает допустимый

    public static Integer maxAccessSpeed = 50; // km/h
    public static Integer speedFineStep = 20; // km/h
    public static Integer finePerGrade = 500; // RUB
    public static Integer dangerSpeed = 50; // km/h

    public static void main(String[] args)
    {
        for(Integer i = 0; i < 10; i++)
        {
            Ship ship = FixSpeed.getNextShip();
            System.out.println(ship);
            System.out.println("Скорость: " + FixSpeed.getShipSpeed(ship) + " км/ч");

            /**
             * Пропускаем спец. суда 
             */


            /**
             * Проверка на наличие номера в списке номеров нарушителей
             */
            Boolean policeCalled = false;
            for(String criminalNumber : Police.getCriminalNumbers())
            {
                String shipNumber = ship.getNumber();
                if(shipNumber.equals(criminalNumber)) {
                    Police.call("судно нарушителя с номером " + shipNumber);
                    blockWay("Немедленно остановитесь!");
                    break;
                }
            }
            if(Police.wasCalled()) {
                continue;
            }

            if(ship.isSpecial()) {
                openWay();
                continue;
            }

            /**
             * Проверяем высоту и массу судна, вычисляем стоимость проезда
             */
            Integer shipHeight = ship.getHeight();
            Integer price = 0;
            if(shipHeight > stationMaxHeight)
            {
                blockWay("высота вашего судна превышает высоту пропускного пункта!");
                continue;
            }
            else if(shipHeight > lightBoatMaxHeight)
            {
                Double weight = WeightMeter.getWeight(ship);
                //Грузовое судно
                if(weight > lightBoatMaxWeight)
                {
                    price = lightBoatPrice;
                    if(ship.hasVehicle()) {
                        price = price + AdditionalPrice;
                    }
                }
                //Пассажирское судно
                else {
                    price = lightBoatPrice;
                }
            }
            else {
                price = cargoBoatPrice;
            }

            /**
             * Проверка скорости судна и выставление штрафа
             */
            Integer shipSpeed = FixSpeed.getShipSpeed(ship);
            if(shipSpeed > dangerSpeed)
            {
                Police.call("cкорость судна - " + shipSpeed + " км/ч, номер - " + ship.getNumber());
                blockWay("вы значительно превысили скорость. Ожидайте полицию!");
                continue;
            }
            else if(shipSpeed > maxAccessSpeed)
            {
                Integer overSpeed = shipSpeed - maxAccessSpeed;
                Integer totalFine = finePerGrade * (1 + overSpeed / speedFineStep);
                System.out.println("Вы превысили скорость! Штраф: " + totalFine + " руб.");
                price = price + totalFine;
            }

            /**
             * Отображение суммы к оплате
             */
            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }

    }

    /**
     * Сбрасываение якоря
     */
    public static void openWay()
    {
        System.out.println("Сбрасывается якорь... Счастливого пути!");
    }

    public static void blockWay(String reason)
    {
        System.out.println("Движение невозможно: " + reason);
    }
}

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //  Create parking spots
        List<ParkingSpot> spots = new ArrayList<>();
        spots.add(new ParkingSpot(1, SpotType.SMALL, false));
        spots.add(new ParkingSpot(2, SpotType.MEDIUM, false));
        spots.add(new ParkingSpot(3, SpotType.LARGE, false));
        spots.add(new ParkingSpot(4, SpotType.ELECTRIC, true));
        spots.add(new ParkingSpot(5, SpotType.MEDIUM, false));

        //  Strategies
        SlotAllocationStrategy allocationStrategy = new NearestSlotStrategy();

        Map<SpotType, Double> hourlyRates = new HashMap<>();
        hourlyRates.put(SpotType.SMALL, 10.0);
        hourlyRates.put(SpotType.MEDIUM, 20.0);
        hourlyRates.put(SpotType.LARGE, 30.0);
        hourlyRates.put(SpotType.ELECTRIC, 25.0);

        PricingStrategy pricingStrategy = new PerHourPricingStrategy(hourlyRates, 5.0);

        //  Gate lists (we pass these lists into ParkingLot so we can register gates later)
        List<EntryGate> entryGates = new ArrayList<>();
        List<ExitGate> exitGates = new ArrayList<>();

        //  Create ParkingLot
        ParkingLot lot = new ParkingLot(100, spots, allocationStrategy, entryGates, exitGates);

        //  Create gates and register them
        EntryGate e1 = new EntryGate(1, lot);
        EntryGate e2 = new EntryGate(2, lot);
        entryGates.add(e1);
        entryGates.add(e2);

        ExitGate x1 = new ExitGate(1, pricingStrategy);
        exitGates.add(x1);

        //  Create some vehicles
        Vehicle v1 = new Vehicle("MH12AB1234", VehicleSize.SMALL, false, false);
        Vehicle v2 = new Vehicle("MH12CD5678", VehicleSize.LARGE, true, true);

        //  Vehicle 1 enters
        System.out.println("Vehicle 1 trying to enter...");
        Ticket t1 = e1.generateTicket(v1);
        if (t1 != null) {
            System.out.println("Ticket generated: id=" + t1.getTicketId() + ", spotType=" + t1.getSpot().getType());
        } else {
            System.out.println("No spot available for vehicle 1");
        }

        //  Vehicle 2 enters
        System.out.println("Vehicle 2 trying to enter...");
        Ticket t2 = e2.generateTicket(v2);
        if (t2 != null) {
            System.out.println("Ticket generated: id=" + t2.getTicketId() + ", spotType=" + t2.getSpot().getType()
                    + ", usingCharging=" + t2.isUsingCharging());
        } else {
            System.out.println("No spot available for vehicle 2");
        }

        

        //  Vehicle 1 exits
        if (t1 != null) {
            System.out.println("Vehicle 1 exiting...");
            double amt1 = x1.processExit(t1);
            System.out.println("Amount to pay for vehicle 1: " + amt1);
        }

        //  Vehicle 2 exits
        if (t2 != null) {
            System.out.println("Vehicle 2 exiting...");
            double amt2 = x1.processExit(t2);
            System.out.println("Amount to pay for vehicle 2: " + amt2);
        }
    }
}

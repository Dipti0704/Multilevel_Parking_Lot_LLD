public class EntryGate {
    private int gateId;
    private ParkingLot lot;

    public EntryGate(int gateId, ParkingLot lot) {
        this.gateId = gateId;
        this.lot = lot;
    }

    public Ticket generateTicket(Vehicle vehicle) {
        ParkingSpot spot = lot.getAvailableSpot(vehicle, gateId);
        if (spot == null) return null;

        boolean usingCharging = vehicle.wantsCharging() && spot.hasChargingPoint();
        spot.park(vehicle);
        return new Ticket((int)(Math.random()*10000), vehicle, spot, usingCharging);
    }
}
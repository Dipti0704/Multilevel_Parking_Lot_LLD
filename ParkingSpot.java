

public class ParkingSpot {
    private int id;
    private SpotType type;
    private boolean isAvailable;
    private Vehicle currentVehicle;
    private boolean hasChargingPoint;

    public ParkingSpot(int id, SpotType type, boolean hasChargingPoint) {
        this.id = id;
        this.type = type;
        this.isAvailable = true;
        this.hasChargingPoint = hasChargingPoint;
    }

    public boolean park(Vehicle vehicle) {
        if (!isAvailable) return false;
        this.currentVehicle = vehicle;
        this.isAvailable = false;
        return true;
    }

    public void vacate() {
        this.currentVehicle = null;
        this.isAvailable = true;
    }

    public boolean isAvailable() { return isAvailable; }
    public SpotType getType() { return type; }
    public boolean hasChargingPoint() { return hasChargingPoint; }
    public Vehicle getCurrentVehicle() { return currentVehicle; }
}

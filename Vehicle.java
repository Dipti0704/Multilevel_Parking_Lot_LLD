

public class Vehicle {
    private String vehicleNumber;
    private VehicleSize size;
    private boolean isElectric;
    private boolean wantsCharging;

    public Vehicle(String vehicleNumber, VehicleSize size, boolean isElectric, boolean wantsCharging) {
        this.vehicleNumber = vehicleNumber;
        this.size = size;
        this.isElectric = isElectric;
        this.wantsCharging = wantsCharging;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public VehicleSize getSize() { return size; }
    public boolean isElectric() { return isElectric; }
    public boolean wantsCharging() { return wantsCharging; }
}

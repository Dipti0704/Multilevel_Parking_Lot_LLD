
import java.util.Date;

public class Ticket {
    private int ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private Date entryTime;
    private boolean isUsingCharging;

    public Ticket(int ticketId, Vehicle vehicle, ParkingSpot spot, boolean isUsingCharging) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = new Date();
        this.isUsingCharging = isUsingCharging;
    }

    public int getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getSpot() { return spot; }
    public Date getEntryTime() { return entryTime; }
    public boolean isUsingCharging() { return isUsingCharging; }
}

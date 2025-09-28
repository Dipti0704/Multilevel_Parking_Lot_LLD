import java.util.List;

public class ParkingLot {
    private int lotId;
    private List<ParkingSpot> spots;
    private SlotAllocationStrategy allocationStrategy;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGates;

    public ParkingLot(int lotId, List<ParkingSpot> spots,
                      SlotAllocationStrategy allocationStrategy,
                      List<EntryGate> entryGates, List<ExitGate> exitGates) {
        this.lotId = lotId;
        this.spots = spots;
        this.allocationStrategy = allocationStrategy;
        this.entryGates = entryGates;
        this.exitGates = exitGates;
    }

    public ParkingSpot getAvailableSpot(Vehicle vehicle, int entryGateId) {
        return allocationStrategy.findSpot(spots, vehicle, entryGateId);
    }
}

import java.util.List;

public class NearestSlotStrategy implements SlotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle, int entryGateId) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                return spot;
            }
        }
        return null;
    }
}
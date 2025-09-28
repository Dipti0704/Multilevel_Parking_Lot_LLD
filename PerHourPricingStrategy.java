import java.util.Map;
import java.util.Date;


public class PerHourPricingStrategy implements PricingStrategy {
    private Map<SpotType, Double> hourlyRates;
    private double chargingFeePerHour;

    public PerHourPricingStrategy(Map<SpotType, Double> hourlyRates, double chargingFeePerHour) {
        this.hourlyRates = hourlyRates;
        this.chargingFeePerHour = chargingFeePerHour;
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        long durationMillis = new Date().getTime() - ticket.getEntryTime().getTime();
        double hours = Math.ceil(durationMillis / (1000.0 * 60 * 60));
        double baseRate = hourlyRates.get(ticket.getSpot().getType());
        double total = hours * baseRate;

        if (ticket.isUsingCharging()) {
            total += hours * chargingFeePerHour;
        }
        return total;
    }
}
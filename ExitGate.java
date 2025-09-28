

public class ExitGate {
    private int gateId;
    private PricingStrategy pricingStrategy;

    public ExitGate(int gateId, PricingStrategy pricingStrategy) {
        this.gateId = gateId;
        this.pricingStrategy = pricingStrategy;
    }

    public double processExit(Ticket ticket) {
        ticket.getSpot().vacate();
        return pricingStrategy.calculatePrice(ticket);
    }
}

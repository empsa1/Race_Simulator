package SimulationElements;

public class Tire {
    private TireType type;
    private double condition;
    public enum TireType {
        SOFT,
        MEDIUM,
        HARD
    }

    public Tire(TireType type) {
        this.type = type;
        this.condition = 100.00;
    }
}

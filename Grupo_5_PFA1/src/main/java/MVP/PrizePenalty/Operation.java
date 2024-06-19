package MVP.PrizePenalty;

public enum Operation {
    ADD("+"),
    SUBTRACT("-"),
    EQUAL("=");

    private final String symbol;  // Field to store the symbol

    // Private constructor to initialize the symbol
    Operation(String symbol) {
        this.symbol = symbol;
    }

    // Getter method to retrieve the symbol
    public String getSymbol() {
        return symbol;
    }
}

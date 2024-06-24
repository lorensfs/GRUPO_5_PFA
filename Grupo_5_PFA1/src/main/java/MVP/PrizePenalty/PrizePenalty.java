package MVP.PrizePenalty;

public class PrizePenalty {
    private Operation operation;
    private int value;
    private Type type;
    private String description;

    public PrizePenalty(Operation operation, int value, Type type, String description) {
        this.operation = operation;
        this.value = value;
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    //For getting the symbol of Position Enum
    public String getOperationSymbol(){
        return this.operation.getSymbol();
    }

    @Override
    public String toString() {
        return "PrizePenalty{" +
                "operation=" + operation +
                ", value=" + value +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}

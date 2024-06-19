package MVP.PrizePenalty;

/**
 * A simple implementation of a queue data structure using linked nodes.
 * @author Lorenzo
 */
public class PrizePenaltyStack {

    /**
     * Constructs an empty PrizePenaltyStack.
     */
    public PrizePenaltyStack() {}

    private NodePrizePenalty top;

    public NodePrizePenalty getTop() {
        return top;
    }

    public void setTop(NodePrizePenalty top) {
        this.top = top;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     * @author Lorenzo
     */
    public boolean empty() {
        return getTop() == null;
    }

    /**
     * Pushes a new node with the given data onto the stack.
     *
     * @param data the data to be stored in the new node
     * @author Lorenzo
     */
    public void push(PrizePenalty data) {
        NodePrizePenalty nodePrizePenaltyS = new NodePrizePenalty();
        nodePrizePenaltyS.setData(data);
        if (!empty()) {
            nodePrizePenaltyS.setNext(this.top);
        }
        this.top = nodePrizePenaltyS;
    }

    /**
     * Removes and returns the top element (node) from the stack.
     *
     * @return the removed top element from the stack, or null if the stack is empty
     * @author Lorenzo
     */
    public NodePrizePenalty pop() {
        if (empty()) {
            return null;
        } else {
            NodePrizePenalty temp;
            temp = getTop();
            this.top = temp.getNext();
            return temp;
        }
    }
    /**
     * Retrieves the data at the top of the stack without removing it.
     *
     * @return the data at the top of the stack, or null if the stack is empty
     * @author Lorenzo
     */
    public String peek() {
        if (empty()) {
            return null;
        } else {
            return this.top.getData().toString();
        }
    }

    /**
     * Método = retornarTamaño
     * Returns the number of elements currently in the stack.
     *
     * @return the number of elements in the stack
     * @author Lorenzo
     */
    public int size() {
        int contador = 0;
        NodePrizePenalty temp = getTop();
        while (temp != null) {
            contador++;
            temp = temp.getNext();
        }
        return contador;
    }

    /**
     * @return a string representation of the stack
     * @author Lorenzo
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        NodePrizePenalty temp = getTop();
        while (temp != null) {
            s.append(temp.getData().toString()).append("\n");
            temp = temp.getNext();
        }
        return s.toString();
    }
}
package MVP.PrizePenalty;

/**
 * The `NodePrizePenalty` class represents a node in a linked list.
 * Each node holds a participant and a reference to the next node in the list.
 *
 * @author Lorenzo
 */
public class NodePrizePenalty {

    /**
     * Constructs a new node with null data and null next reference.
     */
    public NodePrizePenalty() {
        this.data = null;
        this.next = null;
    }

    private PrizePenalty data;
    private NodePrizePenalty next;

    public PrizePenalty getData() {
        return data;
    }

    public void setData(PrizePenalty data) {
        this.data = data;
    }

    public NodePrizePenalty getNext() {
        return next;
    }


    public void setNext(NodePrizePenalty next) {
        this.next = next;
    }

    /**
     * @return a string representation of the node's data
     */
    @Override
    public String toString() {
        return "Penalty/Rewards: " + getData().toString();
    }
}

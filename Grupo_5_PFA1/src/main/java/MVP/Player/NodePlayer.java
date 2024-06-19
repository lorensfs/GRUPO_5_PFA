package MVP.Player;

public class NodePlayer {

    /**
     * Constructs a new node with null data and null next reference.
     *
     * @author lorenzo
     */
    public NodePlayer() {
        this.data = null;
        this.next = null;
    }

    private Player data;
    private NodePlayer next;

    public Player getData() {
        return data;
    }

    public void setData(Player data) {
        this.data = data;
    }

    public NodePlayer getNext() {
        return next;
    }


    public void setNext(NodePlayer next) {
        this.next = next;
    }

    /**
     * @return a string representation of the node's data
     */
    @Override
    public String toString() {
        return "Player: " + getData().toString();
    }
}
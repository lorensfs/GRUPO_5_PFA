package MVP.Player;

/**
 * A simple implementation of a stack data structure using linked nodes.
 *
 * @author Lorenzo
 */

public class ReadyPlayerQueue {

    /**
     * Constructs an empty ReadyPlayerQueue.
     */
    public ReadyPlayerQueue() {}

    private NodePlayer head;
    private NodePlayer tail;

    public NodePlayer getHead() {
        return head;
    }

    public void setHead(NodePlayer head) {
        this.head = head;
    }

    public NodePlayer getTail() {
        return tail;
    }

    public void setTail(NodePlayer tail) {
        this.tail = tail;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     * @author Lorenzo
     */
    public boolean empty() {
        return this.head == null;
    }

    /**
     * Adds a new node with the specified data to the end of the queue.
     *
     * @param data the data to be added to the queue
     * @author Lorenzo
     */
    public void offer(Player data) {
        NodePlayer nodePlayerQ = new NodePlayer();
        nodePlayerQ.setData(data);
        nodePlayerQ.setNext(null);
        if (empty()) {
            this.head = nodePlayerQ;
        } else {

            this.tail.setNext(nodePlayerQ);
        }
        this.tail = nodePlayerQ;
    }

    /**
     * Removes and returns the head node of the queue.
     *
     * @return the head node of the queue, or null if the queue is empty
     * @author Lorenzo
     */
    public NodePlayer poll() {
        if (this.head == null) {
            return null;
        }
        NodePlayer nodePlayerQ = this.head;
        this.head = this.head.getNext();
        nodePlayerQ.setNext(null);
        return nodePlayerQ;
    }

    /**
     * Removes the node containing the player with the specified name from the queue and returns the player's name.
     * If no player with the specified name is found, returns null.
     *
     * @param playerName the name of the player to remove from the queue
     * @return the name of the player removed from the queue, or null if the player is not found or the queue is empty
     * @author Lorenzo
     */
    public String poll(String playerName) {
        if (empty()) {
            return null;
        }
        NodePlayer current = getHead();
        NodePlayer previous = null;
        while (current != null) {
            if (current.getData().getName().equals(playerName)) {
                if (previous == null) {
                    this.head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                if (current == getTail()) {
                    this.tail = previous;
                }
                return current.getData().getName();
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    /**
     * Returns the number of elements currently in the queue.
     *
     * @return the size of the queue
     * @author Lorenzo
     */
    public int size() {
        int contador = 0;
        NodePlayer temp = getHead();
        while (temp != null) {
            contador++;
            temp = temp.getNext();
        }
        return contador;
    }

    /**
     * @return a string representation of the queue
     * @author Lorenzo
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        NodePlayer temp = getHead();
        while (temp != null) {
            s.append(temp.getData().toString()).append("\n");
            temp = temp.getNext();
        }
        return s.toString();
    }
}
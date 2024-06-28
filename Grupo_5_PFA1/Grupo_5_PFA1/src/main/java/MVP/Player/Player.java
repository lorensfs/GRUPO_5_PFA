package MVP.Player;

public class Player {

    private String name;
    private int position;

    public Player(String name) {
        this.position = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", current position=" + position +
                '}';
    }
}

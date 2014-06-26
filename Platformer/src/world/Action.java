package world;

/**
 * Created by Glenn on 25/06/2014.
 */
public enum Action {

    NOTHING(0, 0, 0),
    ATTACK(0, 0, 0),
    TELEPORT(100, 100, 60);

    private int value1, value2, length;

    private Action(int value1, int value2, int length) {

        this.value1 = value1;
        this.value2 = value2;
        this.length = length;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getLength() {
        return length;
    }
}

package Tiles;

public class BombTile implements Tile {
    private String type;
    // private int value;
    private boolean isOpen;
    private boolean flagState;

    public BombTile() {
        type = "B";
        // value = -1;
        isOpen = false;
        flagState = false;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getValue() {
        return type;
    }

    public void setValue(int x) {

    }

    public void open() {
        isOpen = true;
    }

    @Override
    public String toString() {
        String res = "";
        if (isOpen)
            res = "\u001B[96m" + getValue() + resetString;
        else if (flagState)
            res = flagString;
        else
            res = " ";
        return res + resetString;
    }

    @Override
    public boolean getOpen() {
        return isOpen;
    }

    @Override
    public void toggleFlag() {
        flagState = !flagState;
    }

    @Override
    public boolean getToggle() {
        return flagState;
    }

}

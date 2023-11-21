package Tiles;

public class NoTile implements Tile {
    private String type;
    private boolean isOpen;
    private boolean flagState;

    public NoTile() {
        type = "-";
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
            res = "\u001B[93m" + "\u2237" + resetString;
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

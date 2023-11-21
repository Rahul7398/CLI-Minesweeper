package Tiles;

public interface Tile {
    public String resetString = "\u001B[0m";
    public String flagString = "\u001B[91m\u2691";

    public String getType();

    public String getValue();

    public void setValue(int x);

    public void open();

    public boolean getOpen();

    public void toggleFlag();

    public boolean getToggle();

}
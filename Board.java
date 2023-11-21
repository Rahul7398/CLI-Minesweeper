import Tiles.*;
import Tiles.Number;

public class Board {
    public Tile[][] mat;
    public int size;
    public int Bombcount;
    public float weight;
    public int[] cur;
    private String[] tutorialString;

    public Board(int size, int level) {
        this.size = size;
        mat = new Tile[size][size];
        float f1 = (float) level / (float) 10;
        weight = 1 + f1;
        Bombcount = (int) (size * weight);
        cur = new int[] { 0, 0 };
        generate();
        tutorialString = new String[8];
        tutorial();
    }

    public void generate() {
        int count = Bombcount;
        while (count != 0) {
            int dim = 0, row = 0, col = 0;

            do {
                dim = (int) (Math.random() * (size * size));
                row = dim / size;
                col = dim % size;
            } while (mat[row][col] != null);
            mat[row][col] = new BombTile();
            count--;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mat[i][j] != null)
                    continue;
                int val = 0;
                val += (i - 1 < 0 || j - 1 < 0) ? 0
                        : (mat[i - 1][j - 1] != null && mat[i - 1][j - 1].getType().equals("B") ? 1 : 0);
                val += (i - 1 < 0) ? 0 : (mat[i - 1][j] != null && mat[i - 1][j].getType().equals("B") ? 1 : 0);
                val += (i - 1 < 0 || j + 1 >= size) ? 0
                        : (mat[i - 1][j + 1] != null && mat[i - 1][j + 1].getType().equals("B") ? 1 : 0);
                val += (j - 1 < 0) ? 0 : (mat[i][j - 1] != null && mat[i][j - 1].getType().equals("B") ? 1 : 0);
                val += (j + 1 >= size) ? 0 : (mat[i][j + 1] != null && mat[i][j + 1].getType().equals("B") ? 1 : 0);
                val += (i + 1 >= size || j - 1 < 0) ? 0
                        : (mat[i + 1][j - 1] != null && mat[i + 1][j - 1].getType().equals("B") ? 1 : 0);
                val += (i + 1 >= size) ? 0 : (mat[i + 1][j] != null && mat[i + 1][j].getType().equals("B") ? 1 : 0);
                val += (i + 1 >= size || j + 1 >= size) ? 0
                        : (mat[i + 1][j + 1] != null && mat[i + 1][j + 1].getType().equals("B") ? 1 : 0);
                Tile t;
                if (val == 0)
                    t = new NoTile();
                else {
                    t = new Number();
                    t.setValue(val);
                }
                mat[i][j] = t;
            }
        }

    }

    public void display() {
        String pre = " ", post = " ";
        System.out.print("\033\143");
        // "\u001B[0m"
        System.out.print("+");
        for (int i = 0; i < size; i++)
            System.out.print("---+");
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print("|");

            for (int j = 0; j < size; j++) {
                if (i == cur[0] && j == cur[1]) {
                    pre = "(";
                    post = ")";
                } else {
                    pre = " ";
                    post = " ";
                }
                System.out.print(pre + mat[i][j] + post + "|");
            }
            if (i < tutorialString.length)
                if (tutorialString[i] != null)
                    System.out.print(" " + tutorialString[i]);
            System.out.println();
            System.out.print("+");
            for (int j = 0; j < size; j++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }

    public boolean open() {
        if (mat[cur[0]][cur[1]].getType().equals("B")) {
            // Bombcount--;
            return false;
        } else if (mat[cur[0]][cur[1]].getType().equals("-"))
            bfs();
        mat[cur[0]][cur[1]].open();
        return true;
    }

    public int getSize() {
        return size;
    }

    public int getBombCount() {
        return Bombcount;
    }

    public int[] getPos() {
        return cur;
    }

    public void setPos(int y, int x) {
        cur = new int[] { y, x };
    }

    public void toggleFlag() {
        if (cur[0] < 0 || cur[0] >= size || cur[1] < 0 || cur[0] >= size)
            return;
        if (mat[cur[0]][cur[1]].getType().equals("B") && !mat[cur[0]][cur[1]].getToggle())
            Bombcount--;
        mat[cur[0]][cur[1]].toggleFlag();
    }

    public void bfs() {
        traverse(getPos()[0], getPos()[1]);
    }

    private void traverse(int r, int c) {
        if (r < 0 || c < 0 || r >= size || c >= size)
            return;
        if (mat[r][c].getType().equals("-") && !mat[r][c].getOpen()) {
            mat[r][c].open();
            traverse(r - 1, c - 1);
            traverse(r - 1, c);
            traverse(r - 1, c + 1);
            traverse(r, c - 1);
            traverse(r, c + 1);
            traverse(r + 1, c - 1);
            traverse(r + 1, c);
            traverse(r + 1, c + 1);
        } else if (mat[r][c].getType().equals("N"))
            mat[r][c].open();
    }

    public void showEndBomb() {
        // String pre = " ", post = " ";
        System.out.print("\033\143");
        // "\u001B[0m" \u001B[96m
        String resetString = "\u001B[0m";
        System.out.print("+");
        for (int i = 0; i < size; i++)
            System.out.print("---+");
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print("|");

            for (int j = 0; j < size; j++) {
                String sop = mat[i][j].toString();
                if (mat[i][j].getType().equals("B")) {
                    if (mat[i][j].getToggle())
                        sop = "\u001B[92m" + "\u2713" + resetString;
                    else
                        sop = "\u001B[91m" + "\u23E3" + resetString;
                }

                System.out.print(" " + sop + " " + "|");
            }
            System.out.println();
            System.out.print("+");
            for (int j = 0; j < size; j++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }

    public void tutorial() {
        String resetString = "\u001B[0m";

        tutorialString[0] = "For \u001B[91m\u2691 use F" + resetString;
        tutorialString[1] = "\u21A9" + "   to select";
        tutorialString[2] = "  🅆 to navigate";
        tutorialString[3] = "🄰 🅂 🄳";
        tutorialString[4] = "Total Mines " + Bombcount;
    }
}

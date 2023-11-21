import java.util.Scanner;

public class Game {
    public Board board;

    public Game(int size, int level) {
        board = new Board(size, level);

    }

    public String start() {
        boolean check = true;
        while (check && board.getBombCount() > 0) {
            board.display();
            int res = getInput();
            if (res == 0) {
                check = board.open();
            }

        }
        board.display();
        board.showEndBomb();
        if (!check) {
            return "You lose the Game " + Integer.toString(board.getBombCount()) + " remains";
        } else
            return "Yow Won the Game";

    }

    private int getInput() {
        // 0-> open, 1-> move, 2-> flag
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        int y = board.getPos()[0], x = board.getPos()[1];
        if (inp.equals(""))
            return 0;
        char c = inp.charAt(0);
        if (c == 'w')
            y--;
        else if (c == 'a')
            x--;
        else if (c == 's')
            y++;
        else if (c == 'd')
            x++;
        else if (c == 'f') {
            board.toggleFlag();
            return 2;
        }
        board.setPos(y, x);
        return 1;
    }

}

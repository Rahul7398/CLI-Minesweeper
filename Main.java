import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Board b = new Board(10);
        // b.display();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter tile size: ");
        int n = Integer.parseInt(sc.nextLine());
        System.out.println("Enter game difficulty level 1-5: ");
        int l = Integer.parseInt(sc.nextLine());
        Game g = new Game(n, l);
        System.out.println(g.start());
    }
}

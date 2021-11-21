import java.util.Scanner;

public class chess {
    public int position[][] = new int[15][15];
    player1 player1 = new player1(position);
    player2 player2 = new player2(position);
    chess()
    {

    }
    public void drawChessBoard() {
        System.out.print("   ");
        for (int i = 1; i <= 15; i++) {
            System.out.print(i);
            System.out.print("   ");
        }
        System.out.println("");
        for (int i = 1; i <= 15; i++) {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        chess chess = new chess();
        chess.drawChessBoard();
    }
}

class player1 {

    int position[][] = new int[15][15];
    String name = "玩家1";

    player1(int[][] position) {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                this.position[i][j] = position[i][j];
    }

    boolean judge() {
        for (int i = 0; i < 15; i++) //判断横行是否形成五子状态
        {
            for (int j = 0; j < 11; j++) {
                if (position[i][j] == 1 && position[i][j + 1] == 1 && position[i][j + 2] == 1 && position[i][j + 3] == 1 && position[i][j + 4] == 1)
                    return true;
            }
        }
        for (int i = 0; i < 11; i++) //判断竖行是否形成了五子状态
        {
            for (int j = 0; j < 15; j++) {
                if (position[i][j] == 1 && position[i + 1][j] == 1 && position[i + 2][j] == 1 && position[i + 3][j] == 1 && position[i + 4][j] == 1)
                    return true;
            }
        }
        for (int i = 0; i < 11; i++) //判断右斜线是否构成五子现象
        {
            for (int j = 0; j < 11; j++) {
                if (position[i][j] == 1 && position[i + 1][j + 1] == 1 && position[i + 2][j + 2] == 1 && position[i + 3][j + 3] == 1 && position[i + 4][j + 4] == 1)
                    return true;
            }
        }
        for (int i = 0; i < 11; i++) //判断右斜线是否构成五子现象
        {
            for (int j = 14; j > 3; j--) {
                if (position[i][j] == 1 && position[i + 1][j - 1] == 1 && position[i + 2][j - 2] == 1 && position[i + 3][j - 3] == 1 && position[i + 4][j - 4] == 1)
                    return true;
            }
        }
        return false;
    }

    void place() {
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int column = input.nextInt();
        position[row - 1][column - 1] = 1;
    }

}

class player2 {
    int position[][] = new int[15][15];
    String name = "玩家2";

    player2(int[][] position) {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                this.position[i][j] = position[i][j];
    }

    boolean judge() {
        for (int i = 0; i < 15; i++) //判断横行是否形成五子状态
        {
            for (int j = 0; j < 11; j++) {
                if (position[i][j] == 2 && position[i][j + 1] == 2 && position[i][j + 2] == 2 && position[i][j + 3] == 2 && position[i][j + 4] == 2)
                    return true;
            }
        }
        for (int i = 0; i < 11; i++) //判断竖行是否形成了五子状态
        {
            for (int j = 0; j < 15; j++) {
                if (position[i][j] == 2 && position[i + 1][j] == 2 && position[i + 2][j] == 2 && position[i + 3][j] == 2 && position[i + 4][j] == 2)
                    return true;
            }
        }
        for (int i = 0; i < 11; i++) //判断右斜线是否构成五子现象
        {
            for (int j = 0; j < 11; j++) {
                if (position[i][j] == 2 && position[i + 1][j + 1] == 2 && position[i + 2][j + 2] == 2 && position[i + 3][j + 3] == 2 && position[i + 4][j + 4] == 2)
                    return true;
            }
        }
        for (int i = 0; i < 11; i++) //判断右斜线是否构成五子现象
        {
            for (int j = 14; j > 3; j--) {
                if (position[i][j] == 2 && position[i + 1][j - 1] == 2 && position[i + 2][j - 2] == 2 && position[i + 3][j - 3] == 2 && position[i + 4][j - 4] == 2)
                    return true;
            }
        }
        return false;
    }

    void place() {
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int column = input.nextInt();
        position[row - 1][column - 1] = 2;
    }

}

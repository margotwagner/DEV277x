import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BattleShips {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Create oceanMap
        char[][] oceanMap = new char[10][10];
        int[] counts = new int[2];

        // Display starting map
        greeting();
        dispMap(oceanMap);

        // Ask the user where they would like to place their ships
        userDeploy(input, oceanMap);
        dispMap(oceanMap);

        // Deploy computer's ships
        compDeploy(random, oceanMap);

        counts = dispMap(oceanMap);

        while (counts[0] != 0 && counts[1] != 0) {
            // Player's turn
            playerTurn(input, oceanMap);
            counts = dispMap(oceanMap);
            if (counts[0] == 0 || counts[1] == 0) {
                break;
            }

            // Comp turn
            compTurn(random, oceanMap);
            dispMap(oceanMap);
            if (counts[0] == 0 || counts[1] == 0) {
                break;
            }
        }

        System.out.println("Game over.");
        if (counts[0] == 0) {
            System.out.println("Sorry, you lose the battle.");
        } else if (counts[1] == 0)  {
            System.out.println("Hooray! You win the battle :)");
        }
    }

    public static void greeting() {
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println();
        System.out.println("Right now, the sea is empty");
        System.out.println();
    }

    public static int[] dispMap(char[][] oceanMap) {
        int rows = oceanMap.length + 2;
        int cols = oceanMap.length + 4;
        int countUser = 0;
        int countComp = 0;
        int[] counts = new int[2];

        System.out.print("  ");
        for (int k = 0; k < 10; k++) {
            System.out.print(k);

        } System.out.print("   \n");

        for (int row = 0; row < 10; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < 10; col++) {
                if (oceanMap[row][col] == '\0' || oceanMap[row-1][col-2] == 'm') {
                    System.out.print(' ');
                } else if (oceanMap[row][col] == '1') {
                    System.out.print('@');
                    countUser++;
                } else if (oceanMap[row][col] == '2') {
                    System.out.print(' '); // change to ' '
                    countComp++;
                } else {
                    System.out.print(oceanMap[row][col]);
                }

            }
            System.out.print("|" + row);
            System.out.println();
        }
        System.out.print("  ");
        for (int k = 0; k < 10; k++) {
            System.out.print(k);

        } System.out.print("   \n");

        System.out.println();
        System.out.println("Your ships: " + countUser + "| Computer ships: " + countComp);

        counts[0] = countUser;
        counts[1] = countComp;
        return counts;
    }

    public static void userDeploy(Scanner input, char[][] oceanMap) {
        System.out.println();
        System.out.println("Deploy your ships:");
        for (int i = 1; i < 6; i++) {
            boolean coordInvalid = true;
            while (coordInvalid) {
                System.out.print("Enter X coordinate for ship " + i + ": ");
                int x = input.nextInt();
                System.out.print("Enter Y coordinate for ship " + i + ": ");
                int y = input.nextInt();

                if (x > 9 || x < 0 ) {
                    System.out.println("X coordinate must be 0-9. Try again.");
                } else if (y > 9 || y < 0) {
                    System.out.println("Y coordinate must be 0-9. Try again.");
                } else if (oceanMap[y][x] == '1') {
                    System.out.println("You already placed a ship there. Try again.");
                } else {
                    oceanMap[y][x] = '1';
                    coordInvalid = false;
                }
            }
        }

    }

    public static void compDeploy(Random random, char[][] oceanMap) {
        System.out.println();
        System.out.println("Computer is deploying ships:");
        for (int i = 1; i < 6; i++) {
            boolean coordInvalid = true;
            while (coordInvalid) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);

                if (oceanMap[y][x] == '1' || oceanMap[y][x] == '2') { coordInvalid = true;}
                else {
                    coordInvalid = false;
                    oceanMap[y][x] = '2';
                    System.out.println("Ship " + i + " DEPLOYED");
                }
            }
        }
        System.out.println("-----------------------------------");
    }

    public static void playerTurn(Scanner input, char[][] oceanMap) {
        System.out.println();
        System.out.println("YOUR TURN");
        int x = -1;
        int y = -1;
        boolean coordInvalid = true;
        while (coordInvalid) {
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();

            if (x > 9 || x < 0 ) {
                System.out.println("X coordinate must be 0-9. Try again.");
            } else if (y > 9 || y < 0) {
                System.out.println("Y coordinate must be 0-9. Try again.");
            } else if (oceanMap[y][x] == '!' || oceanMap[y][x] == 'x' || oceanMap[y][x] == '-') {
                System.out.println("That position has already been guessed. Try again.");
            } else {
                coordInvalid = false;
            }

        }

        if (oceanMap[y][x] == '2')  {
            System.out.println("Boom! You sunk the ship!");
            oceanMap[y][x] = '!';
        } else if (oceanMap[y][x] == '1')   {
            System.out.println("Oh no, you sunk your own ship :(");
            oceanMap[y][x] = 'x';
        } else {
            System.out.println("Sorry, you missed");
            oceanMap[y][x] = '-';
        }


    }

    public static void compTurn(Random random, char[][] oceanMap) {
        System.out.println();
        System.out.println("COMPUTER'S TURN");
        int x = -1;
        int y = -1;
        boolean coordInvalid = true;
        while (coordInvalid) {
            x = random.nextInt(10);
            y = random.nextInt(10);

            if (oceanMap[y][x] == '!' || oceanMap[y][x] == 'x' || oceanMap[y][x] == '-' || oceanMap[y][x] == 'm' ) {
                coordInvalid = true;
            }
            else { coordInvalid = false; }
        }

        if (oceanMap[y][x] == '1')  {
            System.out.println("The Computer sunk one of your ships!");
            oceanMap[y][x] = 'x';
        } else if (oceanMap[y][x] == '1')   {
            System.out.println("The Computer sunk one of its own ships");
            oceanMap[y][x] = '!';
        } else {
            System.out.println("Computer missed");
            oceanMap[y][x] = 'm';
        }
    }
}

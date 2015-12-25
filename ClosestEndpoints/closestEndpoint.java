package closestEndpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class closestEndpoint {

    /*
     * Prints the String [][] table
     */
    static void printTable(String[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
     * Finds the closest endpoints
     */
    static void printClosestEndpoints(String[][] table) {

        /*
         * Handles the edge case when the table is empty
         */
        if (table.length == 0) {
            return;
        }

        int row = table.length;
        int column = table[0].length;

        class Node {

            /*
             * Coordinates of the node.
             */
            int x;
            int y;

            /*
             * Distance
             * 0 : Itself is the endpoint X
             * 1 - infinity distance from the nearest endpoint
             */
            int distance;

            /*
             * Direction
             * 0 : Itself is the endpoint X
             * 1 : Endpoint above ^
             * 2 : Endpoint on Right >
             * 3 : Endpoint Below v
             * 4 : Endpoint on Left <
             */
            int direction;
            String symbol;

            public String toString() {
                return Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(distance) + " "
                        + Integer.toString(direction) + " " + symbol;
            }
        }

        /*
         * Makes sure we visit every potential path to the endpoint once and
         * thus gives us the O(n) running time.
         */
        Queue<Node> potentialEndpoints = new LinkedList<Node>();
        Node[][] finalTable = new Node[row][column];

        /*
         * Scans the complete table once and pushes the endpoints into the
         * queue. The endpoints discovers the nearby nodes and updates them if a
         * shorter path is available through them.
         */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (table[i][j].equalsIgnoreCase("x")) {
                    Node newNode = new Node();
                    newNode.x = i;
                    newNode.y = j;
                    newNode.distance = 0;
                    newNode.direction = 0;
                    newNode.symbol = "x";
                    potentialEndpoints.add(newNode);
                    finalTable[i][j] = newNode;
                }
            }
        }

        /*
         * Check one by one.
         */
        while (!potentialEndpoints.isEmpty()) {
            Node nextNode = potentialEndpoints.poll();

            if (nextNode.x > 0) {
                if (table[nextNode.x - 1][nextNode.y].equalsIgnoreCase("o")) {
                    if (null == finalTable[nextNode.x - 1][nextNode.y]) {
                        Node newNode = new Node();
                        newNode.x = nextNode.x - 1;
                        newNode.y = nextNode.y;
                        newNode.distance = nextNode.distance + 1;
                        newNode.direction = 3;
                        newNode.symbol = "v";
                        potentialEndpoints.add(newNode);
                        finalTable[nextNode.x - 1][nextNode.y] = newNode;
                    }
                    else {
                        Node currentNode = finalTable[nextNode.x - 1][nextNode.y];
                        if ((nextNode.distance + 1) < currentNode.distance) {
                            currentNode.distance = nextNode.distance + 1;
                            currentNode.direction = 3;
                            currentNode.symbol = "v";
                        }
                        else if ((nextNode.distance + 1) == currentNode.distance) {
                            if (3 < currentNode.direction) {
                                currentNode.direction = 3;
                                currentNode.symbol = "v";
                            }
                        }
                    }
                }
            }

            if (nextNode.x < (row - 1)) {
                if (table[nextNode.x + 1][nextNode.y].equalsIgnoreCase("o")) {
                    if (null == finalTable[nextNode.x + 1][nextNode.y]) {
                        Node newNode = new Node();
                        newNode.x = nextNode.x + 1;
                        newNode.y = nextNode.y;
                        newNode.distance = nextNode.distance + 1;
                        newNode.direction = 1;
                        newNode.symbol = "^";
                        potentialEndpoints.add(newNode);
                        finalTable[nextNode.x + 1][nextNode.y] = newNode;
                    }
                    else {
                        Node currentNode = finalTable[nextNode.x + 1][nextNode.y];
                        if ((nextNode.distance + 1) < currentNode.distance) {
                            currentNode.distance = nextNode.distance + 1;
                            currentNode.direction = 1;
                            currentNode.symbol = "^";
                        }
                        else if ((nextNode.distance + 1) == currentNode.distance) {
                            if (3 < currentNode.direction) {
                                currentNode.direction = 1;
                                currentNode.symbol = "^";
                            }
                        }
                    }
                }
            }

            if (nextNode.y > 0) {
                if (table[nextNode.x][nextNode.y - 1].equalsIgnoreCase("o")) {
                    if (null == finalTable[nextNode.x][nextNode.y - 1]) {
                        Node newNode = new Node();
                        newNode.x = nextNode.x;
                        newNode.y = nextNode.y - 1;
                        newNode.distance = nextNode.distance + 1;
                        newNode.direction = 2;
                        newNode.symbol = ">";
                        potentialEndpoints.add(newNode);
                        finalTable[nextNode.x][nextNode.y - 1] = newNode;
                    }
                    else {
                        Node currentNode = finalTable[nextNode.x][nextNode.y - 1];
                        if ((nextNode.distance + 1) < currentNode.distance) {
                            currentNode.distance = nextNode.distance + 1;
                            currentNode.direction = 2;
                            currentNode.symbol = ">";
                        }
                        else if ((nextNode.distance + 1) == currentNode.distance) {
                            if (3 < currentNode.direction) {
                                currentNode.direction = 2;
                                currentNode.symbol = ">";
                            }
                        }
                    }
                }
            }

            if (nextNode.y < (column - 1)) {
                if (table[nextNode.x][nextNode.y + 1].equalsIgnoreCase("o")) {
                    if (null == finalTable[nextNode.x][nextNode.y + 1]) {
                        Node newNode = new Node();
                        newNode.x = nextNode.x;
                        newNode.y = nextNode.y + 1;
                        newNode.distance = nextNode.distance + 1;
                        newNode.direction = 4;
                        newNode.symbol = "<";
                        potentialEndpoints.add(newNode);
                        finalTable[nextNode.x][nextNode.y + 1] = newNode;
                    }
                }
            }

        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (null == finalTable[i][j]) {
                    if (table[i][j].equalsIgnoreCase("o")) {
                        System.out.print("? ");
                    }
                    else {
                        System.out.print(table[i][j] + " ");
                    }
                }
                else {
                    System.out.print(finalTable[i][j].symbol + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputText;
        int rows, columns;
        String[][] table = null;

        try {
            inputText = bufferedReader.readLine();
            String[] dimensionsString = inputText.split(" ");
            rows = Integer.parseInt(dimensionsString[0]);
            columns = Integer.parseInt(dimensionsString[1]);

            table = new String[rows][columns];

            int iterator = 0;
            for (int i = 0; i < rows; i++) {
                inputText = bufferedReader.readLine();
                iterator = 0;
                for (String element : inputText.split(" ")) {
                    table[i][iterator] = element;
                    ++iterator;
                }
            }
        }
        catch (IOException e) {
            System.err.println("Invalid text in input");
        }

        System.out.println("Input Map : ");
        printTable(table);

        System.out.println("Output Map : ");
        printClosestEndpoints(table);
    }
}

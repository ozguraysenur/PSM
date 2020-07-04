import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import javax.swing.JOptionPane;

public class Game implements Runnable {
    private static Game instance;
    private static boolean board[][];
    private static boolean nextBoard[][];
    private static int dim;
    public static File resultFile;
    public static int iterationCounter = 0;
    public static boolean running = false;


    private Game(String fileName, int sizeOfBoard) {
        dim = sizeOfBoard;
        board = readFromFile(fileName);
        resultFile = new File("Result.txt");
        nextBoard = readFromFile(fileName);
    }

    public Game() {
    }

    public static void setRunning() {
        if (running == true)
            running = false;
        else
            running = true;
    }

    public static void init(String fileName, int dim) {
        if (instance == null)
            instance = new Game(fileName, dim);

        // Czyszczenie Result.txt
        PrintWriter writer;
        try {
            writer = new PrintWriter("Result.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
        }

        JOptionPane.showMessageDialog(null, "            Game of Life. \n \n"
                + "                      by \n \n"
                + "          Aysenur Ozgur");

        runGame();
    }

    private static boolean[][] readFromFile(String fileName) {
        File myFile = new File(fileName);
        boolean[][] newBoard = new boolean[dim][dim];
        int counter = 0;
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line = line.replaceAll(" ", "");
                for (int i = 0; i < dim; i++) {
                    if (line.charAt(i) == '-')
                        newBoard[counter][i] = false;
                    else if (line.charAt(i) == 'O')
                        newBoard[counter][i] = true;
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            new JOptionPane();
            JOptionPane.showMessageDialog(null, "File not found!");
        }
        return newBoard;
    }

    private static void writeToFile(boolean tab[][]) {

        String content = "";

        content += "Iteration number" + iterationCounter + "\n";
        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < tab[i].length; j++) {

                if (tab[i][j] == true)
                    content += "O";
                else if (tab[i][j] == false)
                    content += "-";

                content += " ";
            }
            content += "\n";
        }

        content += "\n";

        try (FileOutputStream fop = new FileOutputStream(resultFile, false)) {
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void runGame() {

        writeToFile(board);
        System.out.println("Iteration number: " + iterationCounter);
        drawConsoleBoard(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == true) {
                    willBeDead(i, j);
                } else {
                    willBeAlive(i, j);
                }
            }

        }
        board = nextBoard;

        nextBoard = new boolean[dim][dim];

        iterationCounter++;

    }

    private static void drawConsoleBoard(boolean[][] board) {

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == true)
                    System.out.print("O");
                else if (board[i][j] == false)
                    System.out.print("-");

                System.out.print(" ");
            }

            System.out.println();
        }
    }

    private static void willBeDead(int i, int j) {
        int counter = 0;

        if ((i > 0) && (board[i - 1][j] == true))
            counter++;
        if (j > 0 && board[i][j - 1] == true)
            counter++;
        if (((i > 0) && (j > 0)) && board[i - 1][j - 1] == true)
            counter++;
        if (i < dim - 1 && board[i + 1][j] == true)
            counter++;
        if (j < dim - 1 && board[i][j + 1] == true)
            counter++;
        if (((i < dim - 1) && (j < dim - 1)) && board[i + 1][j + 1] == true)
            counter++;
        if (((i < dim - 1) && (j > 0)) && board[i + 1][j - 1] == true)
            counter++;
        if ((i > 0 && j < dim - 1) && board[i - 1][j + 1] == true)
            counter++;
        // if(i==3 &&j==3)
        // System.out.println("dead: " +counter);

        if (counter < 2 || counter > 3)
            nextBoard[i][j] = false;
        else
            nextBoard[i][j] = board[i][j];

    }

    private static void willBeAlive(int i, int j) {

        int counter = 0;
        if ((i > 0) && (board[i - 1][j] == true))
            counter++;
        if (j > 0 && board[i][j - 1] == true)
            counter++;
        if (((i > 0) && (j > 0)) && board[i - 1][j - 1] == true)
            counter++;
        if (i < dim - 1 && board[i + 1][j] == true)
            counter++;
        if (j < dim - 1 && board[i][j + 1] == true)
            counter++;
        if (((i < dim - 1) && (j < dim - 1)) && board[i + 1][j + 1] == true)
            counter++;
        if (((i < dim - 1) && (j > 0)) && board[i + 1][j - 1] == true)
            counter++;
        if ((i > 0 && j < dim - 1) && board[i - 1][j + 1] == true)
            counter++;
        // System.out.println("alive: "+counter);

        if (counter == 3)
            nextBoard[i][j] = true;

    }

    @Override
    public void run() {
        init("src\\Default.txt", 10);
    }

    public static void checkLoops() {

        File myFile = new File("result.txt");
        String fileContent = "";
        try {
            Scanner sc = new Scanner(myFile);

            while (sc.hasNextLine()) {

                fileContent += sc.nextLine() + "\n";
            }
            sc.close();
        } catch (FileNotFoundException e) {
            new JOptionPane();
            JOptionPane.showMessageDialog(null, "File not found!");
        }

        String dotsSplitter = "";

        for(int i = 0; i < dim; i++)
            dotsSplitter += ".";

        fileContent = fileContent.replaceAll("Iteration number", "");
        fileContent = fileContent.replaceAll("\\s", "");
        String[] iterations = fileContent.split("[0-9]+");

        boolean isLooped = false;
        String matched = "";
        int matchedIndex = 0;

        mainloop: for (int i = 1; i < iterations.length; i++) {

            Pattern pattern = Pattern.compile(iterations[i]);
            Matcher match = pattern.matcher(fileContent);
            int matcherCount = 0;

            while(match.find())
                matcherCount++;

            if(matcherCount > 1) {
                isLooped = true;
                matched = iterations[i];
                matchedIndex = i;
                break mainloop;
            }
        }

        int nextMatchIndex = 0;

        for (int i = matchedIndex+1; i < iterations.length; i++) {

            if(iterations[i].equals(matched)){
                nextMatchIndex = i;
                break;
            }
        }
    }

}
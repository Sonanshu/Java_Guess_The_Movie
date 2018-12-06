import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;

public class GuessTheMovie {

    private ArrayList<String> arr;
    private File movieList;
    private Scanner scanFile;
    Random random = new Random();
    private char[] moviePrint;
    public char[] duplicateMoviePrint;

    public GuessTheMovie() throws FileNotFoundException {
        arr = new ArrayList<String>();
        movieList = new File("MovieList.txt");
        scanFile = new Scanner(movieList);
    }
public char[] getMoviePrintArray(){
        return moviePrint;
}
    public char[] getDuplicateMoviePrintArray(){
        return duplicateMoviePrint;
    }

    public void insertMovieFromFile() {
        String movie;
        while (scanFile.hasNextLine()) {
            movie = scanFile.nextLine();
            //System.out.println(movie);
            arr.add(movie);
        }
    }

    public String selectMovieAtRandom() {
        int select = random.nextInt(arr.size());
        // System.out.println(arr.size());
        //System.out.println(select);
        return arr.get(select - 1);
    }

    public char[] storeMovieInCharArray() {
        String receivedMovie = selectMovieAtRandom();
        // System.out.println(receivedMovie);
        moviePrint = receivedMovie.toCharArray();
//        System.out.print("moviePrint: ");
//        for(char ch:moviePrint){
//
//            System.out.print(ch);}
       return moviePrint;
    }

    public void displayGrid() {
        char space = ' ';
        duplicateMoviePrint = new char[moviePrint.length];
        for (int i = 0; i < moviePrint.length; i++) {
            if (moviePrint[i] == space) {
                duplicateMoviePrint[i] = '/';
                //  System.out.print(" ");
            } else {
                duplicateMoviePrint[i] = '_';
                // System.out.print(" ");
            }
        }
    }

    public void display(char[] arr) {
        for (char character : arr) {
            System.out.print(character + " ");
        }
        System.out.println();

    }

    public void matchMovie(char alphabet) {
        char wronge = ' ';
        boolean flag = false;
        for (int i = 0; i < duplicateMoviePrint.length; i++) {
            if (moviePrint[i] == alphabet) {
                duplicateMoviePrint[i] = alphabet;
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("Wrong: " + alphabet);
        }
        display(duplicateMoviePrint);

    }

    public boolean didWon() {
        char[] temp = duplicateMoviePrint;
        for (int i = 0; i < moviePrint.length; i++) {
            if (temp[i] == '/') {
                temp[i] = ' ';
            }
        }
        if (Arrays.equals(moviePrint, temp)) {
            // System.out.println("didWon: true");
            return true;
        } else {
            // System.out.println("didWon: false");
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        GuessTheMovie obj = new GuessTheMovie();
        //obj.displayMovieGrid();
        obj.insertMovieFromFile();
        obj.storeMovieInCharArray();
        obj.displayGrid();
        obj.display(obj.getDuplicateMoviePrintArray());
        System.out.println("Welcome to Guess the Movie Game!\nLets get started\nHere is your challenge");
        Scanner scan = new Scanner(System.in);
        char alphabet;
        for (int i = 10; (i > 0 && obj.didWon() != true); i--) {
            System.out.println("You have " + i + " chances left!\nEnter your guess: ");
            alphabet = scan.next().trim().charAt(0);
            obj.matchMovie(alphabet);
        }
        if (obj.didWon()) {
            System.out.println("\nYOU WIN!");
            // break;
        } else {
            System.out.print("\nYOU LOST!\nMovie was: ");
            obj.display(obj.getMoviePrintArray());
        }
    }
}

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;

public class main {
    private static final int target = 24;

    private static Scanner scanner = new Scanner(System.in);
    public static void main (String[] args) throws IOException {
        // Numbers container
        String[] numberChoiceCollection = new String[4];


        System.out.println("Enter 4 numbers (separated by single space): ");
        for (int i = 0; i < numberChoiceCollection.length; i++) {
            numberChoiceCollection[i] = scanner.next();
        }

        // Show choosen numbers
        System.out.print("Choosen number: ");
        for (String number : numberChoiceCollection) {
            System.out.print(number + " ");
        }
    }


    private static void printArr (String[] arr) {
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
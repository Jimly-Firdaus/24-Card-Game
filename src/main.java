import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


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

        // Map to new dynamic ArrayList to append all permutation of the 4 numbers
        ArrayList<String> numberChoiceArr = new ArrayList<>(Arrays.asList(numberChoiceCollection));
        ArrayList<ArrayList<String>> permutationArr = new ArrayList<>();
        permutations(numberChoiceArr, new ArrayList<String>(), permutationArr);
    }

    /**
     * Give all the permutation of the given array
     * @param choosenNumArr given raw array
     * @param perm container for current permutation
     * @param permutationArr array containing all permutations
     * */
    public static void permutations(ArrayList<String> choosenNumArr, ArrayList<String> perm, ArrayList<ArrayList<String>> permutationArr) {
        if (choosenNumArr.size() == 0) {
            permutationArr.add(perm);
        } else {
            for (int i = 0; i < choosenNumArr.size(); i++) {
                ArrayList<String> newItems = new ArrayList<>(choosenNumArr);
                String newItem = newItems.remove(i);
                ArrayList<String> newPerm = new ArrayList<>(perm);
                newPerm.add(newItem);
                permutations(newItems, newPerm, permutationArr);
            }
        }
    }

    private static void printArr (String[] arr) {
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
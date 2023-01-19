import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

public class main {
    private static final int target = 24;

    // Total combination of braces
    private static final int total_method = 5;
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

        // Solution container
        Set<String> answerSet = new LinkedHashSet<>();


        for (int i = 1; i <= total_method; i++) {
            for (ArrayList<String> numberChoice : permutationArr) {
                // Solve for every permutation of the 4 numbers
                solve(numberChoice.get(0), numberChoice.get(1), numberChoice.get(2), numberChoice.get(3), i, answerSet);
            }
        }

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

    /**
     * Solve for 24 game
     * @param num_1, num_2, num_3, num_4 numbers
     * @param method combinations of braces
     * @param answerSet set containing all solutions
     * */
    private static void solve (
            String num_1,
            String num_2,
            String num_3,
            String num_4,
            int method,
            Set<String> answerSet) {
        String[] operators = {"+", "-", "/", "*"};

        boolean generated_two_or_three_occurences = false;
        for (int i = 0; i < operators.length; i++) {
            String[] calculationResult = new String[2];
            String format = "", result = "";
            if (generated_two_or_three_occurences) {
                // for operators with no occurences
                boolean switchOpr = false;
                int position1 = i + 1 == operators.length ? 0 : i + 1;
                int position2 = position1 + 1 == operators.length ? 0 : position1 + 1;
                while (!switchOpr) {
                    String pos1 = operators[i];
                    String pos2 = operators[position1];
                    String pos3 = operators[position2];
                    calculation(num_1, num_2, num_3, num_4, pos1, pos2, pos3, method, calculationResult);
                    format = calculationResult[0];
                    result = calculationResult[1];
                    position2++;
                    if (position2 == operators.length) {
                        position1++;
                        position2 = 0;
                    }
                    if (Math.round(Double.parseDouble(result)) == target) {
                        answerSet.add(format);
                    }
                    if (position1 == operators.length) {
                        switchOpr = true;
                    }
                }

            }
            // for operators with 2-3 occurences
            else {
                int position = 1;
                int currentOpr = 0;
                int currentOpr2 = currentOpr;
                boolean changeOpr = false;
                while (!changeOpr) {
                    String pos1 = position == 1 ? operators[currentOpr] : operators[i];
                    String pos2 = position == 2 ? operators[currentOpr] : operators[i];
                    String pos3 = position == 3 ? operators[currentOpr] : operators[i];

                    calculation(num_1, num_2, num_3, num_4, pos1, pos2, pos3, method, calculationResult);
                    format = calculationResult[0];
                    result = calculationResult[1];

                    if (Math.round(Double.parseDouble(result)) == target) {
                        answerSet.add(format);
                    }
                    position++;
                    // if reached end of opr position
                    if (position == 4) {
                        // reset position & change opr
                        position = 1;
                        if (currentOpr == 3) {
                            changeOpr = true;
                        } else {
                            currentOpr++;
                        }
                        if (operators[i] == operators[3] && changeOpr == true) {
                            generated_two_or_three_occurences = true;
                            i = -1;
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculate given numbers & operators with selected method
     * @param num_1, num_2, num_3, num_4 given numbers
     * @param pos1, pos2, pos3 given operators
     * @param method selected method
     * @param calculationResult array containing format & calculation result
     * */
    private static void calculation (
            String num_1,
            String num_2,
            String num_3,
            String num_4,
            String pos1,
            String pos2,
            String pos3,
            int method,
            String[] calculationResult) {
        String format = "", result = "";
        switch (method) {
            case 1:
                format = "(" + "(" + num_1 + pos1 + num_2 + ")" + pos2 + num_3 + ")" + pos3 + num_4;
                result = evalExpr(evalExpr(evalExpr(num_1 + " " + pos1 + " " + num_2) + " " + pos2 + " " + num_3) + " " + pos3 + " " + num_4);
                break;
            case 2:
                format = "(" + num_1 + pos1 + "(" + num_2 + pos2 + num_3 + ")" + ")" + pos3 + num_4;
                result = evalExpr(evalExpr(num_1 + " " + pos1 + " " + evalExpr(num_2 + " " + pos2 + " " + num_3)) + " " + pos3 + " " + num_4);
                break;
            case 3:
                format = "(" + num_1 + pos1 + num_2 + ")" + pos2 + "(" + num_3 + pos3 + num_4 + ")";
                result = evalExpr(evalExpr(num_1 + " " + pos1 + " " + num_2) + " " + pos2 + " " + evalExpr(num_3 + " " + pos3 + " " + num_4));
                break;
            case 4:
                format = num_1 + pos1 + "(" + "(" + num_2 + pos2 + num_3 + ")" + pos3 + num_4 + ")";
                result = evalExpr(num_1 + " " + pos1 + " " + evalExpr(evalExpr(num_2 + " " + pos2 + " " + num_3) + " " + pos3 + " " + num_4));
                break;
            case 5:
                format = num_1 + pos1 + "(" + num_2 + pos2 + "(" + num_3 + pos3 + num_4 + ")" + ")";
                result = evalExpr(num_1 + " " + pos1 + " " + evalExpr(num_2 + " " + pos2 + " " + evalExpr(num_3 + " " + pos3 + " " + num_4)));
                break;
            default:
                format = "";
                result = "";
        }
        calculationResult[0] = format;
        calculationResult[1] = result;
    }

    private static void printArr (String[] arr) {
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
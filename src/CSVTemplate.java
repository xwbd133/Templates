import java.io.*;
import java.util.*;

/**
 * The standard template file for importing csv files.
 * @author Xing Wei
 */
public class CSVTemplate {

    /**
     * The input dataset from csv file.
     */
    public static Object[][] input_dataset;

    /**
     * Create a csv file from given dataset.
     * @param array the given dataset
     * @param fileName the output file name
     */
    public static void output_file(Object[][] array, String fileName) {
        System.out.println(array.length);
        System.out.println(fileName);
    }

    /**
     * Check if a string is numeric.
     * @param value the string being checked
     * @return is numeric or isn't numeric
     */
    public static boolean isNumeric(String value) {
        if (value == null)
            return false;
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Generate the 2d array from input csv file.
     * @param fileName the name of the input csv file
     * @return the 2d array of data
     */
    public static Object[][] fill(String fileName) {
        int rows = 0;
        int cols = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            cols = reader.readLine().split(",").length;
            while (reader.readLine() != null) rows++;
            rows++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] output = new Object[rows][cols];
        String line;
        int count;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            count = 0;
            while ((line = reader.readLine()) != null) {
                String[] temp1 = line.split(",");
                Object[] temp2 = new Object[temp1.length];
                for (int i = 0; i < temp1.length; i++) {
                    if (isNumeric(temp1[i])) {
                        temp2[i] = Integer.parseInt(temp1[i]);
                    } else {
                        temp2[i] = temp1[i];
                    }
                }
                output[count] = temp2;
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Generate the complete file name given project name and csv file name.
     * @param projectName the name of the project
     * @param fileName the name of the csv file
     * @return the complete file name
     */
    public static String generateFilename(String projectName, String fileName) {
        return "C:\\Users\\Xing Wei\\IdeaProjects\\Data\\" + projectName + "\\" + fileName + ".csv";
    }

    /**
     * Get the subarray of an array with a given range between begin and end.
     * @param arr the given array
     * @param begin the beginning index
     * @param end the end index
     * @return the subarray
     */
    public static Object[] subArray(Object[] arr, int begin, int end) {
        Object[] output = new Object[end - begin + 1];
        System.arraycopy(arr, begin, output, 0, output.length);
        return output;
    }

    /**
     * Convert an array into a 2d matrix.
     * @param arr given array
     * @return 2d matrix
     */
    public static Object[][] convert_array_twoD(Object[] arr) {
        int size = (int) Math.sqrt(arr.length);
        Object[][] output = new Object[size][size];
        for (int i = 0; i < arr.length; i++)
            output[i/size][i%size] = arr[i];
        return output;
    }

    /**
     * Print the specified region of a 2d matrix.
     * @param matrix the 2d matrix
     * @param x_1 the starting index of horizontal direction
     * @param x_2 the ending index of horizontal direction
     * @param y_1 the starting index of vertical direction
     * @param y_2 the ending index of vertical direction
     */
    public static void printMatrix(Object[][] matrix, int x_1, int x_2, int y_1, int y_2) {
        for (int i = y_1; i < y_2; i++) {
            for (int j = x_1; j < x_2; j++)
                System.out.printf("%3s ", matrix[i][j]);
            System.out.println();
        }
    }

    /**
     * The main execution configuration.
     * @param args A string array containing the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============================================================================================");
        System.out.println("                             Welcome to csv file converter.                                 ");
        System.out.println("============================================================================================");
        System.out.print(" Please enter your project name: ");
        String project_name = scanner.next();
        System.out.println("============================================================================================");
        System.out.print("    Please enter your file name: ");
        String file_name = scanner.next();
        String file = generateFilename(project_name, file_name);
        input_dataset = fill(file);
        System.out.println("============================================================================================");

        System.out.println(input_dataset[0][1]);
        output_file(input_dataset, "Templates");
        printMatrix(convert_array_twoD(subArray(input_dataset[1], 1, input_dataset[1].length - 1)), 0, 28, 0, 28);

        System.out.println("============================================================================================");
        System.out.println("           ©2022 Xing Wei, Eidgenössische Technische Hochschule Zürich. All rights reserved.");
        System.out.println("============================================================================================");
    }
}

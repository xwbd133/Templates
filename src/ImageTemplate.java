import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * The standard template file for converting image files.
 * @author Xing Wei
 */
public class ImageTemplate {

    /**
     * Convert all given file type images in a project folder.
     * @param projectName the name of the project
     * @param fileType the type of the image file
     */
    public static void convertAll(String projectName, String fileType) {
        File folder = new File("C:\\Users\\Xing Wei\\IdeaProjects\\Data\\" + projectName);
        File[] temp = folder.listFiles();
        ArrayList<File> contents = new ArrayList<>();
        assert temp != null;
        for (File f : temp) {
            if (f.getName().substring(f.getName().length() - 3).equals(fileType))
                contents.add(f);
        }
        for (File f : contents) {
            convert(projectName, f.getName());
        }
    }

    /**
     * Convert the given jpg/png image into png/jpg image.
     * @param projectName the name of the project
     * @param fileName the name of the image file
     */
    public static void convert(String projectName, String fileName) {
        int nameLength = fileName.length();
        File folder = new File("C:\\Users\\Xing Wei\\IdeaProjects\\Data\\Images\\" + projectName);
        File[] allFiles = folder.listFiles();
        File exact = new File(folder.getAbsolutePath() + "temp.jpg");
        String fileType = "";
        assert allFiles != null;
        for (File f : allFiles) {
            if (f.getName().substring(0, nameLength).equals(fileName)) {
                exact = f;
                fileType = f.getName().substring(f.getName().length() - 3);
            }
        }
        String outputType = "";
        if (fileType.equals("jpg"))
            outputType = "png";
        else if (fileType.equals("png"))
            outputType = "jpg";
        String outFile = "C:\\Users\\Xing Wei\\IdeaProjects\\Data\\Images\\" + projectName + "\\" + outputType.toUpperCase() + "output" + "." + outputType;
        try {
            File output = new File(outFile);
            BufferedImage image = ImageIO.read(exact);
            BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
            ImageIO.write(result, outputType, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main execution configuration.
     * @param args A string array containing the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============================================================================================");
        System.out.println("                            Welcome to image file converter.                                ");
        System.out.println("============================================================================================");
        System.out.print("          Please enter your project name: ");
        String project_name = scanner.next();
        System.out.println("============================================================================================");
        System.out.print(" Do you wish to convert all images? (y/n) ");
        String choice = scanner.next();
        System.out.println("============================================================================================");
        if (choice.equals("y")) {
            System.out.print("       Type of image you wish to convert: ");
            String fileType = scanner.next();
            convertAll(project_name, fileType);
        } else if (choice.equals("n")) {
            System.out.print("             Please enter your file name: ");
            String file_name = scanner.next();
            convert(project_name, file_name);
        }
        System.out.println("============================================================================================");
        System.out.println("           ©2022 Xing Wei, Eidgenössische Technische Hochschule Zürich. All rights reserved.");
        System.out.println("============================================================================================");
    }
}

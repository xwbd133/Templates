import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {

    public static RGBCoordinate[][] imageData;

    public static void read(String fileName) {
        int nameLength = fileName.length();
        File folder = new File("C:\\Users\\Xing Wei\\IdeaProjects\\Data\\Images\\ImageReader");
        File[] allFiles = folder.listFiles();
        File exact = new File(folder.getAbsolutePath() + "temp.jpg");
        assert allFiles != null;
        for (File f : allFiles) {
            if (f.getName().substring(0, nameLength).equals(fileName))
                exact = f;
        }
        try {
            BufferedImage image = ImageIO.read(exact);
            int width = image.getWidth();
            int height = image.getHeight();
            int[] data = image.getRGB(0, 0, width, height, null, 0, width);
            int[][] matrix = new int[height][width];
            for (int i = 0; i < data.length; i++)
                matrix[i/height][i%height] = data[i];
            imageData = new RGBCoordinate[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(matrix[i][j]);
                    RGBCoordinate temp = new RGBCoordinate(c.getRed(), c.getGreen(), c.getBlue());
                    imageData[i][j] = temp;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        read("test");
    }
}

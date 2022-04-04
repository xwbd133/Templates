public class RGBCoordinate {

    public int red;
    public int green;
    public int blue;

    public RGBCoordinate(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public void print() {
        System.out.println("[" + this.red + ", " + this.green + ", " + this.blue + "]");
    }
}

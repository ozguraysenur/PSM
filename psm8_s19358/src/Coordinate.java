public class Coordinate {

    public double x;
    public double y;
    public double alfa;

    public Coordinate(double x, double y, double alfa) {
        this.x = x;
        this.y = y;
        this.alfa = alfa;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

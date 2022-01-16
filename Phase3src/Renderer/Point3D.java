package Renderer;

import java.awt.Point;

public class Point3D {
    public double x, y, z;
    public Point point2D;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.point2D = PointConverter.convertPoint(x, y, z);
    }

    public int getAverageX() {
        return 0;
    }
    

}

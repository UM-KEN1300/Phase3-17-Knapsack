package Renderer;

import java.awt.Point;

public class PointConverter {

    public static double scale = 1;
    private static final double ZoomFactor=1.2;
   
    public static void zoomIn(){
        scale*=ZoomFactor;
    }

    public static void zoomOut(){
        scale/=ZoomFactor;
    }

    public static Point convertPoint(double x, double y, double z) {
        double x3d = y * scale;
        double y3d = z * scale;
        double depth = x * scale;
        double[] newVal = scale(x3d, y3d, depth);
        int x2d = (int) (Display.WIDTH / 2 + newVal[0]);
        int y2d = (int) (Display.HEIGHT / 2 - newVal[1]);
        return new Point(x2d, y2d);
    }

    private static double[] scale(double x3d, double y3d, double depth) {
        double dist = Math.sqrt(Math.pow(x3d, 2) + Math.pow(y3d, 2));
        double theta = Math.atan2(y3d, x3d);
        double depth2 = 15 - depth;
        double localscale = Math.abs(1400 / (depth2 + 1400));
        dist *= localscale;
        double[] newVal = new double[2];
        newVal[0] = dist * Math.cos(theta);
        newVal[1] = dist * Math.sin(theta);
        return newVal;
    }
    public static void rotateAxisX(Point3D p, boolean CW, double degrees) {
        double radius = Math.sqrt(p.y * p.y + p.z * p.z);
        double theta = Math.atan2(p.z, p.y);
        theta += 2 * Math.PI / 360 * degrees * (CW?-1:1);
        p.y = radius * Math.cos(theta);
        p.z = radius * Math.sin(theta);
    }
    
    public static void rotateAxisY(Point3D p, boolean CW, double degrees) {
        double radius = Math.sqrt(p.x * p.x + p.z * p.z);
        double theta = Math.atan2(p.x, p.z);
        theta += 2 * Math.PI / 360 * degrees * (CW?-1:1);
        p.z = radius * Math.cos(theta);
        p.x = radius * Math.sin(theta);
    }
        
    public static void rotateAxisZ(Point3D p, boolean CW, double degrees) {
        double radius = Math.sqrt(p.x * p.x + p.y * p.y);
        double theta = Math.atan2(p.y, p.x);
        theta += 2 * Math.PI / 360 * degrees * (CW?-1:1);
        p.x = radius * Math.cos(theta);
        p.y = radius * Math.sin(theta);
    }
}

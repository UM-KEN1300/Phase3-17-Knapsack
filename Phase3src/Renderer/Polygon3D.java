package Renderer;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.awt.Color;

public class Polygon3D {

    private static final double AmbientLight = 0.01;
    public Point3D[] points;
    public Color baseColor, ligthingColor;

    public Polygon3D(Color color, Point3D... points) {
        this.baseColor = this.ligthingColor = color;
        this.points = new Point3D[points.length];
        for (int i = 0; i < points.length; i++) {
            Point3D p3d = points[i];
            this.points[i] = p3d;
        }
    }

    public Polygon3D(Point3D... points) {
        this.baseColor = this.ligthingColor = Color.white;
        this.points = new Point3D[points.length];
        for (int i = 0; i < points.length; i++) {
            Point3D p3d = new Point3D(points[i].x, points[i].y, points[i].z);
            this.points[i] = p3d;
        }
    }

    public void draw(Graphics g) {
        Polygon poly = new Polygon();
        for (int i = 0; i < this.points.length; i++) {
            Point3D p3d = this.points[i];
            Point p2d = PointConverter.convertPoint(p3d.x, p3d.y, p3d.z);
            poly.addPoint(p2d.x, p2d.y);
        }
        g.setColor(this.ligthingColor);
        g.fillPolygon(poly);
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector3D lightVector3d) {
        for (int i = 0; i < this.points.length; i++) {
            PointConverter.rotateAxisX(this.points[i], CW, xDegrees);
            PointConverter.rotateAxisY(this.points[i], CW, yDegrees);
            PointConverter.rotateAxisZ(this.points[i], CW, zDegrees);
        }
        this.updateLighting(lightVector3d);
    }

    public double getAverageX() {
        double sum = 0;
        for (Point3D p : this.points) {
            sum += p.x;
        }
        return sum / this.points.length;
    }

    public void setColor(Color color) {
        this.ligthingColor= color;
    }

    public static Polygon3D[] sortPoligons(Polygon3D[] polygons) {
        List<Polygon3D> polygonList = new ArrayList<Polygon3D>();

        for (Polygon3D poly : polygons) {
            polygonList.add(poly);
        }
        Collections.sort(polygonList, new Comparator<Polygon3D>() {
            @Override
            public int compare(Polygon3D p1, Polygon3D p2) {
                return p2.getAverageX() - p1.getAverageX() < 0 ? 1 : -1;
            }
        });

        for (int i = 0; i < polygons.length; i++) {
            polygons[i] = polygonList.get(i);
        }

        return polygons;
    }

    private void updateLighting(Vector3D lightVector3d) {
        if (this.points.length < 3) {
            return;
        }
        Vector3D v1 = new Vector3D(this.points[0], this.points[1]);
        Vector3D v2 = new Vector3D(this.points[1], this.points[2]);
        Vector3D normal = Vector3D.normalize(Vector3D.cross(v2, v1));
        double dot = Vector3D.dot(normal, lightVector3d);
        double sign = dot < 0 ? -1 : 1;
        dot = sign * dot * dot;
        dot = (dot + 1) / 2 * 0.8;

        double lightRatio = Math.min(1, Math.max(0,AmbientLight + dot));
        this.updateLigthingColor(lightRatio);
    }

    private void updateLigthingColor(double ligthRatio) {
        // System.out.println("flag");
        int red = (int) (this.baseColor.getRed() * ligthRatio);
        int green = (int) (this.baseColor.getGreen() * ligthRatio);
        int blue = (int) (this.baseColor.getBlue() * ligthRatio);
        this.ligthingColor = new Color(red, green, blue);
        // System.out.println(ligthingColor.toString());
    }
}

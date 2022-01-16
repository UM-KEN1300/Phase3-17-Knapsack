package Renderer;

import java.awt.*;

public class Tetrahedron {
    private Polygon3D[] polygons;
    public Color color;

    public Tetrahedron(Color color, Polygon3D... polygons) {
        this.color = color;
        this.polygons = polygons;
        this.sortPolygons();
        this.setPolygonColor();
    }
    public Tetrahedron(Polygon3D... polygons) {
        this.polygons = polygons;
        this.sortPolygons();
        this.setPolygonColor();
    }

    public void draw(Graphics g) {
        for (Polygon3D p : this.polygons) {
            p.draw(g);
        }
        this.sortPolygons();
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector3D lightVector) {
        for (Polygon3D p : this.polygons) {
            p.rotate(CW, xDegrees, yDegrees, zDegrees, lightVector);
        }
        this.sortPolygons();
        this.setPolygonColor();
    }

    private void sortPolygons() {
        Polygon3D.sortPoligons(this.polygons);
    }

    public void setPolygonColor() {
        for (Polygon3D p : this.polygons) {
            p.setColor(color);
        }
    }
    public void setColor (Color color){
        this.color = color;
        this.setPolygonColor();
    }
}

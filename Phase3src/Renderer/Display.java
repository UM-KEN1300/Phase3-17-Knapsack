package Renderer;

import javax.swing.*;

//Renderer class, the main class which renders the 3D Container object into the frame. 
//https://www.youtube.com/watch?v=DQedlivHAPc&list=PLgRPwj3No0VLXFoqYnL2aYhczXB2qwKvp&ab_channel=MeanRollerCoding is used to buld 
//the 3D engine used to display the container, also used to build methods which can allow user to rotate and zoom on to the container 
//As much as they want 
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Vector;

public class Display extends Canvas implements Runnable {
    private Thread thread;
    public JFrame frame;
    private int[][][] container;
    private static String title = "3D Renderer";
    protected final static int HEIGHT = 1000;
    protected final static int WIDTH = 800;
    private static boolean running = false;
    private ArrayList<Tetrahedron> tetraArr = new ArrayList<>();
    private Tetrahedron tetra;
    private Tetrahedron tetra2;

    private final Vector3D lightVector = Vector3D.normalize(new Vector3D(1, 1, 1));

    private Mouse mouse;

    public Display(int[][][] container) {
        this.frame = new JFrame();
        this.frame.setSize(WIDTH, HEIGHT);
        this.container = container;

        this.mouse = new Mouse();

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }

    public synchronized void start() {
        running = true;
        this.thread = new Thread(this, "display");
        this.thread.start();

    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Tetrahedron> tetraConstructors() {
        ArrayList<Tetrahedron> tetraArr = new ArrayList<>();
        int s = 14;
        Point3D containerPoint1 = new Point3D(0, 0, 0);
        Point3D containerPoint2 = new Point3D(0, 0, 0);
        Point3D containerPoint3 = new Point3D(0, 0, 0);
        Point3D containerPoint4 = new Point3D(0, 0, 0);
        Point3D containerPoint5 = new Point3D(0, 0, 0);
        Point3D containerPoint6 = new Point3D(0, 0, 0);
        Point3D containerPoint7 = new Point3D(0, 0, 0);
        Point3D containerPoint8 = new Point3D(0, 0, 0);

        for (int i = 0; i < container.length; i++) { // As we increase, we go more to the front
            double xCenter = i * s;
            for (int j = 0; j < container[0].length; j++) { // As we increase, we go more to the right
                double yCenter = j * s;
                for (int k = 0; k < container[0][0].length; k++) { // As we increase we go more to the top
                    double zCenter = k * s;
                    Color color = colorOfID(container[i][j][k]);

                    Point3D p1 = new Point3D(xCenter - s / 2, yCenter - s / 2, zCenter - s / 2); // Back, left, bottom
                    Point3D p2 = new Point3D(xCenter - s / 2, yCenter - s / 2, zCenter + s / 2); // Back, left, top
                    Point3D p3 = new Point3D(xCenter - s / 2, yCenter + s / 2, zCenter - s / 2); // Back, right, bottom
                    Point3D p4 = new Point3D(xCenter - s / 2, yCenter + s / 2, zCenter + s / 2); // Back, right, top
                    Point3D p5 = new Point3D(xCenter + s / 2, yCenter - s / 2, zCenter - s / 2); // Front, left, bottom
                    Point3D p6 = new Point3D(xCenter + s / 2, yCenter - s / 2, zCenter + s / 2); // Front, left, top
                    Point3D p7 = new Point3D(xCenter + s / 2, yCenter + s / 2, zCenter - s / 2); // Front, right, bottom
                    Point3D p8 = new Point3D(xCenter + s / 2, yCenter + s / 2, zCenter + s / 2); // Front, right, top

                    int xEnd = container.length - 1;
                    int yEnd = container[0].length - 1;
                    int zEnd = container[0][0].length - 1;

                    if (i == 0 && j == 0 && k == 0) {
                        containerPoint1 = p1;
                    } else if (i == 0 && j == 0 && k == zEnd) {
                        containerPoint2 = p2;
                    } else if (i == 0 && j == yEnd && k == 0) {
                        containerPoint3 = p3;
                    } else if (i == 0 && j == yEnd && k == zEnd) {
                        containerPoint4 = p4;
                    } else if (i == xEnd && j == 0 && k == 0) {
                        containerPoint5 = p5;
                    } else if (i == xEnd && j == 0 && k == zEnd) {
                        containerPoint6 = p6;
                    } else if (i == xEnd && j == yEnd && k == 0) {
                        containerPoint7 = p7;
                    } else if (i == xEnd && j == yEnd && k == zEnd) {
                        containerPoint8 = p8;
                    }

                    Polygon3D poly1 = new Polygon3D(p5, p6, p8, p7);
                    Polygon3D poly2 = new Polygon3D(p2, p4, p8, p6);
                    Polygon3D poly3 = new Polygon3D(p3, p4, p8, p7);
                    Polygon3D poly4 = new Polygon3D(p1, p2, p6, p5);
                    Polygon3D poly5 = new Polygon3D(p1, p2, p4, p3);
                    Polygon3D poly6 = new Polygon3D(p1, p3, p7, p5);

                    Tetrahedron tetra = new Tetrahedron(color, poly1, poly2, poly3, poly4, poly5, poly6);

                    tetraArr.add(tetra);
                }
            }
        }
        Polygon3D containerPoly1 = new Polygon3D(containerPoint5, containerPoint6, containerPoint8, containerPoint7);
        Polygon3D containerPoly2 = new Polygon3D(containerPoint2, containerPoint4, containerPoint8, containerPoint6);
        Polygon3D containerPoly3 = new Polygon3D(containerPoint3, containerPoint4, containerPoint8, containerPoint7);
        Polygon3D containerPoly4 = new Polygon3D(containerPoint1, containerPoint2, containerPoint6, containerPoint5);
        Polygon3D containerPoly5 = new Polygon3D(containerPoint1, containerPoint2, containerPoint4, containerPoint3);
        Polygon3D containerPoly6 = new Polygon3D(containerPoint1, containerPoint3, containerPoint7, containerPoint5);
        Tetrahedron tetraContainer = new Tetrahedron(new Color(0, 0, 0, 40), containerPoly1, containerPoly2,
                containerPoly3, containerPoly4, containerPoly5, containerPoly6);
        tetraArr.add(tetraContainer);
        for (Tetrahedron t : tetraArr) {
        t.rotate(true, 10.0, 10.0, 10.0, lightVector);
        }
        return tetraArr;
    }

    public static Color colorOfID(int colorid) {
        switch (colorid) {
            case -1:
                return new Color(0, 0, 0, 15);
            case 0:
                return Color.red;
            case 1:
                return Color.blue;
            case 2:
                return Color.green;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.PINK;
            case 20:
                return Color.black;
        }
        return Color.white;

    }

    @Override
    public void run() {
        long timer = System.currentTimeMillis();
        int frames = 0;
        this.tetraArr = tetraConstructors();
        while (running) {
            render();
            update();
            frames++;
            if (System.currentTimeMillis() - timer > 10) {
                timer += 1000;
                this.frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);

        for (Tetrahedron t : tetraArr) {
            t.draw(g);
        }
        g.dispose();
        bs.show();

    }

    ClickType prevMouse = ClickType.unknown;
    int initialX, initialY;
    double mouseSensitivity = 2.5;

    private void update() {
        int x = this.mouse.getX();
        int y = this.mouse.getY();
        if (this.mouse.getButton() == ClickType.LeftClick) {
            int xDif = x - initialX;
            int yDif = y - initialY;
            for (Tetrahedron t : tetraArr) {
                t.rotate(true, 0, -yDif / mouseSensitivity, -xDif / mouseSensitivity, lightVector);
            }
        } else if (this.mouse.getButton() == ClickType.RightClick) {
            int xDif = x - initialX;
            for (Tetrahedron t : tetraArr) {
                t.rotate(true, -xDif / mouseSensitivity, 0, 0, lightVector);

            }
        } else if (this.mouse.getButton() == ClickType.ScrollClick) {
            for (Tetrahedron t : tetraArr) {
                t.rotate(true, 5.0, 5.0, 5.0, lightVector);
            }

        }
        if (this.mouse.isScrollingUp()) {
            PointConverter.zoomIn();
        } else if (this.mouse.isScrollingDown()) {
            PointConverter.zoomOut();
        }
        this.mouse.resetScroll();
        initialX = x;
        initialY = y;
    }

    public void setState(int[][][] container) {
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[i].length; j++) {
                this.container[i][j] = container[i][j];
            }
        }
        repaint();
    }
}

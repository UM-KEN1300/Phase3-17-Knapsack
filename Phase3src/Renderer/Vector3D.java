package Renderer;

public class Vector3D {
    public double x, y, z;
    public Vector3D()
    {
        this.x=this.y=this.z=0;
    }

    public Vector3D(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public Vector3D(Point3D p1, Point3D p2){
        this.x=p2.x-p1.x;
        this.y=p2.y-p1.y;
        this.z=p2.z-p1.z;
    }

    public static  double dot(Vector3D normal, Vector3D ligthVector){
        return normal.x*ligthVector.x+normal.y*ligthVector.y+normal.z*ligthVector.z;
    }

    public static Vector3D cross(Vector3D v1, Vector3D v2){
        return new Vector3D(
            v1.y*v2.z-v1.z*v2.y,
            v1.z*v2.x-v1.x*v2.z,
            v1.x*v2.y-v1.y*v2.x);
    }

    public static Vector3D normalize(Vector3D v){
    double magnitude=Math.sqrt(v.x*v.x+v.y*v.y + v.z*v.z);
    return new Vector3D(v.x/magnitude, v.y/magnitude, v.z/magnitude);
    }
}

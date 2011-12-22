package rtifinal.graphics;

/*
 Extremely simple class to
 hold each 3D vertex
 */

import processing.core.*;

public class Point3D {

    public float x, y, z;
// constructors
    public Point3D() {
    }

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

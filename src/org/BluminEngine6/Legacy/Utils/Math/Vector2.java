package org.BluminEngine6.Legacy.Utils.Math;


import javax.vecmath.Vector2f;
import java.io.Serializable;

public class Vector2  implements Serializable {

    public float x;
    public float y;

    public Vector2(float x, float y) {
        set(x,y);
    }

    public Vector2(Vector3 vec) {
        set(vec.x,vec.y);
    }

    public float GetX() {
        return x;
    }
    public float GetY() {
        return y;
    }


    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 Zero = new Vector2(0,0);
    public static Vector2 NULL = new Vector2(-1231245542,-1231245542);

    public Vector2f GetAsVec3f() {
        Vector2f f = new Vector2f();
        f.x =x;
        f.y = y;
        return f;
    }

    public Vector3 GetAsVec3() {
        Vector3 f = new Vector3(x,y,0);
        return f;
    }

    @Override
    public String toString() {
        return "Vector2(" +x + "," + y+")";
    }
}

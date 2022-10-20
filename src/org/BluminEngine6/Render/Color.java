package org.BluminEngine6.Render;

import org.BluminEngine6.Legacy.Utils.Math.Vector2;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;

import java.io.Serializable;


public class Color implements Serializable {

    float r,g,b,a;

    public static final Color White = new Color(1,1,1,1);
    public static final Color Black = new Color(0,0,0,1);
    public static final Color Red = new Color(1,0,0,1);
    public static final Color Green = new Color(0,1,0,1);
    public static final Color Blue = new Color(0,0,1,1);
    public static final Color Yellow = new Color(0,1,1,1);
    public static final Color Purple = new Color(1,0,1,1);
    public Color(float r, float g, float b, float a){
        set(r,g,b,a);
    }

    public Color(Vector2 vec){
        set(vec.x, vec.y,0, 1);
    }
    public Color(Vector3 vec){
        set(vec.x, vec.y,vec.z, 1);
    }

    public float GetR() {
        return r;
    }
    public float GetG() {
        return g;
    }
    public float GetB() {
        return b;
    }
    public float GetA() {
        return a;
    }

    public float[] getAsArray() {
        float array[] = {
                r,
                g,
                b,
                a
        };
        return array;
    }
    public void set(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public String toString() {
        return "Color(" + r + "," + g +","+ b+ ", " + a + ")";
    }

    public Vector3 ToVec3() {
        return new Vector3(r,g,b);
    }
}
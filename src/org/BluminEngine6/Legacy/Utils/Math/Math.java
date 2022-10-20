package org.BluminEngine6.Legacy.Utils.Math;

import org.BluminEngine6.Legacy.Utils.Version;


public class Math {

    public static Vector2 Plus(Vector2 a, Vector2 b) {
        return new Vector2((a.x + b.x), (a.y+b.y));
    }
    public static Vector3 Plus(Vector2 a, Vector3 b) {
        return new Vector3((a.x + b.x), (a.y+b.y), b.z);
    }
    public static Vector3 Plus(Vector3 a, Vector2 b) {
        return new Vector3((a.x + b.x), (a.y+b.y), a.z);
    }
    public static Vector3 Plus(Vector3 a, Vector3 b) {
        return new Vector3((a.x + b.x), (a.y+b.y), (a.z+b.z));
    }

    public static Vector2 Minus(Vector2 a, Vector2 b) {
        return new Vector2((a.x - b.x), (a.y-b.y));
    }
    public static Vector3 Minus(Vector2 a, Vector3 b) {
        return new Vector3((a.x - b.x), (a.y-b.y), b.z);
    }
    public static Vector3 Minus(Vector3 a, Vector2 b) {
        return new Vector3((a.x - b.x), (a.y-b.y), a.z);
    }
    public static Vector3 Minus(Vector3 a, Vector3 b) {
        return new Vector3((a.x - b.x), (a.y-b.y), (a.z-b.z));
    }

    public static Vector2 Times(Vector2 a, Vector2 b) {
        return new Vector2((a.x * b.x), (a.y*b.y));
    }
    public static Vector3 Times(Vector2 a, Vector3 b) {
        return new Vector3((a.x * b.x), (a.y*b.y), b.z);
    }
    public static Vector3 Times(Vector3 a, Vector2 b) {
        return new Vector3((a.x * b.x), (a.y*b.y), a.z);
    }
    public static Vector3 Times(Vector3 a, Vector3 b) {
        return new Vector3((a.x * b.x), (a.y*b.y), (a.z*b.z));
    }

    public static Vector2 Devid(Vector2 a, Vector2 b) {
        return new Vector2((a.x / b.x), (a.y/b.y));
    }
    public static Vector3 Devid(Vector2 a, Vector3 b) {
        return new Vector3((a.x / b.x), (a.y/b.y), b.z);
    }
    public static Vector3 Devid(Vector3 a, Vector2 b) {
        return new Vector3((a.x / b.x), (a.y/b.y), a.z);
    }
    public static Vector3 Devid(Vector3 a, Vector3 b) {
        return new Vector3((a.x / b.x), (a.y/b.y), (a.z/b.z));
    }

    public static Vector2 Modual(Vector2 a, Vector2 b) {
        return new Vector2((a.x % b.x), (a.y%b.y));
    }
    public static Vector3 Modual(Vector2 a, Vector3 b) {
        return new Vector3((a.x % b.x), (a.y%b.y), b.z);
    }
    public static Vector3 Modual(Vector3 a, Vector2 b) {
        return new Vector3((a.x % b.x), (a.y%b.y), a.z);
    }
    public static Vector3 Modual(Vector3 a, Vector3 b) {
        return new Vector3((a.x % b.x), (a.y%b.y), (a.z%b.z));
    }

    public static boolean Equals(Version a, Version b) {
        if(a.r == b.r) {
            if(a.a == b.a) {
                if(a.b == b.b) {
                    if(a.p == b.p) {
                        if(a.ext.equals(b.ext)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean Equals(Vector3 a, Vector3 b) {
        if(a.x == b.x) {
            if(a.y == b.y) {
                if(a.z == b.z) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean Equals(Vector3 a, Vector2 b) {
        if(a.x == b.x) {
            if(a.y == b.y) {
                return true;
            }
        }
        return false;
    }
    public static boolean Equals(Vector2 a, Vector3 b) {
        if(a.x == b.x) {
            if(a.y == b.y) {
                return true;
            }
        }
        return false;
    }
    public static boolean Equals(Vector2 a, Vector2 b) {
        if(a.x == b.x) {
            if(a.y == b.y) {
                return true;
            }
        }
        return false;
    }

    public static Matrix multiply(Matrix matrix, Matrix other) {
        Matrix result = Matrix.identity();
        for (int i = 0; i < Matrix.SIZE; i++) {
            for (int j = 0; j < Matrix.SIZE; j++) {
                result.Set(i, j, matrix.Get(i, 0) * other.Get(0, j) +
                        matrix.Get(i, 1) * other.Get(1, j) +
                        matrix.Get(i, 2) * other.Get(2, j) +
                        matrix.Get(i, 3) * other.Get(3, j));
            }
        }
        return result;
    }

}



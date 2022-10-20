package org.BluminEngine6.Render;

import org.BluminEngine6.Legacy.Utils.Math.*;

import java.io.Serializable;

/**
 * Class used for models Vertex data handling/Buffering
 * @author Vrglab
 */
public class Vertex implements Serializable {
    private Vector3 position, normals = Vector3.Zero;
    private Vector2 texturePosition = Vector2.NULL;
    private Color color;


    public Vertex(Vector3 pos, Vector2 texpos) {
        position = pos;
        texturePosition = texpos;
        color = new Color(1,1,1,1);
    }
    public Vertex(Vector3 pos, Vector3 norm, Vector2 texpos) {
        position = pos;
        texturePosition = texpos;
        normals = norm;
        color = new Color(1,1,1,1);
    }
    public Vertex(Vector3 pos, Color c, Vector2 texpos, Vector3 norm) {
        position = pos;
        texturePosition = texpos;
        normals = norm;
        color = c;
    }
    public Vertex(Vector3 pos) {
        position = pos;
    }


    public Vector3 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Vector2 getTexturePosition() {
        return texturePosition;
    }

    public Vector3 getNormals() {
        return normals;
    }
}

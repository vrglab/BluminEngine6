package org.BluminEngine6.Editor.Componants;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Math.Matrix;
import org.BluminEngine6.Object.Component;
import org.BluminEngine6.Legacy.Rendering.Color;

public class Camera extends Component {

    public float fov = 90;
    public float FarPlane = 10000.0f;
    public float NearPlane = 0.1f;
    private Matrix projectionMatrix;
    public Color clearColor = new Color(0.5f, 0.3f, 0.7f, 1);

    public Camera() {
        tag = Application.getTagMannager().GetTag(1);
    }

    public Matrix getProjectionMatrix() {
        return projectionMatrix;
    }

    @Override
    public void Update() {
        transform = Parent.transform;

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        projectionMatrix = Matrix.projection(fov,
                (Application.getDisplay().getCurentScreenRes().getWIDTH() / Application.getDisplay().getCurentScreenRes().getHIGHT()) + 0.7f,
                NearPlane,FarPlane);
    }

    @Override
    public void Awake() {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {

    }
}

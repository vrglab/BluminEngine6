package org.BluminEngine6.Editor.Componants;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Math.Matrix;
import org.BluminEngine6.Object.Component;

public class Camera extends Component {


    public float fov = 90;
    public float FarPlane = 10000.0f;
    public float NearPlane = 0.1f;
    private Matrix projectionMatrix;

    public Camera() {
        transform.position.z = 0;
    }

    public Matrix getProjectionMatrix() {
        return projectionMatrix;
    }

    @Override
    public void Update() {

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
    public void PreInit() {

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

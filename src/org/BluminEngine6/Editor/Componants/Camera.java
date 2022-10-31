package org.BluminEngine6.Editor.Componants;

import ExampleGame.ExampleScene;
import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Rendering.MeshRenderer3D;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Input;
import org.BluminEngine6.Legacy.Utils.Math.Matrix;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Object.Component;
import org.BluminEngine6.Legacy.Rendering.Color;
import org.lwjgl.glfw.GLFW;

public class Camera extends BluminBehaviour {

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
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_A)){
           transform.position.x -= 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_W)){
            transform.position.z -= 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_S)){
           transform.position.z += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_D)){
           transform.position.x += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_SPACE)){
           transform.position.y += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_LEFT_SHIFT)){
            transform.position.y -= 0.01;
        }
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_ESCAPE)){
            SceneMannager.ChangeScene(new ExampleScene());
        }
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        projectionMatrix = Matrix.projection(fov,(Application.getDisplay().getCurentScreenRes().getWIDTH() / Application.getDisplay().getCurentScreenRes().getHIGHT()) + 0.7f,NearPlane,FarPlane);
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

package ExampleGame;

import org.BluminEngine6.Legacy.Audio.Mixer;
import org.BluminEngine6.Editor.SceneManagment.Scene;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Input;
import org.lwjgl.glfw.GLFW;

public class ExampleScene extends Scene {
    ExampleGameobject ego = new ExampleGameobject();

    public ExampleScene() {
        RegisterGameObject(ego);
    }

    @Override
    public void Update() {
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_A)){
            mainCamera.transform.position.x -= 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_W)){
            mainCamera.transform.position.z -= 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_S)){
            mainCamera.transform.position.z += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_D)){
            mainCamera.transform.position.x += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_SPACE)){
            mainCamera.transform.position.y += 0.01;
        }
        if(Input.Instance().WasHeld(GLFW.GLFW_KEY_LEFT_SHIFT)){
            mainCamera.transform.position.y -= 0.01;
        }
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {

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

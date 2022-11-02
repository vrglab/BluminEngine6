package ExampleGame;

import org.BluminEngine6.Audio.AudioSystem;
import org.BluminEngine6.Editor.Componants.Transform;
import org.BluminEngine6.Legacy.Audio.Mixer;
import org.BluminEngine6.Editor.SceneManagment.Scene;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Input;
import org.BluminEngine6.Physics.Physics;
import org.lwjgl.glfw.GLFW;

public class ExampleScene extends Scene {

    Physics physics = new Physics();

    ExampleGameobject ego = new ExampleGameobject();
    ExampleGameobject2 egoo = new ExampleGameobject2();

    AudioSystem ass;

    public ExampleScene() {
        RegisterGameObject(physics);
        RegisterGameObject(ego);
        RegisterGameObject(egoo);
    }

    @Override
    public void Update() {
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_E)) {
            ego.Enable();
        }
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_R)) {
            ego.Disable();
        }


        if(!ego.rgBody.getBody().checkCollideWith(egoo.rgBody.getBody())) {
            ego.transform.position.x += 0.001f;
        }

        if(!egoo.rgBody.getBody().checkCollideWith(ego.rgBody.getBody())) {
            egoo.transform.position.x += 0.01f;
        }
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
            ego.collider.Debug = true;
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

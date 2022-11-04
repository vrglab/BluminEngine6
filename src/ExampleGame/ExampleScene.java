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
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_1)) {
            physics.Enable();
        }
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        physics.Disable();
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

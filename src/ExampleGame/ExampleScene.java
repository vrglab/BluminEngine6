package ExampleGame;

import org.BluminEngine6.Editor.Componants.Transform;
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

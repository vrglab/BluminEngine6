package ExampleGame;

import org.BluminEngine6.Legacy.Audio.Mixer;
import org.BluminEngine6.Editor.SceneManagment.Scene;

public class ExampleScene extends Scene {
    ExampleGameobject ego = new ExampleGameobject();
    Mixer mix = new Mixer();

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

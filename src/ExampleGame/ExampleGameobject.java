package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Render.Shader;

public class ExampleGameobject extends BluminBehaviour {
    Shader shad;
    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        shad.Creat();
    }

    @Override
    public void PreInit() {
        shad = Application.getCoreResources().GetShader(0,7);
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

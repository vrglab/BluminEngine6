package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.MeshRenderer;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Render.Model;
import org.BluminEngine6.Render.Shader;

public class ExampleGameobject extends BluminBehaviour {

    MeshRenderer mr;
    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        Model m = new Model(0, 3, Application.getCoreResources());
        mr = new MeshRenderer(m, Application.getCoreResources());
        RegisterComponant(mr);
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

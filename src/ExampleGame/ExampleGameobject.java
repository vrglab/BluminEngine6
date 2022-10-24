package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Audio.Legacy.AudioSource;
import org.BluminEngine6.Editor.Componants.Audio.Legacy.Mixer;
import org.BluminEngine6.Editor.Componants.MeshRenderer;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Render.Model;
import org.BluminEngine6.Render.Shader;
import org.BluminEngine6.Utils.Archives.ArchiveFolder;

import java.io.IOException;

public class ExampleGameobject extends BluminBehaviour {
    Mixer m = new Mixer();
    public ExampleGameobject() {
        RegisterComponant(m);
    }
    MeshRenderer mr;
    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {

        Model m = new Model();
        m.setMesh(1, Application.getCoreResources().getRoot().getFolder(0));
        mr = new MeshRenderer(m);
        AudioSource s = new AudioSource(0, Application.getCoreResources().getRoot().getFolder(3));
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

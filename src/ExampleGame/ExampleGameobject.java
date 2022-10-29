package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Editor.Rendering.ImageRenderer;
import org.BluminEngine6.Editor.Rendering.MeshRenderer3D;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Models.Material;
import org.BluminEngine6.Models.Mesh;
import org.BluminEngine6.Models.Model;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Utils.ResourceBatch;

public class ExampleGameobject extends BluminBehaviour {

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
        /*Model ourm = new Model(new Mesh(Utils.Cube(1)), new Material());
        Model ourm2 = new Model(ResourceBatch.GetMesh(1, Application.getCoreResources().getRoot().getFolder(0)), new Material());
        /*try {
            ourm.SaveToFile("Dragon", Application.getTempFolder().getAbsolutePath());
            ourm2.SaveToFile("Cube", Application.getTempFolder().getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MeshRenderer3D mr = new MeshRenderer3D(ourm);
        RegisterComponant(mr);*/

        ImageRenderer ir = new ImageRenderer(ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4)));
        RegisterComponant(ir);
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

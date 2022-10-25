package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Rendering.MeshRenderer3D;
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
        Model ourm = ResourceBatch.GetModel(1, Application.getCoreResources().getRoot().getFolder(1));
       /* Model ourm2 = new Model(ResourceBatch.GetMesh(0, Application.getCoreResources().getRoot().getFolder(0)), new Material());
        try {
            ourm.SaveToFile("Dragon", Application.getTempFolder().getAbsolutePath());
            ourm2.SaveToFile("Cube", Application.getTempFolder().getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        MeshRenderer3D mr = new MeshRenderer3D(ourm);
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

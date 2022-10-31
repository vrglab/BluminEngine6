package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Audio.AudioSystem;
import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Editor.Componants.Transform;
import org.BluminEngine6.Editor.Rendering.ImageRenderer;
import org.BluminEngine6.Editor.Rendering.MeshRenderer3D;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Audio.AudioSource;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Models.Material;
import org.BluminEngine6.Models.Mesh;
import org.BluminEngine6.Models.Model;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Utils.ResourceBatch;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ExampleGameobject extends BluminBehaviour {

    Model ourm2;
    MeshRenderer3D mr;
    AudioSource as;

    @Override
    public void Update() {
        if(!as.isPlaying()) {
            as.Play();
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
        Model ourm = new Model(new Mesh(Utils.Cube(1)), new Material());
        ourm2 = new Model(ResourceBatch.GetMesh(0, Application.getCoreResources().getRoot().getFolder(0)), new Material());
        /*try {
            ourm.SaveToFile("Dragon", Application.getTempFolder().getAbsolutePath());
            ourm2.SaveToFile("Cube", Application.getTempFolder().getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        mr = RegisterComponent(new MeshRenderer3D(ourm2));

        as = RegisterComponent(new AudioSource(1, Application.getCoreResources().getRoot().getFolder(3)));

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

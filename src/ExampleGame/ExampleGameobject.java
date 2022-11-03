package ExampleGame;

import com.bulletphysics.linearmath.Transform;
import org.BluminEngine6.Application;
import org.BluminEngine6.Audio.AudioSystem;
import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Editor.Rendering.ColliderRenderer;
import org.BluminEngine6.Editor.Rendering.ImageRenderer;
import org.BluminEngine6.Editor.Rendering.MeshRenderer3D;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Audio.AudioSource;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Input;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Models.Material;
import org.BluminEngine6.Models.Mesh;
import org.BluminEngine6.Models.Model;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Physics.Collision.Collider;
import org.BluminEngine6.Physics.Collision.ConvexHullCollider;
import org.BluminEngine6.Physics.Physics;
import org.BluminEngine6.Physics.RigidBody;
import org.BluminEngine6.Utils.ResourceBatch;
import org.lwjgl.glfw.GLFW;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ExampleGameobject extends BluminBehaviour {

    Model ourm2;
    MeshRenderer3D mr;
    AudioSource as;

    Collider collider;
    public RigidBody rgBody;

    @Override
    public void Update() {
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_Q)) {
            as.Pause();
        }
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_T)) {
            as.Play();
        }
        if(Input.Instance().WasPressed(GLFW.GLFW_KEY_Y)) {
            as.Stop();
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
        transform.position.x = 30;
        //Model ourm = new Model(ResourceBatch.GetMesh(0, Application.getCoreResources().getRoot().getFolder(0)), new Material());
        ourm2 = ResourceBatch.GetModel(0, Application.getCoreResources().getRoot().getFolder(1));
        /*try {
            ourm.SaveToFile("Cube", Application.getTempFolder().getAbsolutePath());
            ourm2.SaveToFile("Dragon", Application.getTempFolder().getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        mr = RegisterComponent(new MeshRenderer3D(ourm2));

        collider = RegisterComponent( new ConvexHullCollider(ourm2));
        rgBody = RegisterComponent(new RigidBody(collider));
        RegisterComponent(new ColliderRenderer(collider));

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

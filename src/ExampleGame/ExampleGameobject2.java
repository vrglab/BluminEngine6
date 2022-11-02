package ExampleGame;

import org.BluminEngine6.Application;
import org.BluminEngine6.Audio.AudioSource;
import org.BluminEngine6.Editor.Rendering.MeshRenderer3D;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Input;
import org.BluminEngine6.Models.Model;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Physics.Collision.Collider;
import org.BluminEngine6.Physics.RigidBody;
import org.BluminEngine6.Utils.ResourceBatch;
import org.lwjgl.glfw.GLFW;

public class ExampleGameobject2 extends BluminBehaviour {

    Model ourm2;
    MeshRenderer3D mr;
    AudioSource as;

    Collider collider;
    RigidBody rgBody;

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
        transform.position.x = 10;
        ourm2 = ResourceBatch.GetModel(0, Application.getCoreResources().getRoot().getFolder(1));
        mr = RegisterComponent(new MeshRenderer3D(ourm2));

        collider = RegisterComponent( new Collider());
        rgBody = RegisterComponent(new RigidBody());

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

package org.BluminEngine6.Editor.Componants;

import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Object.Component;

import javax.vecmath.Quat4f;

import java.io.Serializable;

public class Transform extends Component implements Serializable {
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Transform() {
        position = new Vector3(0,0,0);
        rotation = new Vector3(0,0,0);
        scale = new Vector3(1,1,1);
    }

    public Transform(Vector3 position) {
        this.position = position;
        rotation = new Vector3(0,0,0);
        scale = new Vector3(1,1,1);
    }
    public Transform(Vector3 position, Vector3 rotation) {
        this.position = position;
        this.rotation = rotation;
        scale = new Vector3(1,1,1);
    }

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public com.bulletphysics.linearmath.Transform GetAsPhysicsTransform() {
        com.bulletphysics.linearmath.Transform t = new com.bulletphysics.linearmath.Transform();
        t.setRotation(new Quat4f(rotation.x,rotation.y,rotation.z,1));
        t.transform(position.GetAsVec3f());
        return t;
    }

    public static Transform DefaultZero = new Transform(new Vector3(0,0,0),new Vector3(0,0,0), new Vector3(1,1,1));


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

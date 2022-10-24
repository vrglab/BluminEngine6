package org.BluminEngine6.Legacy.Audio;


import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Object.Component;

import static org.lwjgl.openal.AL10.*;

public class listener extends Component {

    @Override
    public void Update() {
        try {
            //alListener3f(AL_POSITION, Parent.transform.position.x, Parent.transform.position.y, Parent.transform.position.z);
            alListener3f(AL_VELOCITY, 0, 0, 0);
        } catch (Exception e) {
            Debug.logException(e);
        }
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

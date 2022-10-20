package org.BluminEngine6.Editor.Componants.Audio.Fmod;

import BluminEngine5.Componant.Component;
import BluminEngine5.Utils.Annotations.Obsolete;
import org.fmod.FMODLoader;
import org.fmod.studio.Bank;
import org.fmod.studio.EventDescription;
import org.fmod.studio.FmodStudioSystem;

import static org.fmod.jni.FMODConstants.FMOD_INIT_NORMAL;
import static org.fmod.jni.FMODConstants.FMOD_STUDIO_INIT_NORMAL;

/**
 * Please refrain from using this class in any way, as it still not complete and broken
 */
@Obsolete @Deprecated
public class Fmod extends Component {

    FmodStudioSystem studioSystem;

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        FMODLoader.loadNatives();
        studioSystem = FmodStudioSystem.create();
        studioSystem.initialize(32, FMOD_STUDIO_INIT_NORMAL, FMOD_INIT_NORMAL, null);
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

    public Bank GetBank(String path) {
        return studioSystem.getBank(path);
    }

    public EventDescription GetEvent(String path){
        return studioSystem.getEvent(path);
    }
}

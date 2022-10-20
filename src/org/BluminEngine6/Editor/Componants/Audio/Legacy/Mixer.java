package org.BluminEngine6.Editor.Componants.Audio.Legacy;


import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Object.Component;
import org.lwjgl.openal.*;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.openal.AL10.AL_INVERSE_DISTANCE_CLAMPED;
import static org.lwjgl.openal.AL10.alDistanceModel;
import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcDestroyContext;


public class Mixer extends Component {

    private listener Listener;
    private List<MixerProperty> mixers = new ArrayList<>();

    public static Mixer instance;

    long device = 000000;
    long context = 000000;

    public MixerProperty Get(int id) {
        return mixers.get(id);
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        String DefaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
        device = ALC10.alcOpenDevice(DefaultDeviceName);
        ALCCapabilities alc =  ALC.createCapabilities(device);
        context = ALC10.alcCreateContext(device, (IntBuffer) null);
        ALC10.alcMakeContextCurrent(context);
        AL.createCapabilities(alc);
        ALCapabilities cap = AL.getCapabilities();
        AL.setCurrentThread(cap);
        alDistanceModel(AL_INVERSE_DISTANCE_CLAMPED);

        if(!cap.OpenAL10) {
            Utils.CrashApp(-45, "No Valid OpenAL library found");
        }

        ///Listener = SceneManager.GetCurent().GetActiveScene().ActiveCamera.getComponant(listener.class);
        if(Listener == null) {
            Debug.log("Should no longer be null");
            //Listener =  SceneManager.GetCurent().GetActiveScene().ActiveCamera.RegisterComponant(new listener());
            if(Listener == null) {
                Utils.CrashApp(-50, "Mixer failed to find Listener");
            }
        }
        var MasterMixer = new MixerProperty();
        MasterMixer.name = "Master";
        mixers.add(0, MasterMixer);
        if(instance == null) {
            instance = this;
        } else{
            Debug.logError("There can only be 1 Mixer in Each Scene");
        }
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
        alcDestroyContext(context);
        alcCloseDevice(device);
        instance = null;
    }
}


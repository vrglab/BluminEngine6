package org.BluminEngine6.Legacy.Audio;


import org.BluminEngine6.Archives.ArchiveFolder;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Utils.ResourceBatch;
import org.lwjgl.openal.AL10;

import org.BluminEngine6.Object.Component;

import static org.lwjgl.openal.AL10.*;

public class AudioSource extends Component {

    private AudioFile Audiofile;
    private MixerProperty mp;

    private float rolloff = 2, Distance = 6, max_distance = 50;
    int id, buffer;

    public AudioSource(int file, ArchiveFolder rm) {
        try {
           // Audiofile = ResourceBatch.GetAudio(file, rm);
            if(Mixer.instance == null) {
                Debug.logError("Mixer is required in the scene for audio playing");
                return;
            } else {
                mp = Mixer.instance.Get(0);
            }
        } catch (Exception e) {
            Debug.logException(e);
        }
    }

    public void setMixerProperty(MixerProperty mp) {
        this.mp = mp;
    }

    @Override
    public void Update() {
        AL10.alSource3f(id, AL_POSITION, Parent.transform.position.x,Parent.transform.position.y,Parent.transform.position.z);
        AL10.alSourcef(id, AL_MAX_DISTANCE, max_distance);
        AL10.alSourcef(id, AL_REFERENCE_DISTANCE, Distance);
        AL10.alSourcef(id, AL_ROLLOFF_FACTOR, rolloff);
        SetVolume(mp.Volume);
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        id = AL10.alGenSources();
        buffer = AL10.alGenBuffers();

        if(Audiofile.sdata == null) {
            AL10.alBufferData(buffer, AL_FORMAT_STEREO16, Audiofile.data, Audiofile.samplerate);
        } else{
            AL10.alBufferData(buffer, AL_FORMAT_STEREO16, Audiofile.sdata, Audiofile.samplerate);
        }
        AL10.alSourcei(id, AL_BUFFER, buffer);
        SetVolume(0.4f);

    }

    public void SetVolume(float volume) {

        AL10.alSourcef(id, AL_GAIN, volume);
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
        Stop();
        alDeleteBuffers(buffer);
    }

    public void Play() {
        alSourcePlay(id);
    }

    public void Pause() {
        alSourcePause(id);
    }

    public void Stop() {
        alSourceStop(id);
    }

    public boolean isPlaying() {
        return alGetSourcei(id, AL_SOURCE_STATE) == AL_PLAYING;
    }

    public float getRolloff() {
        return rolloff;
    }

    public void setRolloff(float rolloff) {
        this.rolloff = rolloff;
    }

    public float getDistance() {
        return Distance;
    }

    public void setDistance(float distance) {
        Distance = distance;
    }

    public float getMax_distance() {
        return max_distance;
    }

    public void setMax_distance(float max_distance) {
        this.max_distance = max_distance;
    }
}

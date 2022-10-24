package org.BluminEngine6.Legacy.Audio;

import java.util.ArrayList;
import java.util.List;

public class MixerProperty {
    public String name;
    public float Volume = 0.4f;
    public List<AudioSource> sources = new ArrayList<>();
    public MixerProperty parent;
}

package org.BluminEngine6.Editor.Componants.Audio.Legacy;

import BluminEngine5.Utils.Annotations.MustCreate;
import BluminEngine5.Utils.Utils;
import org.apache.commons.io.FilenameUtils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.stb.STBVorbis.stb_vorbis_decode_filename;
import static org.lwjgl.system.MemoryStack.*;


/**
 * Class used to handel Audio files
 * @author Vrglab
 */
@MustCreate
public class AudioFile {

    public final ByteBuffer data;
    public final ShortBuffer sdata;
    public final int format;
    public final int samplerate;

    private AudioFile(ByteBuffer data, int format, int samplerate) {
        this.data = data;
        this.sdata = null;
        this.format = format;
        this.samplerate = samplerate;
    }

    private AudioFile(ShortBuffer data, int format, int samplerate) {
        this.data = null;
        this.sdata = data;
        this.format = format;
        this.samplerate = samplerate;
    }

    public static AudioFile create(File file) {
        try {

            InputStream is = Utils.LoadFileAsStream(file.getAbsolutePath());


            if (FilenameUtils.getExtension(file.getAbsolutePath()).equals("wav")) {
                byte[] wavData = new byte[is.available()];
                ByteBuffer dest = ByteBuffer.allocateDirect(wavData.length * 2);
                dest.put(wavData);
                dest.flip();

                InputStream bufferedIn = new BufferedInputStream(is);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
                AudioFormat audioFormat = audioInputStream.getFormat();

                ByteBuffer dest1 = ByteBuffer.allocateDirect(wavData.length * 2);
                dest1.put( audioInputStream.readAllBytes());
                dest1.flip();
                is.close();
                return new AudioFile(dest1, audioFormat.getChannels(), (int) audioFormat.getSampleRate());
            }

            if (FilenameUtils.getExtension(file.getAbsolutePath()).equals("ogg")) {
                stackPush();
                IntBuffer chanels = stackMallocInt(1);
                stackPush();
                IntBuffer samplerate = stackMallocInt(1);

                ShortBuffer audioFile = stb_vorbis_decode_filename(file.getAbsolutePath(), chanels, samplerate);


                var f = new AudioFile(audioFile, chanels.get(), samplerate.get());
                stackPop();
                stackPop();
                is.close();
                return f;
            }

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void dispose() {
        this.data.clear();
    }

    private static AudioInputStream convertToPCM(AudioInputStream audioInputStream)
    {
        AudioFormat m_format = audioInputStream.getFormat();

        if ((m_format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) &&
                (m_format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED))
        {
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    m_format.getSampleRate(), 16,
                    m_format.getChannels(), m_format.getChannels() * 2,
                    m_format.getSampleRate(), m_format.isBigEndian());
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        }

        return audioInputStream;
    }
}

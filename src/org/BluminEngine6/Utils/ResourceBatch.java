package org.BluminEngine6.Utils;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Audio.AudioFile;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.ObjLoader;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Models.Mesh;
import org.BluminEngine6.Models.Model;
import org.BluminEngine6.Models.Texture;
import org.BluminEngine6.Render.Shader;
import org.BluminEngine6.Utils.Archives.ArchiveFolder;
import org.BluminEngine6.Utils.Archives.ArchiveMannager;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class ResourceBatch {
    private static HashMap<String, Shader> shadersbacth = new HashMap<String, Shader>();
    private static HashMap<String, Texture> texturesbacth = new HashMap<String, Texture>();
    private static HashMap<String, Mesh> meshsbacth = new HashMap<String, Mesh>();
    private static HashMap<String, AudioFile> audiobatch = new HashMap<String, AudioFile>();

    private static HashMap<String, Model> modelssbacth = new HashMap<String, Model>();


    public static Texture GetTexture(int file, ArchiveFolder folder) {
        var arch = folder.getFile(file);
        var location = Application.getTempFolder().getAbsolutePath() +"/Temp " + arch.FileName + "." + arch.Extension;
        Debug.log("Getting: "+ arch.FileName + "." + arch.Extension);
        if(!texturesbacth.containsKey(location)) {
            var dat = new Texture(arch);
            texturesbacth.put(location,dat);
            return dat;
        } else{
            return texturesbacth.get(location);
        }
    }

    public static Shader GetShader(int file, ArchiveFolder folder) {
        try {
            var arch = folder.getFile(file);
            var location = Application.getTempFolder().getAbsolutePath() +"/Temp " + arch.FileName + "." + arch.Extension;

            var f = ArchiveMannager.LoadArchiveFileToTempFile(arch);
            if(!shadersbacth.containsKey(location)) {
                var dat = new Shader(f.getAbsolutePath(), folder);
                shadersbacth.put(location,dat);
                f.delete();
                return dat;
            } else{
                return shadersbacth.get(location);
            }
        }catch(Exception e) {

            return null;
        }
    }

    public static AudioFile GetAudio(int file, ArchiveFolder folder) {
        try {
            var arch = folder.getFile(file);
            var f = ArchiveMannager.LoadArchiveFileToTempFile(arch);
            var location = Application.getMetadata().ResourceFolder +"/Temp/Temp " + arch.FileName + "." + arch.Extension;

            var shaderLocation = f.getAbsolutePath();
            if(!audiobatch.containsKey(location)) {
                var dat = AudioFile.create(f);
                audiobatch.put(location,dat);
                f.delete();
                return dat;
            } else{
                return audiobatch.get(location);
            }
        }catch (Exception e) {
            Debug.logException("Could not load the wav file",e);
            return null;
        }
    }

    public static Mesh GetMesh(int file, ArchiveFolder folder) {
        try{
            var arch = folder.getFile(file);


            var location = Application.getTempFolder().getAbsolutePath() +"/Temp " + arch.FileName + "." + arch.Extension;


            if(!meshsbacth.containsKey(location)) {
                var f = ArchiveMannager.LoadArchiveFileToTempFile(arch);
                var shaderLocation = f.getAbsolutePath();

                var dat = ObjLoader.LoadFile(shaderLocation);
                f.delete();
                meshsbacth.put(location,dat);
                return dat;
            } else{
                return meshsbacth.get(location);
            }
        } catch(IOException e){
            return  null;
        }
    }

    public static Model GetModel(int file, ArchiveFolder folder) {
        File f = null;
        try{
            var arch = folder.getFile(file);

            f = ArchiveMannager.LoadArchiveFileToTempFile(arch);

            var location = Application.getTempFolder().getAbsolutePath() + "/Temp " + arch.FileName + "." + arch.Extension;

            if(!modelssbacth.containsKey(location)) {

                if(!FilenameUtils.getExtension(f.getAbsolutePath()).equals("bmd")) {
                    Debug.log(FilenameUtils.getExtension(f.getAbsolutePath()));
                    Debug.logException(new Exception( f.getAbsolutePath() + " is not a BluminEngine Model File"));
                    return null;
                }

                ObjectInputStream objectInputStream = new ObjectInputStream(Utils.LoadFileAsStream(f.getAbsolutePath()));
                var dat = (Model) objectInputStream.readObject();
                dat.setMaterialRaw(dat.getMaterialRaw());
                dat.setMeshRaw(dat.getMeshRaw());
                modelssbacth.put(location,dat);
                objectInputStream.close();
                f.delete();
                return dat;
            } else{
                return modelssbacth.get(location);
            }
        } catch(Exception e){
            f.delete();
            return  null;
        }
    }

}

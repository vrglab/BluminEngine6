package org.BluminEngine6.Render;


import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Annotations.MustCreate;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Math.Vector2;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchivedFile;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.apache.commons.io.FilenameUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;

@MustCreate
public class Texture implements Serializable{

    private org.newdawn.slick.opengl.Texture texture;
    private float width, height;
    private ByteBuffer decodedbytes;
    private int textureId;
    public ArchivedFile file;
    private String fileBackup;

    public Texture(ArchivedFile file) {
        this.file = file;
    }

    public Texture(String file) {
        this.fileBackup = file;
    }
    public Texture(Vector2 size) {
        width = (int)size.x;
        height = (int)size.y;
    }

    public void Create(ResourceMannager rm) {
        if(file != null) {
            try{
                texture = GetTexFromFile(rm);
                width = texture.getWidth();
                height = texture.getHeight();
                textureId = texture.getTextureID();

                decodedbytes = ByteBuffer.allocate(file.GetDecodedData().length * 4);
                decodedbytes.put(file.GetDecodedData());
                decodedbytes.flip();

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);
            } catch(IOException e){
                Debug.logException("Failed to load texture with exception", e);
            }
        } else{
            try{
                InputStream is = Utils.LoadFileAsStream(fileBackup);
                File f = new File(fileBackup);
                texture =  TextureLoader.getTexture(FilenameUtils.getExtension(fileBackup), is , GL11.GL_LINEAR);
                width = texture.getWidth();
                height = texture.getHeight();
                textureId = texture.getTextureID();
                byte[] data = texture.getTextureData();

                decodedbytes = ByteBuffer.allocate(data.length * 4);
                decodedbytes.put(data);
                decodedbytes.flip();

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);
                f.delete();
            } catch(IOException e){
                Debug.logException("Failed to create texture with exception", e);
            }
        }


    }

    public void Destroy() {
        GL13.glDeleteTextures(textureId);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public ByteBuffer getDecodedbytes() {
        return decodedbytes;
    }

    public int getTextureId() {
        return textureId;
    }


    private org.newdawn.slick.opengl.Texture GetTexFromFile(ResourceMannager rm)
    throws IOException{

        File f = rm.LoadIntoTempFile(file);
        InputStream is = Utils.LoadFileAsStream(f.getAbsolutePath());
        org.newdawn.slick.opengl.Texture tex =  TextureLoader.getTexture(file.Extension, is , GL11.GL_LINEAR);
        is.close();
        f.delete();
        return  tex;
    }
}

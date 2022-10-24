package org.BluminEngine6.Legacy.Rendering;


import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Annotations.MustCreate;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;
import org.BluminEngine6.Render.Color;
import org.BluminEngine6.Render.Texture;
import org.BluminEngine6.Utils.Archives.ArchiveFile;
import org.BluminEngine6.Utils.Archives.ArchiveFolder;
import org.BluminEngine6.Utils.ResourceBatch;

import java.io.Serializable;

@MustCreate
public class Material implements Serializable {
    private Texture texture, DefuseMap,  SpecularMap, ReflectionsMap;
    private Color color;


    public Vector3 Ambient = new Vector3(0.31f,0.31f,0.31f);

    public float Shine = 0f;
    public float reflection = 0f;
    ResourceMannager mrm;

    public Material() {
        texture = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        DefuseMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        SpecularMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        ReflectionsMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        color = new Color(1,0.5f,1,1);
    }

    public Material(ArchiveFile text, ArchiveFolder rm) {
        texture = ResourceBatch.GetTexture(text.getId(), rm);
        DefuseMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        SpecularMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        ReflectionsMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        color = new Color(1,0.5f,1,1);
    }

    public Material(Texture text) {
        texture = text;
        DefuseMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        SpecularMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        ReflectionsMap = ResourceBatch.GetTexture(0, Application.getCoreResources().getRoot().getFolder(4));
        color = new Color(1,0.5f,1,1);
    }

    public void Creat() {
        texture.Create();
        DefuseMap.Create();
        SpecularMap.Create();
        ReflectionsMap.Create();
    }

    public void Destroy() {
        texture.Destroy();
        DefuseMap.Destroy();
        SpecularMap.Destroy();
        ReflectionsMap.Destroy();
    }

    public Texture getTexture() {
        return texture;
    }

    public Texture getDefuseMap() {
        return DefuseMap;
    }

    public Texture getSpecularMap() {
        return SpecularMap;
    }
    public Texture getReflectionsMap() {
        return ReflectionsMap;
    }

    public void SetTexture(Texture t) {
        texture = t;
    }

    public void setDefuseMap(Texture defuseMap) {
        DefuseMap = defuseMap;
    }

    public void setSpecularMap(Texture specularMap) {
        SpecularMap = specularMap;
    }

    public void setReflectionsMap(Texture ReflectionsMap) {
        this.ReflectionsMap = ReflectionsMap;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean equals(Material obj) {
        if(obj.texture.getTextureId() == texture.getTextureId()) {
            if(obj.getDefuseMap().getTextureId() == getDefuseMap().getTextureId()) {
                if(obj.getSpecularMap().getTextureId() == getSpecularMap().getTextureId()) {
                    if(obj.getReflectionsMap().getTextureId() == getReflectionsMap().getTextureId()) {
                        if(obj.Shine == Shine) {
                            if(obj.reflection == reflection) {
                                if(obj.Ambient == Ambient) {
                                    if(obj.getColor() == getColor()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}

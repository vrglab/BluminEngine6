package org.BluminEngine6.Render;


import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Annotations.MustCreate;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchivedFile;

import java.io.Serializable;

@MustCreate
public class Material implements Serializable {
    private Texture texture, DefuseMap,  SpecularMap, ReflectionsMap;
    private Color color;


    public Vector3 Ambient = new Vector3(0.31f,0.31f,0.31f);

    public float Shine = 0f;
    public float reflection = 0f;


    public Material() {
        texture = Application.getResourceMannager().GetTexture(0, 5);
        DefuseMap = Application.getResourceMannager().GetTexture(0,5);
        SpecularMap = Application.getResourceMannager().GetTexture(0,5);
        ReflectionsMap = Application.getResourceMannager().GetTexture(0,5);
        color = new Color(1,0.5f,1,1);
    }

    public Material(ArchivedFile text) {
        texture = Application.getResourceMannager().GetTexture(text.ArchiveId, text.ID);
        DefuseMap = Application.getResourceMannager().GetTexture(0,5);
        SpecularMap = Application.getResourceMannager().GetTexture(0,5);
        ReflectionsMap = Application.getResourceMannager().GetTexture(0,5);
        color = new Color(1,0.5f,1,1);
    }

    public Material(Texture text) {
        texture = text;
        DefuseMap = Application.getResourceMannager().GetTexture(0,5);
        SpecularMap = Application.getResourceMannager().GetTexture(0,5);
        ReflectionsMap = Application.getResourceMannager().GetTexture(0,5);
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
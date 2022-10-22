package org.BluminEngine6.Render;


import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.ObjLoader;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;
import org.BluminEngine6.Physics.Colision.Collider;
import org.BluminEngine6.Utils.Archives.ArchiveFolder;
import org.BluminEngine6.Utils.ResourceBatch;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

/**
 * The model class does as its name suggests, the model class handles everything that is needed for
 * 3D model to properly load
 * @author Vrglab
 * @version SerializationSystem_0.1
 * @since 0.0.1.0_Alpha
 */
public class Model implements Serializable {

    //public Transform transform = Transform.DefaultZero;
    private MeshData mesh = null;
    private MaterialData material;
    private List<Collider> coliders = new ArrayList<>();

    public Model(Mesh mesh, List<Collider> coliders, Material m) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream defl = new DeflaterOutputStream(out);

        byte[] meshdata = SerializationUtils.serialize(mesh);
        try {

            defl.write(meshdata);
            defl.flush();
            defl.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mesh = new MeshData(mesh, out.toByteArray());
        this.coliders = coliders;
        setMaterial(m);
    }

    public Model(int file, ArchiveFolder rm) {
        var dat = ObjLoader.LoadModel(rm.getFile(file));
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        InflaterOutputStream infl = new InflaterOutputStream(out2);
        Mesh m = null;

        try {
            infl.write(dat.getRawMesh());
            infl.flush();
            infl.close();
            ByteArrayInputStream in = new ByteArrayInputStream(out2.toByteArray());
            ObjectInputStream is = new ObjectInputStream(in);

            m = (Mesh)is.readObject();
        } catch (Exception e) {
            Debug.logException(e);
        }

        mesh = new MeshData(m, dat.getRawMesh());
        coliders = dat.getColliders();
        setMaterial(dat.getRawMaterial(), rm);
    }

    public Model() {

    }

    public void setMesh(int file,  ArchiveFolder rm) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream defl = new DeflaterOutputStream(out);

        byte[] meshdata = SerializationUtils.serialize(ResourceBatch.GetMesh(file, rm));
        try {
            defl.write(meshdata);
            defl.flush();
            defl.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mesh m = null;
        try {

            ByteArrayInputStream in = new ByteArrayInputStream(meshdata);
            ObjectInputStream is = new ObjectInputStream(in);

            m = (Mesh)is.readObject();
        } catch (Exception e) {
            Debug.logException(e);
        }

        this.mesh = new MeshData(m, out.toByteArray());
        setMaterial(new Material());
    }

    public Mesh getMesh() {
        return mesh.mesh;
    }

    public byte[] getRawMesh() {
        return mesh.rawMesh;
    }

    public List<Collider> getColliders() {
        return coliders;
    }

    public void AddCollider(Collider col) {
        coliders.add(col);
    }

    public void RemoveCollider(Collider col) {
        if(!coliders.contains(col)){
            return;
        }
        coliders.remove(coliders.lastIndexOf(col));
    }

    public Material getMaterial() {
        return material.getMaterial();
    }

    public SerliazedMaterial getRawMaterial() {
        return material.SerMaterial;
    }

    public void setMaterial(Material material) {
        this.material = new MaterialData(material);
    }

    public void setMaterial(SerliazedMaterial material, ArchiveFolder rm) {
        this.material = new MaterialData(material,rm);
    }


    private static class SerliazedMaterial implements Serializable  {

        public SerliazedTexture texture, DefuseMap,  SpecularMap, ReflectionsMap;

        private Color color = Color.Black;

        public Vector3 Ambient = new Vector3(0.31f,0.31f,0.31f);

        public float Shine = 0f;
        public float reflection = 0f;


        private static class SerliazedTexture implements Serializable{
            int file = 0;
            int Archive = 0;

            public SerliazedTexture(int file, int archive) {
                this.file = file;
                Archive = archive;
            }
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public static Material Parse(SerliazedMaterial material, ArchiveFolder rm) {
            Material m = new Material();
            m.reflection = material.reflection;
            m.Shine = material.Shine;
            m.Ambient = material.Ambient;
            m.setSpecularMap(ResourceBatch.GetTexture(m.getSpecularMap().file.getId(), Application.getCoreResources().getRoot().getFolder(m.getSpecularMap().file.getFolderId())));
            m.setDefuseMap(ResourceBatch.GetTexture(m.getSpecularMap().file.getId(), Application.getCoreResources().getRoot().getFolder(m.getSpecularMap().file.getFolderId())));
            m.setReflectionsMap(ResourceBatch.GetTexture(m.getSpecularMap().file.getId(), Application.getCoreResources().getRoot().getFolder(m.getSpecularMap().file.getFolderId())));
            m.SetTexture(ResourceBatch.GetTexture(m.getSpecularMap().file.getId(), Application.getCoreResources().getRoot().getFolder(m.getSpecularMap().file.getFolderId())));
            m.setColor(material.color);
            return m;
        }

        public static SerliazedMaterial Parse(Material material) {
            SerliazedMaterial m = new SerliazedMaterial();
            m.color = material.getColor();
            m.Ambient = material.Ambient;
            m.Shine = material.Shine;;
            m.reflection = material.reflection;
            m.texture = new SerliazedTexture(material.getTexture().file.getId(), material.getTexture().file.getFolderId());
            m.DefuseMap = new SerliazedTexture(material.getDefuseMap().file.getId(), material.getDefuseMap().file.getFolderId());
            m.SpecularMap = new SerliazedTexture(material.getSpecularMap().file.getId(), material.getSpecularMap().file.getFolderId());
            m.ReflectionsMap = new SerliazedTexture(material.getReflectionsMap().file.getId(), material.getReflectionsMap().file.getFolderId());
            return m;
        }

    }

    private class MaterialData implements Serializable{
        SerliazedMaterial SerMaterial;
        Material Material;

        public MaterialData(Material material) {
            Material = material;
            SerMaterial = SerliazedMaterial.Parse(material);
        }

        public MaterialData(SerliazedMaterial material, ArchiveFolder rm) {
            SerMaterial = material;
            Material = SerliazedMaterial.Parse(material,rm);
        }

        public SerliazedMaterial getSerMaterial() {
            return SerMaterial;
        }

        public Material getMaterial() {
            return Material;
        }
    }

    private static class MeshData implements Serializable{
        Mesh mesh = null;
        byte[] rawMesh = null;

        public MeshData(Mesh mesh, byte[] rawMesh) {
            this.mesh = mesh;
            this.rawMesh = rawMesh;
        }
    }


    public void SaveToFile(String file) throws IOException {
        Mesh meshUntoutched = mesh.mesh;
        Material materialuntoutched = material.Material;
        mesh.mesh = null;
        material.Material = null;
        FileOutputStream fileOutputStream = new FileOutputStream(Application.getMetadata().ResourceFolder + "/" + file + ".bmd");
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(this);
        oos.close();
        mesh.mesh = meshUntoutched;
        material.Material = materialuntoutched;
    }

}



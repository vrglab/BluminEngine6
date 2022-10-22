package org.BluminEngine6.Legacy.Utils;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Math.Vector2;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchivedFile;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;
import org.BluminEngine6.Render.Mesh;
import org.BluminEngine6.Render.Model;
import org.BluminEngine6.Render.Vertex;
import org.BluminEngine6.Utils.Archives.ArchiveFile;
import org.BluminEngine6.Utils.Archives.ArchiveFolder;
import org.BluminEngine6.Utils.Archives.ArchiveMannager;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.lwjgl.assimp.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjLoader {



    public static Mesh LoadFile(String obj) {
        AIScene scene = Assimp.aiImportFile(obj, Assimp.aiProcess_JoinIdenticalVertices | Assimp.aiProcess_Triangulate );

        if(scene == null) {
            Debug.logError("Failed to load model at: " + obj);
            return null;
        } else {
            AIMesh aiMesh = AIMesh.create(scene.mMeshes().get(0));
            int vertexCount = aiMesh.mNumVertices();
            AIVector3D.Buffer vertecies = aiMesh.mVertices();
            AIVector3D.Buffer normals = aiMesh.mNormals();
            Vertex[] vertexes = new Vertex[vertexCount];

            for (int i = 0; i < vertexCount; i++) {
                AIVector3D vertex = vertecies.get(i);
                Vector3 meshVertex = new Vector3(vertex.x(), vertex.y(), vertex.z());

                AIVector3D normal = normals.get(i);
                Vector3 meshNromals = new Vector3(normal.x(), normal.y(), normal.z());

                Vector2 textCord = new Vector2(0,0);
                if(aiMesh.mNumUVComponents().get(0) != 0) {
                    AIVector3D text = aiMesh.mTextureCoords(0).get(i);
                    textCord.x = text.x();
                    textCord.y = text.y();
                }
                vertexes[i] = new Vertex(meshVertex, meshNromals, textCord);
            }

            int faceCount = aiMesh.mNumFaces();
            AIFace.Buffer faceBuf = aiMesh.mFaces();
            int[] indecies = new int[faceCount * 3];

            for (int i = 0; i < faceCount; i++) {
                AIFace face = faceBuf.get(i);
                indecies[i * 3 + 0] = face.mIndices().get(0);
                indecies[i * 3 + 1] = face.mIndices().get(1);
                indecies[i * 3 + 2] = face.mIndices().get(2);
            }


            Assimp.aiReleaseImport(scene);
            return new Mesh(vertexes, indecies);
        }
    }

    public static Mesh LoadFile(ArchivedFile obj, ResourceMannager rm) {
        AIScene scene = GetDtaFromFile(obj, rm);

        if(scene == null) {
            Debug.logError("Failed to load model at: " + obj);
            return null;
        } else {
            AIMesh aiMesh = AIMesh.create(scene.mMeshes().get(0));
            int vertexCount = aiMesh.mNumVertices();
            AIVector3D.Buffer vertecies = aiMesh.mVertices();
            AIVector3D.Buffer normals = aiMesh.mNormals();
            Vertex[] vertexes = new Vertex[vertexCount];

            for (int i = 0; i < vertexCount; i++) {
                AIVector3D vertex = vertecies.get(i);
                Vector3 meshVertex = new Vector3(vertex.x(), vertex.y(), vertex.z());

                AIVector3D normal = normals.get(i);
                Vector3 meshNromals = new Vector3(normal.x(), normal.y(), normal.z());

                Vector2 textCord = new Vector2(0,0);
                if(aiMesh.mNumUVComponents().get(0) != 0) {
                    AIVector3D text = aiMesh.mTextureCoords(0).get(i);
                    textCord.x = text.x();
                    textCord.y = text.y();
                }
                vertexes[i] = new Vertex(meshVertex, meshNromals, textCord);
            }

            int faceCount = aiMesh.mNumFaces();
            AIFace.Buffer faceBuf = aiMesh.mFaces();
            int[] indecies = new int[faceCount * 3];

            for (int i = 0; i < faceCount; i++) {
                AIFace face = faceBuf.get(i);
                indecies[i * 3 + 0] = face.mIndices().get(0);
                indecies[i * 3 + 1] = face.mIndices().get(1);
                indecies[i * 3 + 2] = face.mIndices().get(2);
            }

            Assimp.aiReleaseImport(scene);
            return new Mesh(vertexes, indecies);
        }
    }

    public static Model LoadModel(ArchiveFile obj) {
        File f = null;
        try{

            ArchiveFile af = new ArchiveFile(obj.getId(),obj.FileName, obj.Extension,  new String(org.BluminEngine6.Utils.Utils.DecodedDataFromBase64(obj.getFileData())), obj.getFolderId());
             f = ArchiveMannager.LoadArchiveFileToTempFile(af);
            if(!FilenameUtils.getExtension(f.getAbsolutePath()).equals("bmd")) {
                Debug.log(FilenameUtils.getExtension(f.getAbsolutePath()));
                Debug.logException(new Exception( f.getAbsolutePath() + " is not a BluminEngine Model File"));
                return null;
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(Utils.LoadFileAsStream(f.getAbsolutePath()));
            Model m = (Model) objectInputStream.readObject();
            objectInputStream.close();
            f.delete();
            return m;
        } catch (Exception e) {
            Debug.logException(e);
            f.delete();
            return null;
        }
    }

    private static AIScene GetDtaFromFile(ArchivedFile file, ResourceMannager rm){
        try{
            File f = rm.LoadIntoTempFile(file);
            AIScene as = Assimp.aiImportFile(f.getAbsolutePath(), Assimp.aiProcess_JoinIdenticalVertices | Assimp.aiProcess_Triangulate );
            f.delete();
            return  as;
        } catch (IOException e) {

        }
        return null;
    }


}

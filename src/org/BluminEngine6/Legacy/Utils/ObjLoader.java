package org.BluminEngine6.Legacy.Utils;

import BluminEngine5.Application;
import BluminEngine5.Rendering.Master.Mesh;
import BluminEngine5.Rendering.Master.Model;
import BluminEngine5.Rendering.Vertex;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.Math.Vector2;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.ResourceMannager.Archive.ArchivedFile;
import org.apache.commons.io.FilenameUtils;
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

    public static Mesh LoadFile(ArchivedFile obj) {
        AIScene scene = GetDtaFromFile(obj);

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

    public static Model LoadModel(ArchivedFile obj) {
        try{
            File f = Application.getResourceManager().LoadIntoTempFile(obj);
            if(!FilenameUtils.getExtension(f.getAbsolutePath()).equals("bmd")) {
                Debug.log(FilenameUtils.getExtension(f.getAbsolutePath()));
                Debug.logException(new Exception( f.getAbsolutePath() + " is not a BluminEngine Model File"));
                return null;
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(Utils.LoadFileAsStream(f.getAbsolutePath()));
            Model m = (Model) objectInputStream.readObject();
            return m;
        } catch (Exception e) {
            Debug.logException(e);
            return null;
        }
    }

    private static AIScene GetDtaFromFile(ArchivedFile file){
        try{
            File f = Application.getResourceManager().LoadIntoTempFile(file);
            AIScene as = Assimp.aiImportFile(f.getAbsolutePath(), Assimp.aiProcess_JoinIdenticalVertices | Assimp.aiProcess_Triangulate );
            f.delete();
            return  as;
        } catch (IOException e) {

        }
        return null;
    }


}

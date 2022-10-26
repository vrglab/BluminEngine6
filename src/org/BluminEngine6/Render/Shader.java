package org.BluminEngine6.Render;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Math.Matrix;
import org.BluminEngine6.Legacy.Utils.Math.Vector2;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchivedFile;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Utils.Archives.ArchiveFile;
import org.BluminEngine6.Utils.Archives.ArchiveFolder;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;

public class Shader {
    private String VertexShader, FragmentShader, name;
    private int vertexId, fragmentId, programid;
    ArchiveFile Vertex, Fragment;


    public Shader(String location, ArchiveFolder rm) {
        String data = Utils.LoadFile(location);
        JSONObject obj = new JSONObject(data);
        name = obj.getString("name");

        JSONObject VertexObj = obj.getJSONObject("Vertex");
        JSONObject FragmentObj = obj.getJSONObject("Fragment");

        Vertex = rm.getFile(VertexObj.getInt("File"));
        Fragment = rm.getFile(FragmentObj.getInt("File"));

        VertexShader = new String(Vertex.getFileData(), StandardCharsets.UTF_8);
        FragmentShader = new String(Fragment.getFileData(), StandardCharsets.UTF_8);
    }

    public void Creat() {
        programid = GL20.glCreateProgram();
        vertexId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        VertexShader = setShaderData(VertexShader,vertexId);
        GL20.glAttachShader(programid, vertexId);

        fragmentId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        FragmentShader = setShaderData(FragmentShader, fragmentId);
        GL20.glAttachShader(programid, fragmentId);

        GL20.glLinkProgram(programid);
        if(GL20.glGetProgrami(programid, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            Debug.logError(GL20.glGetProgramInfoLog(programid));
            Utils.CrashApp(-16, "Failed to link shader program");
        }

        GL20.glValidateProgram(programid);
        if(GL20.glGetProgrami(programid, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            Debug.logError(GL20.glGetProgramInfoLog(programid));
            Utils.CrashApp(-17, "Failed to validate shader program");
        }
    }

    public int GetUniformLocation(String name) {
        return GL20.glGetUniformLocation(programid, name);
    }

    public int GetUniformLocation(String name, int arraypos, String types) {
        String n = name + "[" + arraypos + "]" + "." + types;
        return GL20.glGetUniformLocation(programid, n);
    }

    private String mainCompile(int shader, String data) {
        boolean Compiled = GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) != GL11.GL_FALSE;
        int index = 0;
        var tempog = data;
        var Fulldata = tempog;
        boolean FullyFailed = false;

        while(!Compiled && FullyFailed == false) {
            switch(index) {
                case 0:
                    Fulldata = "#version 110 core \n" + tempog;
                    break;
                case 1:
                    Fulldata = "#version 120 core \n" + tempog;
                    break;
                case 2:
                    Fulldata = "#version 130 core \n" + tempog;
                    break;
                case 3:
                    Fulldata = "#version 140 core \n" + tempog;
                    break;
                case 4:
                    Fulldata = "#version 150 core \n" + tempog;
                    break;
                case 5:
                    Fulldata = "#version 330 core \n" + tempog;
                    break;
                case 6:
                    Fulldata = "#version 400 core \n" + tempog;
                    break;
                case 7:
                    Fulldata = "#version 410 core \n" + tempog;
                    break;
                case 8:
                    Fulldata = "#version 420 core \n" + tempog;
                    break;
                case 9:
                    Fulldata = "#version 430 core \n" + tempog;
                    break;
                case 10:
                    Fulldata = "#version 440 core \n" + tempog;
                    break;
                case 11:
                    Fulldata = "#version 450 core \n" + tempog;
                    break;
                case 12:
                    Fulldata = "#version 460 core \n" + tempog;
                    break;
                case 13:
                    Fulldata = tempog;
                    break;

            }
            Source(shader, Fulldata);
            if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) != GL11.GL_FALSE) {
                Compiled = true;
            } else{
                if(index >= 13){
                    FullyFailed = true;
                }
            }
            index++;
        }
        if(FullyFailed) {
            Debug.logError(name + ": " + GL20.glGetShaderInfoLog(shader));
            Utils.CrashApp(-2, "Shader failed to compile");
            return "null";
        }
        return Fulldata;
    }

    private String Source(int shader, String data) {
        GL20.glShaderSource(shader, data);
        GL20.glCompileShader(shader);
        return data;
    }

    public void SetUniform(String name, int data) {
        GL20.glUniform1i(GetUniformLocation(name), data);
    }
    public void SetUniform(String name, float data) {
        GL20.glUniform1f(GetUniformLocation(name), data);
    }
    public void SetUniform(String name, boolean data) {
        GL20.glUniform1i(GetUniformLocation(name), data ? 1 : 0);
    }
    public void SetUniform(String name, Vector3 data) {
        GL20.glUniform3f(GetUniformLocation(name), data.x, data.y, data.z);
    }
    public void SetUniform(String name, Vector2 data) {
        GL20.glUniform2f(GetUniformLocation(name), data.x, data.y);
    }
    public void SetUniform(String name, Matrix data) {
        try{
            FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix.SIZE * Matrix.SIZE);
            matrix.put(data.Get()).flip();
            GL20.glUniformMatrix4fv(GetUniformLocation(name), true, matrix);
        } catch(NullPointerException e) {

        }
    }

    /*
    public void SetUniform(String name, BaseLight data, int arrayPos) {
        GL20.glUniform3f(GetUniformLocation(name, arrayPos, "pos"),
                data.Parent.transform.position.x, data.Parent.transform.position.y, data.Parent.transform.position.z);
        GL20.glUniform1f(GetUniformLocation(name, arrayPos, "intesity"),
                data.color.GetA());
        GL20.glUniform4f(GetUniformLocation(name, arrayPos, "Color"),
                data.color.GetR(),data.color.GetG(),data.color.GetB(),data.color.GetA());
    }

    public void SetUniform(String name, Color data) {
        GL20.glUniform4f(GetUniformLocation(name), data.GetR(),data.GetG(),data.GetB(),data.GetA());
    }
*/
    public void Run() {
        GL20.glUseProgram(programid);
    }

    public void Stop() {
        GL20.glUseProgram(0);
    }

    public void Delete() {
        GL20.glDetachShader(programid, vertexId);
        GL20.glDetachShader(programid, fragmentId);
        GL20.glDeleteShader(vertexId);
        GL20.glDeleteShader(fragmentId);
        GL20.glDeleteProgram(programid);
    }

    public String setShaderData(String data, int id){
        Source(id, data);
        return mainCompile(id, data);
    }


    public String getVertexShader() {
        return VertexShader;
    }

    public String getFragmentShader() {
        return FragmentShader;
    }

    public String getName() {
        return name;
    }
}

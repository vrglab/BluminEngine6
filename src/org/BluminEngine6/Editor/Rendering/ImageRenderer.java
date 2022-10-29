package org.BluminEngine6.Editor.Rendering;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.Math.Matrix;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Models.Mesh;
import org.BluminEngine6.Models.Texture;
import org.BluminEngine6.Object.Component;
import org.BluminEngine6.Render.Shader;
import org.BluminEngine6.Utils.ResourceBatch;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;

public class ImageRenderer extends Component {

    Texture texture;
    Shader shader;

    Mesh mesh = new Mesh(Utils.Quad());

    public ImageRenderer(Texture texture) {
        this.texture = texture;
    }

    public ImageRenderer(Texture texture, Shader shader) {
        this.texture = texture;
        this.shader = shader;
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_MIRRORED_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL_TEXTURE_2D, texture.getTextureId());
        shader.SetUniform(
                "transformationMatrix",
                Matrix.transform(
                        Parent.transform.position.GetAsVec2(),
                        Parent.transform.rotation,
                        Parent.transform.scale.GetAsVec2())
        );
        GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0,  mesh.getVertecies().length);
    }

    @Override
    public void Init() {
        shader = ResourceBatch.GetShader(1, Application.getCoreResources().getRoot().getFolder(2).getFolder(1));
        shader.Creat();
        mesh.CreatAll();
        texture.Create();
    }

    @Override
    public void Awake() {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {

    }
}

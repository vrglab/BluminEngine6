package org.BluminEngine6.Editor.Rendering;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Transform;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.Math.Matrix;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Models.Model;
import org.BluminEngine6.Object.Component;
import org.BluminEngine6.Physics.Collision.Collider;
import org.BluminEngine6.Render.Shader;
import org.BluminEngine6.Utils.ResourceBatch;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_BLEND;

public class ColliderRenderer extends Component {

    private Collider model;
    private Shader shader;

    public ColliderRenderer(Collider model) {
        this.model = model;
        shader = ResourceBatch.GetShader(1, Application.getCoreResources().getRoot().getFolder(2).getFolder(0).getFolder(2));
    }

    public ColliderRenderer(Collider model, Shader shader) {
        this.model = model;
        this.shader = shader;
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        if(model.getModel().getMesh().getIndecies() == null || model.getModel().getMesh().getIndecies().length <= 0) {
            DrawWithoutIndecies();
        } else{
            DrawWithIndecies();
        }
    }

    private void DrawWithoutIndecies() {
        shader.Run();

        shader.SetUniform("transform", Matrix.transform(Parent.transform));
        shader.SetUniform("ProjectionMatrix", SceneMannager.getCurrentScene().mainCamera.getProjectionMatrix());
        shader.SetUniform("ViewMatrix",
                Matrix.view(SceneMannager.getCurrentScene().mainCamera.transform.position,
                        SceneMannager.getCurrentScene().mainCamera.transform.rotation));

        //Set the Textures
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getModel().getMaterial().getTexture().getTextureId());
        shader.SetUniform("material.Texture",  0);

        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getModel().getMaterial().getDefuseMap().getTextureId());
        shader.SetUniform("material.diffuse",  1);

        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getModel().getMaterial().getSpecularMap().getTextureId());
        shader.SetUniform("material.specular", 2);

        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getModel().getMaterial().getReflectionsMap().getTextureId());
        shader.SetUniform("material.ReflectionsMap", 3);

        GL11.glDrawArrays(GL_LINE_STRIP, 0, model.getModel().getMesh().getVertecies().length);
        shader.Stop();
    }

    private void DrawWithIndecies() {
        shader.Run();

        glDepthFunc(GL_LEQUAL);
        glEnable(GL_DEPTH_TEST);
        GL30.glBindVertexArray(model.getModel().getMesh().getVao());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL30.glEnableVertexAttribArray(3);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getModel().getMesh().getIbo());

        var camera = SceneMannager.getCurrentScene().mainCamera;
        Matrix view  = Matrix.view(camera.transform.position, camera.transform.rotation);
        Matrix projection = camera.getProjectionMatrix();
        Matrix trnasform = Matrix.transform(transform);

        shader.SetUniform("transform", trnasform);
        shader.SetUniform("ProjectionMatrix", projection);
        shader.SetUniform("ViewMatrix", view);

        shader.SetUniform("DrawTex",  false);


        GL11.glDrawElements(GL_LINE_LOOP, model.getModel().getMesh().getIndecies().length, GL11.GL_UNSIGNED_INT, 0);



        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glDisableVertexAttribArray(3);
        GL30.glBindVertexArray(0);
        shader.Stop();
    }

    @Override
    public void Init() {
        shader.Creat();
        model.getModel().Create();
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
        shader.Delete();
    }

    public Model getModel() {
        return model.getModel();
    }

    public Shader getShader() {
        return shader;
    }
}

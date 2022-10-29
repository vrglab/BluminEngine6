package org.BluminEngine6.Editor.Rendering;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Math.Matrix;
import org.BluminEngine6.Models.Model;
import org.BluminEngine6.Object.Component;
import org.BluminEngine6.Render.Shader;
import org.BluminEngine6.Utils.ResourceBatch;
import org.lwjgl.opengl.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;

public class MeshRenderer3D extends Component {

    private Model model;
    private Shader shader;

    public MeshRenderer3D(Model model) {
        this.model = model;
        shader = ResourceBatch.GetShader(1, Application.getCoreResources().getRoot().getFolder(2).getFolder(0).getFolder(2));
    }

    public MeshRenderer3D(Model model, Shader shader) {
        this.model = model;
        this.shader = shader;
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        if(model.getMesh().getIndecies() == null || model.getMesh().getIndecies().length <= 0) {
            DrawWithoutIndecies();
        } else{
            DrawWithIndecies();
        }
    }

    private void DrawWithoutIndecies() {
        shader.Run();

        shader.SetUniform("transform", Matrix.transform(Parent.transform));
        shader.SetUniform("ProjectionMatrix", SceneMannager.getCurrentScene().mainCamera.getComponant(Camera.class).getProjectionMatrix());
        shader.SetUniform("ViewMatrix",
                Matrix.view(SceneMannager.getCurrentScene().mainCamera.getComponant(Camera.class).transform.position,
                        SceneMannager.getCurrentScene().mainCamera.getComponant(Camera.class).transform.rotation));

        //Set the Textures
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getTexture().getTextureId());
        shader.SetUniform("material.Texture",  0);

        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getDefuseMap().getTextureId());
        shader.SetUniform("material.diffuse",  1);

        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getSpecularMap().getTextureId());
        shader.SetUniform("material.specular", 2);

        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getReflectionsMap().getTextureId());
        shader.SetUniform("material.ReflectionsMap", 3);

        GL11.glDrawArrays(GL_TRIANGLES, 0, model.getMesh().getVertecies().length);
        shader.Stop();
    }

    private void DrawWithIndecies() {
        shader.Run();

        glDepthFunc(GL_LEQUAL);
        glEnable(GL_DEPTH_TEST);
        GL30.glBindVertexArray(model.getMesh().getVao());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL30.glEnableVertexAttribArray(3);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getMesh().getIbo());

        if(model.getMaterial().getColor().GetA() < 1) {
            glEnable(GL_BLEND);
            glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
        }
        var camera = SceneMannager.getCurrentScene().mainCamera.getComponant(Camera.class);
        Matrix view  = Matrix.view(camera.transform.position, camera.transform.rotation);
        Matrix projection = camera.getProjectionMatrix();
        Matrix trnasform = Matrix.transform(Parent.transform);



        shader.SetUniform("transform", trnasform);
        shader.SetUniform("ProjectionMatrix", projection);
        shader.SetUniform("ViewMatrix", view);
      /*  shader.SetUniform("viewPos", SceneManager.GetCurent().GetActiveScene().ActiveCamera.transform.position);
        shader.SetUniform("material.ambient", model.getMaterial().Ambient);
        shader.SetUniform("material.shininess", model.getMaterial().Shine);
        shader.SetUniform("material.reflectivenes", model.getMaterial().reflection);



        shader.SetUniform("levelLightData.sunlight.intensity", SceneManager.GetCurent().GetActiveScene().LightObjects.SceneSun.Intesity);
        shader.SetUniform("levelLightData.sunlight.position", SceneManager.GetCurent().GetActiveScene().LightObjects.SceneSun.transform.position);
        shader.SetUniform("levelLightData.sunlight.color", SceneManager.GetCurent().GetActiveScene().LightObjects.SceneSun.color);

        shader.SetUniform("pointLightsIntheLevel", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.getAmnt());
        for (int i = 0; i < SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.getAmnt(); i++) {
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".position", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).transform.position);
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".intensity", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).Intesity);
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".color", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).color);
            shader.SetUniform("levelLightData.pointlights" + "[" + i + "]" +".attenuation", SceneManager.GetCurent().GetActiveScene().LightObjects.PointLights.GetLight(i).attenuation);
        }
*/

        //Set the Textures
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getTexture().getTextureId());
        shader.SetUniform("material.Texture",  0);

        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getDefuseMap().getTextureId());
        shader.SetUniform("material.diffuse",  1);

        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getSpecularMap().getTextureId());
        shader.SetUniform("material.specular", 2);

        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL13.glBindTexture(GL13.GL_TEXTURE_2D, model.getMaterial().getReflectionsMap().getTextureId());
        shader.SetUniform("material.ReflectionsMap", 3);


        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getMesh().getIndecies().length, GL11.GL_UNSIGNED_INT, 0);

        if(model.getMaterial().getColor().GetA() < 1) {
            glDisable(GL_BLEND);
        }


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
        model.Create();
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
        return model;
    }

    public Shader getShader() {
        return shader;
    }
}

package org.BluminEngine6.Editor.Rendering;

import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Rendering.Color;
import org.BluminEngine6.Render.Renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glClearColor;

public class MasterRenderer extends Renderer {

    @Override
    public void OnRender() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        Color c = SceneMannager.getCurrentScene().mainCamera.getComponant(Camera.class).clearColor;
        glClearColor(c.GetR(),c.GetG(),c.GetB(),c.GetA());

        OnRender.Invoke();
    }
}

package org.BluminEngine6.Render;

import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.EventSystem.Action;
import org.BluminEngine6.Legacy.Utils.EventSystem.IAction;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glClearColor;

public class Renderer {
    public Action<IAction> OnRender = new Action<>();

    public void OnRender() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        Color c = SceneMannager.getCurrentScene().mainCamera.getComponant(Camera.class).clearColor;
        glClearColor(c.r,c.g,c.b,c.a);

        OnRender.Invoke();
    }
}

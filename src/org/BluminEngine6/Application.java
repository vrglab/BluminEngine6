package org.BluminEngine6;

import org.BluminEngine6.Legacy.Utils.EventSystem.Action;
import org.BluminEngine6.Legacy.Utils.EventSystem.IAction;
import org.BluminEngine6.Legacy.Utils.Metadata;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Render.Display;
import org.BluminEngine6.Render.DisplayMode;
import org.BluminEngine6.Render.Resolution;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;

public class Application {
    public static Action<IAction> Update = new Action<>();
    public static Action<IAction> Start = new Action<>();
    public static Action<IAction> Awake = new Action<>();
    public static Action<IAction> OnExit = new Action<>();
    private static Metadata metadata;
    private static ResourceMannager resourceMannager;
    static Display display;
    static File tempFolder;

    public static void Run(Resolution res,DisplayMode dm) {
        try {
            metadata = new Metadata("Config.ini");
            resourceMannager = new ResourceMannager(metadata.ResourceFolder + "/" + metadata.MainArchiveFile);


            display = new Display();
            display.CreateWindow(metadata.GameName, res, dm);

            while (!glfwWindowShouldClose(display.getWindow()) ) {
                Update.Invoke();
                //renderer.Render();

                glfwSwapBuffers(display.getWindow());
                glfwPollEvents();
            }
            display.Close(OnExit);
        } catch (Exception e) {
            Utils.CrashApp(-1, e);
        }

    }

    public static ResourceMannager getResourceMannager() {
        return resourceMannager;
    }

    public static Metadata getMetadata() {
        return metadata;
    }

    public static File getTempFolder() {
        return tempFolder;
    }
}

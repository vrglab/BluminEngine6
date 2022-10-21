package org.BluminEngine6;

import org.BluminEngine6.Editor.SceneManagment.SceneMannager;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.EventSystem.Action;
import org.BluminEngine6.Legacy.Utils.EventSystem.IAction;
import org.BluminEngine6.Legacy.Utils.Metadata;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Object.Tags.TagMannager;
import org.BluminEngine6.Render.Display;
import org.BluminEngine6.Render.DisplayMode;
import org.BluminEngine6.Render.Resolution;
import org.apache.commons.io.FileUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.lwjgl.glfw.GLFW.*;

public class Application {
    public static Action<IAction> Update = new Action<>();
    public static Action<IAction> Start = new Action<>();
    public static Action<IAction> Awake = new Action<>();
    public static Action<IAction> OnExit = new Action<>();


    static Metadata metadata;
    static ResourceMannager resourceMannager;
    static Display display;
    static File tempFolder;
    static TagMannager tagMannager = new TagMannager();;

    public static void Run(Resolution res,DisplayMode dm) {
        try {
            Debug.log("Starting BluminEngine6 0.0.1.0_DevSystem");
            metadata = new Metadata("Config.ini");

            tempFolder = new File(metadata.ResourceFolder + "/.temp");
            tempFolder.mkdirs();
            Files.setAttribute(tempFolder.toPath(), "dos:hidden", true);

            resourceMannager = new ResourceMannager(metadata.MainArchiveFile);


            Debug.log("Opening game: " + metadata.GameName + " Version " + metadata.gameVersion);

            display = new Display();
            display.CreateWindow(metadata.GameName, res, dm);
            GLCapabilities cap = GL.createCapabilities();
            GL.setCapabilities(cap);
            if(!cap.forwardCompatible || !cap.OpenGL20) {
                Utils.CrashApp(-3, "OpenGL not fully supported on this device");
            }


            Awake.Invoke();


            Start.Invoke();
            while (!glfwWindowShouldClose(display.getWindow()) ) {
                Update.Invoke();
                //renderer.Render();

                glfwSwapBuffers(display.getWindow());
                glfwPollEvents();
            }
            tempFolder.delete();
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

    public static Display getDisplay() {
        return display;
    }

    public static TagMannager getTagMannager() {
        return tagMannager;
    }
}

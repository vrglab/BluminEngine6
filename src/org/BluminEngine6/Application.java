package org.BluminEngine6;

import org.BluminEngine6.Editor.Rendering.MasterRenderer;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.EventSystem.Action;
import org.BluminEngine6.Legacy.Utils.EventSystem.IAction;
import org.BluminEngine6.Legacy.Utils.Metadata;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.BluminEngine6.Legacy.Utils.Version;
import org.BluminEngine6.Object.Tags.TagMannager;
import org.BluminEngine6.Render.Display;
import org.BluminEngine6.Render.DisplayMode;
import org.BluminEngine6.Render.Renderer;
import org.BluminEngine6.Render.Resolution;
import org.BluminEngine6.Utils.Archives.Archive;
import org.BluminEngine6.Utils.Archives.ArchiveMannager;
import org.apache.commons.io.FilenameUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_BACK;

public class Application {
    public static Action<IAction> Update = new Action<>();
    public static Action<IAction> Start = new Action<>();
    public static Action<IAction> Awake = new Action<>();
    public static Action<IAction> OnExit = new Action<>();

    public static Version version = new Version(0,0,0,0,"Pre-Alpha");
    static Metadata metadata;
    static List<Archive> resourceMannager = new ArrayList<>();
    static Archive coreResources;
    static Display display;
    static File tempFolder;
    static TagMannager tagMannager = new TagMannager();
    static Renderer renderer = new MasterRenderer();

    public static void Run(Resolution res,DisplayMode dm) {
        try {
            Debug.log("Starting BluminEngine6 " + version);
            metadata = new Metadata("Config.ini");

            tempFolder = new File(metadata.ResourceFolder + "/.temp");
            tempFolder.mkdirs();
            Files.setAttribute(tempFolder.toPath(), "dos:hidden", true);

            coreResources = ArchiveMannager.DeserializeArchiveFile(metadata.ResourceFolder + "/Core.baf");
            File f = new File(metadata.ResourceFolder);

            for (File file: f.listFiles()) {
                if(file.isFile()) {
                    if(FilenameUtils.isExtension(file.getAbsolutePath(), "baf")) {
                        if(!FilenameUtils.getBaseName(file.getAbsolutePath()).equals("Core")) {
                            resourceMannager.add(ArchiveMannager.DeserializeArchiveFile(file.getAbsolutePath()));
                        }
                    }
                }
            }



            Debug.log("Opening game: " + metadata.GameName + " Version " + metadata.gameVersion);

            display = new Display();
            display.CreateWindow(metadata.GameName, res, dm);
            GLCapabilities cap = GL.createCapabilities();
            GL.setCapabilities(cap);
            if(!cap.forwardCompatible || !cap.OpenGL11 || !cap.GL_ARB_draw_buffers) {
                Utils.CrashApp(-3, "OpenGL not fully supported on this device");
            }
            Awake.Invoke();
            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);

            Start.Invoke();
            while (!glfwWindowShouldClose(display.getWindow()) ) {
                Update.Invoke();
                renderer.OnRender();

                glfwSwapBuffers(display.getWindow());
                glfwPollEvents();
            }

            Debug.log("Closing BluminEngine6 " + version);
            DestroyTempFolder();
            display.Close(OnExit);
        } catch (Throwable e) {
            DestroyTempFolder();
            Utils.CrashApp(-1, e);
        }
    }

    private static void DestroyTempFolder() {
        if(tempFolder.listFiles().length > 0) {
            for (File files: tempFolder.listFiles()) {
                files.delete();
            }
        }
        tempFolder.delete();
    }

    /**
     * Game engines pre built core resources
     * @author: Vrglab
     */
    public static Archive getCoreResources() {
        return coreResources;
    }

    public static Archive getArchive(int id) {
        return resourceMannager.get(id);
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

    public static Renderer getRenderer() {
        return renderer;
    }
}

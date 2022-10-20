package org.BluminEngine6.Render;


import org.BluminEngine6.Legacy.Utils.EventSystem.Action;
import org.BluminEngine6.Legacy.Utils.Utils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Class used for handling window's
 * @author Vrglab
 */
public class Display{

    public long getWindow() {
        return window;
    }

    private long window;
    private Resolution CurentScreenRes;


    private DisplayMode CurentDisplayMode;

    private GLFWWindowSizeCallback sizeCallback = new GLFWWindowSizeCallback() {
        @Override
        public void invoke(long window, int width, int height) {
            CurentScreenRes.HIGHT = height;
            CurentScreenRes.WIDTH = width;
            GL11.glViewport(0,0,CurentScreenRes.WIDTH, CurentScreenRes.HIGHT);
        }
    };

    public void CreateWindow(String name, Resolution res, DisplayMode mode) {
        if (!glfwInit()) {
            Utils.CrashApp(-12, "Glfw could not be initialized");
        }
        glfwDefaultWindowHints();

        long monitor = NULL;
        switch(mode) {
            case Windowed:
                glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
                glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
                glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);

                break;
            case Fullscreen:
                glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
                glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
                glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
                monitor = glfwGetPrimaryMonitor();
                break;
            case WindowedLocked:
                glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
                glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
                glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);
                break;
            case WindowedLockedBorderless:
                glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
                glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
                glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
                break;
        }

        glfwWindowHint(GLFW_REFRESH_RATE, res.getFPS());
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        window = glfwCreateWindow(res.getWIDTH(), res.getHIGHT(), name, monitor, NULL);

        if ( window == NULL ) {
            Utils.CrashApp(-13, "Failed to create the GLFW window");
        }
        CurentScreenRes = res;
        CurentDisplayMode = mode;
        GLFW.glfwFocusWindow(window);
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    public void Close() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void Close(Action actToinvoke) {
        actToinvoke.Invoke();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }



    public Resolution getCurentScreenRes() {
        return CurentScreenRes;
    }


    public  void make2D() {
        //Remove the Z axis
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho(0, CurentScreenRes.getWIDTH(), 0, CurentScreenRes.getHIGHT(), -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
    }

    public  void make3D() {
        //Restore the Z axis
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
    }
}

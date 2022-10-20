package org.BluminEngine6.Render;


import java.awt.*;

public class Resolution {
    public int WIDTH;
    public int HIGHT;
    private int FPS;

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHIGHT() {
        return HIGHT;
    }

    public int getFPS() {
        return FPS;
    }

    public Resolution(int w, int h) {
        WIDTH = w;
        HIGHT = h;
        FPS = 190;
    }

    public Resolution(int w, int h, int fps) {
        WIDTH = w;
        HIGHT = h;
        FPS = fps;
    }

    public static Resolution GetMaxScreenRes() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        return new Resolution(width, height);
    }

    public static Resolution CurentRes(){
        return new Resolution((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), 190);
    }

    @Override
    public String toString() {
        return "Resolution{" + WIDTH + ", " + HIGHT +", " + FPS +'}';
    }
}

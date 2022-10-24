package org.BluminEngine6.Dev.Console;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.internal.ImGuiWindow;
import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.objActionData;
import org.BluminEngine6.Object.Object;

import java.io.*;

public class Cmd extends Object {
    objActionData logicsData = new objActionData();
    ImGuiImplGlfw ss;
    ImGuiImplGl3 s;

    public Cmd() {
            logicsData.OnDestroy = () -> {
                Destroy();
            };

            logicsData.OnExit = () -> {
                logicsData.OnDestroy.Run();
                OnExit();
            };

            logicsData.OnPreInit = () -> {
                PreInit();
            };

            logicsData.OnRender = () -> {
                OnRender();
            };

            logicsData.OnSceneLoad = () -> {
                SceneLoad();
            };

            logicsData.OnUpdate = () -> {
                Update();
            };

            logicsData.OnStart = () -> {
                Init();
            };
            Application.Awake.addListener(logicsData.OnPreInit);
            Application.Start.addListener(logicsData.OnStart);
            Application.Update.addListener(logicsData.OnUpdate);
            Application.OnExit.addListener(logicsData.OnExit);
            Application.getRenderer().OnRender.addListener(logicsData.OnRender);
    }

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {
        ss.newFrame();
        ImGui.newFrame();


        ImGui.render();
        s.renderDrawData(ImGui.getDrawData());

    }

    @Override
    public void Init() {
        ImGui.createContext();
        s = new ImGuiImplGl3();
        ss = new ImGuiImplGlfw();
        s.init();
        ss.init(Application.getDisplay().getWindow(), false);

    }

    @Override
    public void PreInit() {

    }

    @Override
    public void OnExit() {
    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {
        ss.dispose();
        s.dispose();
        ImGui.destroyContext();
    }
}

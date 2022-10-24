package org.BluminEngine6.Legacy.Utils.Logics;

import org.BluminEngine6.Legacy.Utils.objActionData;
import org.BluminEngine6.Object.Component;
import org.BluminEngine6.Object.Object;
import org.newdawn.slick.util.Log;

public abstract class LogicObject extends Object {
    public objActionData logicsData = new objActionData();

    public LogicObject() {
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
    }

}

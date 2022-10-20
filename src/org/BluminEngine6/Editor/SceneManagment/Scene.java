package org.BluminEngine6.Editor.SceneManagment;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Camera;
import org.BluminEngine6.Legacy.Utils.objActionData;
import org.BluminEngine6.Object.BluminBehaviour;
import org.BluminEngine6.Object.Object;

import java.util.HashMap;
import java.util.Map;

public abstract class Scene extends Object {
    protected objActionData logicsData = new objActionData();
    private HashMap<Integer, BluminBehaviour> gameObjects = new HashMap<>();
    public BluminBehaviour mainCamera = new BluminBehaviour() {

        Camera main = new Camera();

        @Override
        public void SceneLoad() {

        }

        @Override
        public void Destroy() {

        }

        @Override
        public void Update() {

        }

        @Override
        public void OnRender() {

        }

        @Override
        public void Init() {

        }

        @Override
        public void PreInit() {
            RegisterComponant(main);
        }

        @Override
        public void OnExit() {

        }
    };

    public Scene() {
        RegisterGameObject(mainCamera);
        logicsData.OnDestroy = () -> {
            for (BluminBehaviour bb: gameObjects.values()) {
                if(bb.Active){
                    bb.logicsData.OnDestroy.Run();
                }
            }
            Destroy();
        };

        logicsData.OnExit = () -> {
            for (BluminBehaviour bb: gameObjects.values()) {
                if(bb.Active){
                    bb.logicsData.OnExit.Run();
                }
            }
            logicsData.OnDestroy.Run();
            OnExit();
        };

        logicsData.OnPreInit = () -> {
            for (BluminBehaviour bb: gameObjects.values()) {
                if(bb.Active){
                    bb.logicsData.OnPreInit.Run();
                }
            }
            PreInit();
        };

        logicsData.OnRender = () -> {
                OnRender();
            for (BluminBehaviour bb: gameObjects.values()) {
                if(bb.Active){
                    bb.logicsData.OnRender.Run();
                }
            }
        };

        logicsData.OnSceneLoad = () -> {
                SceneLoad();
            for (BluminBehaviour bb: gameObjects.values()) {
                if(bb.Active){
                    bb.logicsData.OnSceneLoad.Run();
                }
            }
        };

        logicsData.OnUpdate = () -> {
                Update();
            for (BluminBehaviour bb: gameObjects.values()) {
                if(bb.Active){
                    bb.logicsData.OnUpdate.Run();
                }
            }

        };

        logicsData.OnStart = () -> {
                Init();
            for (BluminBehaviour bb: gameObjects.values()) {
                if(bb.Active){
                    bb.logicsData.OnStart.Run();
                }
            }
        };


        Application.Awake.addListener(logicsData.OnPreInit);
        Application.Start.addListener(logicsData.OnStart);
        Application.Update.addListener(logicsData.OnUpdate);
        Application.OnExit.addListener(logicsData.OnExit);
        //TODO: bind the onRender method to the master Renderers event
    }

    public <t extends BluminBehaviour> t RegisterGameObject(t obj) {
        gameObjects.put(obj.hashCode(),obj);
        return obj;
    }


    public <t extends BluminBehaviour> t GetGameObject(Class<t> obj) {
        for (BluminBehaviour bb: gameObjects.values()) {
            if(bb.getClass().isAssignableFrom(obj)){
                return obj.cast(bb);
            }
        }
        return null;
    }
}

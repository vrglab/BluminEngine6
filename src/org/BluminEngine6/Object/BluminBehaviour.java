package org.BluminEngine6.Object;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.objActionData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BluminBehaviour extends Object{
        protected objActionData logicsData = new objActionData();
        private List<Component> AttachedComponants = new ArrayList<>();

        protected BluminBehaviour() {

            logicsData.OnDestroy = () -> {
                Destroy();
            };

            logicsData.OnExit = () -> {
                logicsData.OnDestroy.Run();
                OnExit();
            };

            logicsData.OnPreInit = () -> {
                if(Active) {
                    PreInit();
                }
            };

            logicsData.OnRender = () -> {
                if(Active) {
                    OnRender();
                }
            };

            logicsData.OnSceneLoad = () -> {
                if(Active) {
                    SceneLoad();
                }
            };

            logicsData.OnUpdate = () -> {
                if(Active) {
                    Update();
                }
            };

            logicsData.OnStart = () -> {
                if(Active) {
                    Init();
                }
            };

            Application.Awake.addListener(logicsData.OnPreInit);
            Application.Start.addListener(logicsData.OnStart);
            Application.Update.addListener(logicsData.OnUpdate);
            Application.OnExit.addListener(logicsData.OnExit);
        }

    public <t extends Component> t RegisterComponant(t component) {


        AttachedComponants.add(component);
        return component;
    }

    public <t extends Component> void UnregisterComponant(t component) {
        if(AttachedComponants.contains(component)) {
            AttachedComponants.remove(AttachedComponants.lastIndexOf(component));
        }
    }

    public <t extends Component> t getComponant(Class<t> componantType) {
        for (Component comp: AttachedComponants) {
            if(comp.getClass().isAssignableFrom(componantType)) {
                try{
                    return componantType.cast(comp);
                } catch(ClassCastException e){
                    Debug.logException(e);
                }
            }
        }
        return null;
    }
}
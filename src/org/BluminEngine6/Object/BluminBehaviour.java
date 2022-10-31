package org.BluminEngine6.Object;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Transform;
import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Legacy.Utils.Math.Vector3;
import org.BluminEngine6.Legacy.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

public abstract class BluminBehaviour extends Object{
        public objActionData logicsData;
        private List<Component> AttachedComponants;

        public Transform transform;

        protected BluminBehaviour() {
            logicsData = new objActionData();
            AttachedComponants = new ArrayList<>();
            transform = new Transform();
            RegisterComponent(transform);

            logicsData.OnDestroy = () -> {
                for (Component c: AttachedComponants) {
                    if(c.Active) {
                        c.LogicsData.OnDestroy.Run();
                    }
                }
                Destroy();
            };

            logicsData.OnExit = () -> {
                logicsData.OnDestroy.Run();
                OnExit();
                for (Component c: AttachedComponants) {
                    if(c.Active) {
                        c.LogicsData.OnExit.Run();
                    }
                }
            };

            logicsData.OnAwake = () -> {
                    Awake();
                for (Component c: AttachedComponants) {
                    if(c.Active) {
                        c.LogicsData.OnAwake.Run();
                    }
                }
            };

            logicsData.OnRender = () -> {
                    OnRender();
                for (Component c: AttachedComponants) {
                    if(c.Active) {
                        c.LogicsData.OnRender.Run();
                    }
                }
            };

            logicsData.OnSceneLoad = () -> {
                    SceneLoad();
                for (Component c: AttachedComponants) {
                    if(c.Active) {
                        c.LogicsData.OnSceneLoad.Run();
                    }
                }
            };

            logicsData.OnUpdate = () -> {

                    Update();
                for (Component c: AttachedComponants) {
                    if(c.Active) {
                        c.LogicsData.OnUpdate.Run();
                    }
                }
            };

            logicsData.OnStart = () -> {

                    Init();
                for (Component c: AttachedComponants) {
                    if(c.Active) {
                        c.LogicsData.OnStart.Run();
                    }
                }
            };

            tag = Application.getTagMannager().GetTag(0);
        }

    public <t extends Component> t RegisterComponent(t component) {
        component.Parent = this;
        component.transform = transform;
        AttachedComponants.add(component);
        return component;
    }

    public <t extends Component> void UnregisterComponant(t component) {
        if(AttachedComponants.contains(component)) {
            AttachedComponants.remove(AttachedComponants.lastIndexOf(component));
        }
    }

    public void Enable() {
        if(Active == false) {
            Active = true;
            logicsData.OnAwake.Run();
            logicsData.OnStart.Run();
        }
    }

    public void Disable() {
        if(Active == true) {
            logicsData.OnDestroy.Run();
            Active = false;
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

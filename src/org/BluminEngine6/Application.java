package org.BluminEngine6;

import org.BluminEngine6.Legacy.Utils.EventSystem.Action;
import org.BluminEngine6.Legacy.Utils.EventSystem.IAction;
import org.BluminEngine6.Legacy.Utils.Metadata;
import org.BluminEngine6.Legacy.Utils.ResourceMannager.ResourceMannager;

public class Application {
    public static Action<IAction> Update = new Action<>();
    public static Action<IAction> Start = new Action<>();
    public static Action<IAction> Awake = new Action<>();
    public static Action<IAction> OnExit = new Action<>();

    private static Metadata metadata;

    private static ResourceMannager resourceMannager;

    public static void Run() {

    }


    public static ResourceMannager getResourceMannager() {
        return resourceMannager;
    }

    public static Metadata getMetadata() {
        return metadata;
    }
}

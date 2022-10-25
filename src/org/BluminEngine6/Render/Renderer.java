package org.BluminEngine6.Render;

import org.BluminEngine6.Legacy.Utils.EventSystem.Action;
import org.BluminEngine6.Legacy.Utils.EventSystem.IAction;

public abstract class Renderer {
    public Action<IAction> OnRender = new Action<>();

    public abstract void OnRender();
}

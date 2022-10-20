package org.BluminEngine6.Object;

import org.BluminEngine6.Application;
import org.BluminEngine6.Editor.Componants.Transform;
import org.BluminEngine6.Legacy.Utils.objActionData;

public abstract class Component extends Object{
    protected objActionData LogicsData = new objActionData();
    protected BluminBehaviour Parent;

    public Transform transform = Transform.DefaultZero;

    public Component() {
        tag = Application.getTagMannager().GetTag(2);
    }


}

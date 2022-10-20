package org.BluminEngine6.Object;

import org.BluminEngine6.Legacy.Utils.Logics.IObjLogic;

public abstract class Object extends java.lang.Object implements IObjLogic {
    private Class<? extends Object> thisClass = getClass();
    public String name = thisClass.getName();
}

package org.BluminEngine6.Object;

import org.BluminEngine6.Legacy.Utils.Logics.IObjLogic;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public abstract class Object extends java.lang.Object implements IObjLogic, Serializable {
    private final Class<? extends Object> This = getClass();
    public String name = This.getName();
    public boolean Active = true;
}

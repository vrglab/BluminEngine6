package org.BluminEngine6.Object;

import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.Logics.IObjLogic;
import org.BluminEngine6.Object.Tags.Tag;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public abstract class Object extends java.lang.Object implements IObjLogic, Serializable {

    public String name = getClass().getSimpleName();
    public boolean Active = true;
    public Tag tag = Application.getTagMannager().GetTag(0);
}

package org.BluminEngine6.Object.Tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagMannager {
    static List<Tag> tags = new ArrayList<>();

    public TagMannager() {
        tags.add(new Tag("Default", 0));
        tags.add(new Tag("Main", 1));
        tags.add(new Tag("Component", 2));
    }

    public String GetTagName(int id) {
        for (Tag t: tags) {
            if(t.id == 0) {
                return t.name;
            }
        }
        return "";
    }

    public Tag GetTag(int id) {
        for (Tag t: tags) {
            if(t.id == 0) {
                return t;
            }
        }
        return null;
    }
}

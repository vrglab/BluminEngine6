package org.BluminEngine6.Object.Tags;

public final class Tag {
    String name = "Default";
    int id = 0;

    public Tag(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}

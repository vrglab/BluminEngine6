package org.BluminEngine6.Utils.Archives;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Archive implements Serializable {
    private Map<Integer, ArchiveFolder> foldors = new HashMap<>();
    private Map<Integer, ArchiveFile> rootFiles = new HashMap<>();
}

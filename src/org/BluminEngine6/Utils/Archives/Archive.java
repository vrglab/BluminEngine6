package org.BluminEngine6.Utils.Archives;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Archive implements Serializable {
    private Map<Integer, ArchiveFolder> folders = new HashMap<>();
    private Map<Integer, ArchiveFile> files = new HashMap<>();
}

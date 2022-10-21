package org.BluminEngine6.Utils.Archives;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;

public class ArchiveFolder implements Serializable {
    private List<Integer> folders = new ArrayList<>();
    private Map<Integer, ArchiveFile> Files = new HashMap<>();
    private int id = NULL, ParentFolderId = NULL;
    private String name = "";

}

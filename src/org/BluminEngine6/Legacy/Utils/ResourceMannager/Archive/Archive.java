package org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;

public class Archive implements Serializable {
    public String name;
    public List<ArchivedFile> archivedFiles = new ArrayList<>();
    public int Id = NULL;
    public int ParentArchiveId = NULL;

    protected static int idFileCounter = 0;
}

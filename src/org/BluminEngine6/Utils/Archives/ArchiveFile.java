package org.BluminEngine6.Utils.Archives;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import static org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;

public class ArchiveFile implements Serializable {
    private int id = NULL, FolderId = NULL;
    public String FileName;
    public String Extension;
    protected String fileData;

    public ArchiveFile(int id, String fileName, String extension, String fileData, int FolderId) {
        this.id = id;
        FileName = fileName;
        Extension = extension;
        this.fileData = fileData;
        this.FolderId = FolderId;
    }

    public int getId() {
        return id;
    }

    public int getFolderId() {
        return FolderId;
    }

    public String getFileData() {
        return fileData;
    }
}

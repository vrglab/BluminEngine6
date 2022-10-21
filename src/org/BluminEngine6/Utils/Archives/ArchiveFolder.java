package org.BluminEngine6.Utils.Archives;

import org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchivedFile;
import org.BluminEngine6.Utils.Utils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;

public class ArchiveFolder implements Serializable {
    private Map<Integer, ArchiveFolder> folders = new HashMap<>();
    private Map<Integer, ArchiveFile> files = new HashMap<>();
    private int id = NULL, ParentFolderId = NULL;
    public String name = "";

    private final AtomicInteger FileCounter = new AtomicInteger();
    private final AtomicInteger FolderCounter = new AtomicInteger();

    public ArchiveFolder getFolder(int id) {
        return folders.get(id);
    }
    public ArchiveFile getFileInRoot(int id) {
        return files.get(id);
    }

    public ArchiveFile FileToArchiveFile(String file) {

        File f = new File(file);
        String abPath = f.getAbsolutePath();
        int id = FileCounter.getAndIncrement();
        try {
            ArchiveFile af = new ArchiveFile(id, FilenameUtils.getName(abPath),FilenameUtils.getExtension(abPath), Utils.EncodeFileWithBase64(abPath));
            files.put(id, af);
            return af;
        } catch (IOException e) {
            return null;
        }
    }

}

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
        if(!f.isFile()) {
            return null;
        }
        try {
            ArchiveFile af = new ArchiveFile(id, FilenameUtils.getName(abPath),FilenameUtils.getExtension(abPath), Utils.EncodeFileWithBase64(abPath));
            files.put(id, af);
            return af;
        } catch (IOException e) {
            return null;
        }
    }

    public ArchiveFolder DirectoryToArchiveFoldor(String directory) {
        File f = new File(directory);
        String abPath = f.getAbsolutePath();
        int id = FileCounter.getAndIncrement();
        ArchiveFolder foldor = new ArchiveFolder();
        if(!f.isDirectory()) {
            return null;
        }

        foldor.name = FilenameUtils.getBaseName(f.getAbsolutePath());
        for (File fil: f.listFiles()) {
            if(fil.isFile()){
                FileToArchiveFile(fil.getAbsolutePath());
            } else{

            }
        }
    }

    public ArchiveFile CreateEmptyFile(String name, String extension) {
        int id = FileCounter.getAndIncrement();
        try {
            ArchiveFile af = new ArchiveFile(id, name,extension, Utils.EncodeStringWithBase64("null"));
            files.put(id, af);
            return af;
        } catch (IOException e) {
            return null;
        }
    }

}

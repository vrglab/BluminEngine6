package org.BluminEngine6.Utils.Archives;

import org.BluminEngine6.Legacy.Utils.Debuging.Debug;
import org.BluminEngine6.Utils.Utils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
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
    public ArchiveFile getFile(int id) {
        return files.get(id);
    }
    public ArchiveFile FileToArchiveFile(String file) {
        File f = new File(file);
        String abPath = f.getAbsolutePath();
        if(!f.isFile()) {
            return null;
        }

        int id = FileCounter.getAndIncrement();
        try {
            ArchiveFile af = new ArchiveFile(id, FilenameUtils.getName(abPath),FilenameUtils.getExtension(abPath), Utils.EncodeFileWithBase64(abPath), id);
            return af;
        } catch (IOException e) {
            Debug.logException(e);
            return null;
        }
    }

    public ArchiveFolder DirectoryToArchiveFoldor(String directory) {
        File f = new File(directory);
        String abPath = f.getAbsolutePath();
        if(!f.isDirectory()) {
            return null;
        }
        int id = FolderCounter.getAndIncrement();
        ArchiveFolder folder = new ArchiveFolder();

        folder.id = id;
        folder.name = FilenameUtils.getBaseName(abPath);
        for (File fil: f.listFiles()) {
            if(fil.isFile()){
              ArchiveFile af =  folder.FileToArchiveFile(fil.getAbsolutePath());
              folder.files.put(af.getId(), af);
            } else{
              ArchiveFolder a =  folder.DirectoryToArchiveFoldor(fil.getAbsolutePath());
              a.ParentFolderId = id;
              folder.folders.put(a.id, a);
            }
        }

        return folder;
    }
    public ArchiveFile CreateEmptyFile(String name, String extension) {
        int id = FileCounter.getAndIncrement();
        try {
            ArchiveFile af = new ArchiveFile(id, name,extension, Utils.EncodeStringWithBase64("null"), NULL);
            files.put(id, af);
            return af;
        } catch (IOException e) {
            return null;
        }
    }

}

package org.BluminEngine6.Utils.Archives;

import org.BluminEngine6.Utils.Utils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Archive implements Serializable {
    private ArchiveFolder root  = new ArchiveFolder();
    public Archive(String name) {
        root.name = name;
    }

    public Archive() {
        root.name = "Root";
    }

    public Archive(String directory, String name) {
        root = root.DirectoryToArchiveFoldor(directory);
        root.name = name;
    }

    public ArchiveFolder getRoot() {
        return root;
    }
}

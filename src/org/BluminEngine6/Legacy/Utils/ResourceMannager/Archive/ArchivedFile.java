package org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive;

import java.io.Serializable;
import java.util.Base64;

import static BluminEngine5.Utils.ResourceMannager.Archive.ArchiveMannager.NULL;

public class ArchivedFile implements Serializable {
    public String FileName;
    public String Extension;
    public String fileData;
    public int ID = NULL;
    public int ArchiveId = NULL;

    public byte[] GetDecodedData() {
        return Base64.getDecoder().decode(fileData);
    }
}

package org.BluminEngine6.Utils.Archives;

import org.BluminEngine6.Application;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.Base64;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class ArchiveMannager {


    public static Archive DeserializeArchiveFile(String file) throws IOException {
        if(!FilenameUtils.getExtension(file).equals("baf")){
           return null;
        }
        File f = new File(file);
        byte[] fileData = FileUtils.readFileToByteArray(f);
        return DecompressAndDecode(fileData);
    }

    public static File WriteArchiveFileToFile(String directory, ArchiveFile file) throws IOException {
        String location = directory + "/" + file.FileName + "." + file.Extension;
        File f = new File(location);
        FileUtils.writeByteArrayToFile(f, file.getFileData());
        return f;
    }

    public static File WriteArchiveFileToFile(String directory, ArchiveFile file, UUID ui) throws IOException {
        String location = directory + "/" + ui.toString() + " " + file.FileName + "." + file.Extension;
        File f = new File(location);
        FileUtils.writeByteArrayToFile(f, file.getFileData());
        return f;
    }

    public static Archive SerializeDirectoryToArchive(String FromDirectory, String ToDirectory) throws IOException {
        Archive archive = DirectoryToArchive(FromDirectory);
        byte[] data = CompressAndEncode(archive);
        String filename = FilenameUtils.getBaseName(FromDirectory) + ".baf";
        File file = new File(ToDirectory + "/" + filename);
        FileUtils.writeByteArrayToFile(file, data, false);
        return archive;
    }

    public static byte[] CompressAndEncode(Archive arc) throws IOException {
        byte[] data = SerializationUtils.serialize(arc);
        byte[] dataEncoded = Base64.getEncoder().encode(data);
        byte[] comp1 = compress(dataEncoded);
        byte[] comp2 = compress(comp1);
        return comp2;
    }

    private static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream defl = new DeflaterOutputStream(out);
        defl.write(data);
        defl.flush();
        defl.close();
        out.close();
        return out.toByteArray();
    }

    private static byte[] Decompress(byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InflaterOutputStream infl = new InflaterOutputStream(out);
        infl.write(data);
        infl.flush();
        infl.close();
        out.close();
        return out.toByteArray();
    }

    public static Archive DecompressAndDecode(byte[] data) throws IOException {
        byte[] decomp1 = Decompress(data);
        byte[] DecompresedData = Decompress(decomp1);
        byte[] DecodedData = Base64.getDecoder().decode(DecompresedData);

        return SerializationUtils.deserialize(DecodedData);
    }

    public static Archive DirectoryToArchive(String directory) {
        Archive arc = new Archive(directory, FilenameUtils.getBaseName(directory));
        return arc;
    }

    public static File LoadArchiveFileToTempFile(ArchiveFile af) throws IOException {
        UUID id = UUID.randomUUID();
        return WriteArchiveFileToFile(Application.getTempFolder().getAbsolutePath(), af, id);
    }

}

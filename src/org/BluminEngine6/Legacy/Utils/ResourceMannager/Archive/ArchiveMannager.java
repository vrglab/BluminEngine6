package org.BluminEngine6.Legacy.Utils.ResourceMannager.Archive;

import BluminEngine5.Utils.Debuging.Debug;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class ArchiveMannager implements Serializable{
    public static final int NULL = -0000000;
    private List<Archive> Archives = new ArrayList<>();


    private static int idArchiveCounter = 0;

    public ArchiveMannager() {
        if(Archives.size() <= 0) {
            Archive a = CreateArchive();
            Archives.add(a);
        }
    }

    public ArchivedFile ArchiveFile(String File) {
        ArchivedFile af = new ArchivedFile();
        Path path = Paths.get(File);
        af.FileName = FilenameUtils.getBaseName(File);
        af.Extension = FilenameUtils.getExtension(File);
        try {

            byte[] fileContent = FileUtils.readFileToByteArray(path.toFile());
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            af.fileData = encodedString;
            af.ID = NULL;
        } catch (IOException e) {
            Debug.logError(e.getMessage());
        }
        return  af;
    }
    public void PutFileInArchive(ArchivedFile file, int ArchiveId) {
        Archive ar = Archives.get(ArchiveId);
        file.ArchiveId = ArchiveId;
        file.ID = ar.ParentArchiveId;
        ar.ParentArchiveId++;
        ar.archivedFiles.add(file);

        Archives.remove(ArchiveId);
        Archives.add(ArchiveId, ar);
    }
    public void PutFileInArchive(String file, int ArchiveId) {
        Archive ar = Archives.get(ArchiveId);
        ArchivedFile af = ArchiveFile(file);
        af.ArchiveId = ArchiveId;
        af.ID = ar.ParentArchiveId;
        ar.ParentArchiveId++;
        ar.archivedFiles.add(af);
        Archives.remove(ArchiveId);
        Archives.add(ArchiveId, ar);
    }
    public void PutFileInArchive(String file) {
        Archive ar = Archives.get(0);
        ArchivedFile af = ArchiveFile(file);
        af.ArchiveId = 0;
        af.ID = ar.ParentArchiveId;
        ar.ParentArchiveId++;
        ar.archivedFiles.add(af);
        Archives.remove(0);
        Archives.add(0,ar);
    }
    public ArchivedFile GeFileFromArchive(int FileId, int ArchiveId) {
        try {
            return Archives.get(ArchiveId).archivedFiles.get(FileId);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
    public Archive CreateArchive() {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.name = "Default";
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive CreateArchive(String name) {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.name = name;
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive CreateArchive(int parentArchive) {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.ParentArchiveId = parentArchive;
        ar.name = "Default Child of " + parentArchive + " with the id of " + ar.Id;
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive CreateArchive(int parentArchive, String name) {
        Archive ar = new Archive();
        ar.Id = idArchiveCounter;
        ar.ParentArchiveId = parentArchive;
        ar.name = name;
        idArchiveCounter++;
        Archives.add(ar);
        return ar;
    }
    public Archive GetArchive(int id) {
        return Archives.get(id);
    }

    //TODO: Write Archive/File deletion functionality

    public static ArchiveMannager WriteFromDirectory(String directory){
        Path path = Paths.get(directory);
        ArchiveMannager am = new ArchiveMannager();
        if(path.toFile().isDirectory()) {

        }
        return am;
    }





    public static void Compress(ArchiveMannager archive, String file) throws Exception{
        try {
            UUID id = UUID.randomUUID();
            if(Files.exists(Paths.get(file+".baf"))) {
                Files.delete(Paths.get(file+".baf"));
            }

            FileOutputStream fileOutputStream = new FileOutputStream("Res/temp " + id.toString() +".baf");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(archive);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            FileInputStream fis = new FileInputStream("Res/temp " + id.toString() +".baf");
            FileOutputStream fos = new FileOutputStream(file+".baf");
            DeflaterOutputStream dos = new DeflaterOutputStream(fos);
            int data;
            while ((data=fis.read())!=-1)
            {
                dos.write(data);
            }
            fis.close();
            dos.close();
            Files.delete(Paths.get("Res/temp " +id.toString()  +".baf"));

        }catch (Exception e) {
            Debug.logError(e.getMessage());
        }
    }

    public static ArchiveMannager Decompress(String file) throws Exception{
        UUID id = UUID.randomUUID();
        FileInputStream fis = new FileInputStream(file+".baf");
        FileOutputStream fos = new FileOutputStream("Res/temp " + id.toString() +".baf");
        InflaterInputStream iis = new InflaterInputStream(fis);
        fos.write(iis.readAllBytes());
        fos.close();
        FileInputStream fileInputStream = new FileInputStream("Res/temp " + id.toString() +".baf");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArchiveMannager r =  (ArchiveMannager) objectInputStream.readObject();
        objectInputStream.close();
        Files.delete(Paths.get("Res/temp " + id.toString() +".baf"));
        return r;
    }


    public int GetSize() {
        return  Archives.size();
    }
    public List<Archive> getArchives() {
        return Archives;
    }
}

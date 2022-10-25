package org.BluminEngine6.Utils;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class Utils {

    public static byte[] DecodedDataFromBase64(String data) {
        return Base64.getDecoder().decode(data);
    }
    public static String EncodeFileWithBase64(String file) throws IOException {
        File f = new File(file);

        byte[] array = FileUtils.readFileToByteArray(f);

        return Base64.getEncoder().encodeToString(array);
    }

    public static String EncodeStringWithBase64(String data) throws IOException {
        byte[] array = data.getBytes();
        return Base64.getEncoder().encodeToString(array);
    }

    public static byte[] Compress(byte[] raw) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream defl = new DeflaterOutputStream(out);

        try {
            defl.write(raw);
            defl.flush();
            defl.close();
        } catch (IOException e) {
        }
        return out.toByteArray();
    }

    public static byte[] Deompress(byte[] raw) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InflaterOutputStream defl = new InflaterOutputStream(out);

        try {
            defl.write(raw);
            defl.flush();
            defl.close();
        } catch (IOException e) {
        }
        return out.toByteArray();
    }
}

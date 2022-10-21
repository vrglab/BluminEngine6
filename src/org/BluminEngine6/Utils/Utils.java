package org.BluminEngine6.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Utils {

    public static byte[] DecodedDataFromBase64(String data) {
        return Base64.getDecoder().decode(data);
    }
    public static String EncodeFileWithBase64(String file) throws IOException {
        File f = new File(file);

        byte[] array = FileUtils.readFileToByteArray(f);

        return Base64.getEncoder().encodeToString(array);
    }
}

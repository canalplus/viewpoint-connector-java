package fr.viewpoint.client.storage.impl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.viewpoint.client.storage.impl.LiveStorageImpl.WrapperData;

public class StringStorageUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Return a String representation
     * 
     * @param localWrapper
     * @return
     * @throws IOException
     */
    public static String get(WrapperData localWrapper) throws IOException {
        try {
            return mapper.writeValueAsString(localWrapper);
        } catch (IOException e) {
            throw e;
        }
    }

}

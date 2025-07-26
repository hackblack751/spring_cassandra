package org.huy.util;

import java.util.UUID;

public class AppUtils {

    public static boolean isValidUUID(String id) {
        if(id == null || id.isBlank()) return false;

        // There must be other better way.
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}

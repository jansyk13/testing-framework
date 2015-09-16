package edu.jsykora.testing;

public class SessionIdStorage {

    private static String sessionId = null;

    public static void saveSessionIdToSomeStorage(String string) {
        sessionId = string;
    }

    public static String getPreviousSessionIdFromSomeStorage() {
        return sessionId;
    }

}

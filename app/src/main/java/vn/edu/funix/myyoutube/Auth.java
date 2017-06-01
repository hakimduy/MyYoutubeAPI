package vn.edu.funix.myyoutube;

/**
 * Shared class used by every sample. Contains methods for authorizing a user and caching credentials.
 */
public class Auth {
    public static final String Key = "AIzaSyAuTxkv7GgsxKG2vQoJhfOJndzUS6MTMvw";

    public static String PlayList = "PLK3MgqxfgsiCAqPLTQzoU9FSoHk9DrVfZ";

    public static String VideoJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + PlayList + "&key=" + Key + "&maxResults=50";

}
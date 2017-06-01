package vn.edu.funix.myyoutube;

/**
 * Created by Duy on 05/31/17.
 */

public class VideoYoutube {
    private String Title;
    private String Thumbnail;
    private String IdVideo;

    public VideoYoutube(String title, String thumbnail, String idVideo) {
        Title = title;
        Thumbnail = thumbnail;
        IdVideo = idVideo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }
}

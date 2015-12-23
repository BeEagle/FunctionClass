package cchao.org.mylibrary.photo.localimage.util;

/**
 * Created by chenchao on 15/12/23.
 */
public class ImageItem {

    private String id;
    private String orientation;
    private String imagePath;
    private String thumnbailPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getThumnbailPath() {
        return thumnbailPath;
    }

    public void setThumnbailPath(String thumnbailPath) {
        this.thumnbailPath = thumnbailPath;
    }
}

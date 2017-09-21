package com.shahin.demo.Data;

import android.graphics.Bitmap;

/**
 * Created by Shahin on 9/18/2017.
 */
public class DashboardDataModel {

    private String mImageTitle;
    private String mImageDescription;
    private Bitmap mImage;

    public String getImageTitle() {
        return mImageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.mImageTitle = imageTitle;
    }

    public String getImageDescription() {
        return mImageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.mImageDescription = imageDescription;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        this.mImage = image;
    }
}





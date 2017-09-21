package com.shahin.demo.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import com.shahin.demo.Data.Constants;
import com.shahin.demo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Shahin on 9/18/2017.
 */

public class Utils {
    public static void saveImageToExternalStorage(Context context, Bitmap image) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String timeStamp = dateFormat.format(new Date());
        String imageFileName = "picture_" + timeStamp + ".jpg";

        File direct = new File(Utils.getSaveRootDir(context).getAbsolutePath() + Constants.IMAGE_DIRECTORY);

        if (!direct.exists()) {
            direct.mkdirs();
        }

        File file = new File(direct, imageFileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(context, context.getResources().getString(R.string.image_saved), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getSaveRootDir(Context context) {
        File saveDir = context.getExternalFilesDir(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            File dirs[] = context.getExternalFilesDirs(null);
            for (File dir : dirs) {
                // dir can be null if the device contains an external SD card slot but no SD card is present.
                if (dir != null && Environment.isExternalStorageRemovable(dir)) {
                    saveDir = dir;
                    break;
                }
            }
        }
        return saveDir;
    }
}

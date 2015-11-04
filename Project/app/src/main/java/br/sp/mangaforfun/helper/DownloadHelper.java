package br.sp.mangaforfun.helper;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.sp.mangaforfun.R;
import br.sp.mangaforfun.utils.RoundedTransformation;

public class DownloadHelper {

    private Activity activity;
    private TextView title;
    private TextView description;
    private TextView chapters;
    private ImageView photo;
    private ScrollView scrollView;

    public DownloadHelper(Activity activity){

        this.activity = activity;

        this.title = (TextView) activity.findViewById(R.id.title_download_activity);
        this.description = (TextView) activity.findViewById(R.id.description_download_activity);
        this.chapters = (TextView) activity.findViewById(R.id.chapters_download_activity);
        this.photo = (ImageView) activity.findViewById(R.id.photo_download_activity);
        this.scrollView = (ScrollView) activity.findViewById(R.id.scrollview_download_activity);
    }

    public void setTitle(String title){

        this.title.setText(title);
    }

    public void setChapters(String chapters){

        this.chapters.setText(String.valueOf(chapters.split(" , ").length));
    }

    public void setPhoto(String urlImage){

        if(urlImage.length() > 0) {

            try {

                Picasso.with(activity).load(urlImage).transform(new RoundedTransformation(12, 0)).into(photo);
            } catch (Exception e) {

                Log.i("Set Photo", "Exception", e);
            }
        }
    }

    public void setDescription(String description){

        this.description.setText(description);
    }

    public void setScrollView(Boolean enable){

        if(enable)
            this.scrollView.setVisibility(View.VISIBLE);
        else
            this.scrollView.setVisibility(View.INVISIBLE);
    }
}

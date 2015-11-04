package br.sp.mangaforfun.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import br.sp.mangaforfun.utils.TouchImageView;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> chapters;
    private int mTotalPages;

    public ViewPagerAdapter(Context context, ArrayList<String> chapters, int mTotalPages) {

        super();

        this.mContext = context;
        this.chapters = chapters;
        this.mTotalPages = mTotalPages;
    }

    @Override
    public int getCount() {

        return mTotalPages;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Bitmap pageBitmap = BitmapFactory.decodeFile(chapters.get(position));

        TouchImageView touchImageView = new TouchImageView(mContext);

        touchImageView.setImageBitmap(pageBitmap);

        container.addView(touchImageView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        return touchImageView;
    }

    @Override
    public int getItemPosition(Object object) {

        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((TouchImageView) object);
    }
}

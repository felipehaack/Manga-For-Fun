package br.sp.mangaforfun.extended;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;

public class ExpandableListViewMangas extends ExpandableListView {

    private Button fab;

    public ExpandableListViewMangas(Context context, AttributeSet attrs) {

        super(context, attrs);

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

                if (i == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {

                    fab.animate().scaleXBy(0.5f).scaleYBy(0.5f).scaleX(0).scaleY(0).setDuration(300);
                }

                if (i == OnScrollListener.SCROLL_STATE_IDLE) {

                    fab.animate().scaleXBy(0.5f).scaleYBy(0.5f).scaleX(1).scaleY(1).setDuration(300);
                }
            }
        });
    }

    public void setButton(Button fab) {

        this.fab = fab;
    }
}

package br.sp.mangaforfun.extended;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import br.sp.mangaforfun.helper.MainHelper;

public class ExpandableListViewMangas extends ExpandableListView {

    private MainHelper mainHelper;

    public ExpandableListViewMangas(Context context, AttributeSet attrs) {

        super(context, attrs);

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

                if (i == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {

                    mainHelper.scaleInAddManga();
                }

                if (i == OnScrollListener.SCROLL_STATE_IDLE) {

                    mainHelper.scaleOutAddManga();
                }
            }
        });
    }

    public void setMainHelper(MainHelper mainHelper) {

        this.mainHelper = mainHelper;
    }
}

package br.sp.mangaforfun.gestures;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import br.sp.mangaforfun.helper.MainHelper;

public class SlideLayerExpandableListView {

    private Activity activity;
    private ExpandableListView expandableListView;
    private MainHelper mainHelper;

    private float startX = 0f;
    private float startY = 0f;

    private float moveX = 0f;
    private float moveY = 0f;

    private float acumX = 0f;

    float limitX = 13f;
    float limitY = 7f;

    private int width;

    private Boolean direction = true; //True - Right | False - Left
    public Boolean translate = false;
    private Boolean translateStart = false;
    private Boolean lockAll = false;

    private Runnable endAnimation = new Runnable() {
        @Override
        public void run() {

            lockAll = false;
            expandableListView.setTranslationX(acumX);
        }
    };

    public SlideLayerExpandableListView(ExpandableListView expandableListView, Activity activity, MainHelper mainHelper) {

        this.expandableListView = expandableListView;
        this.activity = activity;
        this.mainHelper = mainHelper;

        setWidth();
    }

    public void setWidth() {

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        this.width = size.x;
    }

    public void motionEventEnd() {

        if (translate && acumX > 0 && acumX < width) {

            lockAll = true;

            if (direction) {

                expandableListView.animate().x(0).setDuration(500).withEndAction(endAnimation);

                acumX = 0f;
            } else {

                expandableListView.animate().x(this.width).setDuration(500).withEndAction(endAnimation);

                acumX = (float) width;
            }

            mainHelper.scaleOutAddManga();
        }
    }

    public void motionEventMove() {

        if (translateStart) {

            float distX = startX > moveX ? startX - moveX : moveX - startX;
            float distY = startY > moveY ? startY - moveY : moveY - startY;

            if (distX > limitX) {

                translateStart = false;
                translate = true;
            }

            if (distY > limitY && translateStart) {

                translateStart = false;
            }
        }

        if (translate) {

            float aux = acumX + (moveX - startX);

            if (aux > 0 && aux < width) {

                acumX = aux;

                expandableListView.setTranslationX(acumX);
            }

            if (startX > moveX)
                direction = true;
            else
                direction = false;

            startX = moveX;
        }
    }

    public void motionEvent(MotionEvent event) {

        if (!lockAll) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN: {

                    startX = event.getX(0);
                    startY = event.getY(0);

                    translate = false;
                    translateStart = true;

                    break;
                }

                case MotionEvent.ACTION_MOVE: {

                    moveX = event.getX(0);
                    moveY = event.getY(0);

                    motionEventMove();

                    break;
                }

                case MotionEvent.ACTION_UP: {

                    motionEventEnd();

                    break;
                }

                case MotionEvent.ACTION_CANCEL: {

                    motionEventEnd();

                    break;
                }
            }
        }
    }
}

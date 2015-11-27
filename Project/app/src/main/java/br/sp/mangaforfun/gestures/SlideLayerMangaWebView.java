package br.sp.mangaforfun.gestures;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class SlideLayerMangaWebView {

    private LinearLayout layoutMangaWebView;
    private Activity activity;

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

    public SlideLayerMangaWebView(LinearLayout layoutMangaWebView, Activity activity) {

        this.layoutMangaWebView = layoutMangaWebView;
        this.activity = activity;

        setWidth();
    }

    public void setWidth() {

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        this.width = size.x;
    }

    public void motionEventEnd() {

        if (direction) {

            layoutMangaWebView.animate().x(0).setDuration(500);
            acumX = 0f;
        } else {

            layoutMangaWebView.animate().x(this.width).setDuration(500);
            acumX = (float) width;
        }

        layoutMangaWebView.setTranslationX(acumX);
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

                layoutMangaWebView.setTranslationX(acumX);
            }

            if (startX > moveX)
                direction = true;
            else
                direction = false;

            startX = moveX;
        }
    }

    public void motionEvent(MotionEvent event) {

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

package br.sp.mangaforfun.helper;

import android.widget.Button;

public class MainHelper {

    private Button addManga;

    public MainHelper(Button addManga) {

        this.addManga = addManga;
    }

    public void scaleOutAddManga() {

        addManga.animate().scaleXBy(0.5f).scaleYBy(0.5f).scaleX(1).scaleY(1).setDuration(300);
    }

    public void scaleInAddManga() {

        addManga.animate().scaleXBy(0.5f).scaleYBy(0.5f).scaleX(0).scaleY(0).setDuration(300);
    }
}

package br.sp.mangaforfun.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import br.sp.mangaforfun.R;
import br.sp.mangaforfun.adapters.ExpandableListAdapterMangas;
import br.sp.mangaforfun.adapters.ExpandableListAdapterServer;
import br.sp.mangaforfun.extended.ExpandableListViewMangas;
import br.sp.mangaforfun.gestures.SlideLayerExpandableListView;

public class MainActivity extends AppCompatActivity {

    private Button addManga;
    private ExpandableListView expandableListViewServer;
    private ExpandableListViewMangas expandableListViewManga;
    private SlideLayerExpandableListView slideLayerMangaWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        createButton();
        createExpandableListViewManga();
        createExpandableListViewServer();
        setPerspective();
    }

    public void setPerspective(){
    }

    public void createExpandableListViewManga(){

        expandableListViewManga = (ExpandableListViewMangas) findViewById(R.id.main_activity_expandable_listview_mangas);
        expandableListViewManga.setButton(addManga);

        ArrayList<Object> parentItens = new ArrayList<>();
        ArrayList<String> parent = new ArrayList<>();

        parent.add("dragon ball z");
        parent.add("200");
        parentItens.add(parent);

        parent = new ArrayList<>();

        parent.add("nisekoi");
        parent.add("123");
        parentItens.add(parent);

        parent = new ArrayList<>();

        parent.add("one punch man");
        parent.add("55");
        parentItens.add(parent);

        ArrayList<Object> childItens = new ArrayList<>();
        ArrayList<Object> childContainer = new ArrayList<>();
        ArrayList<String> childInformations = new ArrayList<>();

        childInformations.add("1");
        childInformations.add("35");
        childInformations.add("50");
        childContainer.add(childInformations);
        childItens.add(childContainer);

        childContainer = new ArrayList<>();

        for(int i = 0; i < 50; i++) {

            childInformations.add(String.valueOf(i));
            childInformations.add("112");
            childInformations.add("112");
            childContainer.add(childInformations);
            childInformations = new ArrayList<>();
        }

        childItens.add(childContainer);

        childInformations = new ArrayList<>();
        childContainer = new ArrayList<>();
        childInformations.add("3");
        childInformations.add("3");
        childInformations.add("20");
        childContainer.add(childInformations);
        childItens.add(childContainer);

        ExpandableListAdapterMangas adapter = new ExpandableListAdapterMangas(parentItens, childItens);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this, expandableListViewManga);

        expandableListViewManga.setAdapter(adapter);

        slideLayerMangaWebView = new SlideLayerExpandableListView(expandableListViewManga, this);
    }

    public void createExpandableListViewServer(){

        expandableListViewServer = (ExpandableListView) findViewById(R.id.main_activity_expandable_listview);

        ArrayList<String> parentItens = new ArrayList<>();
        ArrayList<Object> childItens = new ArrayList<>();

        parentItens.add("batoto");
        parentItens.add("mangareader");

        ArrayList<String> child = new ArrayList<>();

        child.add("english");
        child.add("portuguese");

        childItens.add(child);

        child = new ArrayList<>();

        child.add("english");
        child.add("portuguese");

        childItens.add(child);

        ExpandableListAdapterServer adapter = new ExpandableListAdapterServer(parentItens, childItens);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this, expandableListViewServer);

        expandableListViewServer.setAdapter(adapter);

        expandableListViewServer.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                return true;
            }
        });
    }

    public void createButton(){

        addManga = (Button) findViewById(R.id.add_manga_button);

        addManga.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN: {

                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_manga_button);

                        addManga.startAnimation(animation);

                        break;
                    }

                    case MotionEvent.ACTION_UP: {

                        addManga.clearAnimation();

                        Intent i = new Intent(MainActivity.this, DownloadActivity.class);
                        startActivity(i);

                        break;
                    }
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        slideLayerMangaWebView.motionEvent(ev);

        if (slideLayerMangaWebView.translate)
            return true;

        return super.dispatchTouchEvent(ev);
    }
}

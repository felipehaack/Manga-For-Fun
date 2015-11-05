package br.sp.mangaforfun.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.sp.mangaforfun.R;
import br.sp.mangaforfun.adapters.ExpandableListAdapter;

public class MainActivity extends AppCompatActivity {

    private Button addManga;

    private List<String> listDataHeader = new ArrayList<String>();
    private HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView) findViewById(R.id.main_expandable_list_view);

        populateLists();

        expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        expandableListView.setAdapter(expandableListAdapter);

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

    public void populateLists() {

        for (int i = 0; i < 5; ++i) {

            listDataHeader.add("Manga " + String.valueOf(i));

            List<String> chapterList = new ArrayList<>();

            for (int j = 0; j < 40; ++j) {

                chapterList.add("Chapter " + String.valueOf(j));
            }

            listDataChild.put(listDataHeader.get(listDataHeader.size() - 1), chapterList);
        }
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
}

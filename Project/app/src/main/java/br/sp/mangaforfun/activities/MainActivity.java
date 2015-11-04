package br.sp.mangaforfun.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.sp.mangaforfun.R;
import br.sp.mangaforfun.adapters.ExpandableListAdapter;

public class MainActivity extends AppCompatActivity {

    private Button addManga;

    private String rootPath;

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

        /*ListView list_view = (ListView) findViewById(R.id.main_list_view);

        File rootFile = new File(rootPath);

        File mangasFolder[] = rootFile.listFiles();


        final ArrayList<String> mangas = new ArrayList<>();

        for(File manga : mangasFolder)
            mangas.add(String.valueOf(manga.getName()));


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mangas);

        list_view.setAdapter(adapter);


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

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);

                intent.putExtra("mangaPath", rootPath + "/" +  mangas.get(i));

                startActivity(intent);
            }
        });*/
    }

    public void populateLists(){

        rootPath = System.getenv("SECONDARY_STORAGE") + "/Mangas";

        File rootFile = new File(rootPath);

        File rootFolder[] = rootFile.listFiles();

        for(File manga : rootFolder){

            listDataHeader.add(manga.getName());

            File chapters[] = manga.listFiles();
            List<String> chapterList = new ArrayList<>();

            for(File chapter : chapters){

                chapterList.add(chapter.getAbsolutePath());
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

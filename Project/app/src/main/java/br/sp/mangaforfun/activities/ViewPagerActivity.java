package br.sp.mangaforfun.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.sp.mangaforfun.R;
import br.sp.mangaforfun.adapters.ViewPagerAdapter;

public class ViewPagerActivity extends AppCompatActivity {

    private String mangaPath;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_view_pager);

        mangaPath = getIntent().getStringExtra("mangaPath");

        ArrayList<String> chapters = new ArrayList<>();

        File mangaFile = new File(mangaPath);
        File mangaFileList[] = mangaFile.listFiles();

        for(File f : mangaFileList)
            chapters.add(f.getAbsolutePath());

        viewPagerAdapter = new ViewPagerAdapter(this, chapters, chapters.size());

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPager.setAdapter(viewPagerAdapter);
    }
}

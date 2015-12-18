package br.sp.mangaforfun.support;

import java.util.ArrayList;

public class AllManga {

    private ArrayList<String> servers;
    private ArrayList<String> languages;
    private ArrayList<String> searchMangas;
    private ArrayList<String> searchChapters;

    public AllManga(){

        servers.add("batoto");
        servers.add("mangareader");

        languages.add("english");

        searchMangas.add("");
        searchChapters.add("");
    }
}

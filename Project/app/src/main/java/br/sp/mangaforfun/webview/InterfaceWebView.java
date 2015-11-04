package br.sp.mangaforfun.webview;

import android.content.Context;

public class InterfaceWebView {

    private Context context;

    InterfaceWebView(Context context){

        this.context = context;
    }

    public void setManga(String isManga, String title, String photo, String description, String chapters, String chaptersUrl){

        if(title.length() > 0) {

            /*if(chapters.length() > 0) {

                downloadActivity.manga.setTitle(title);
                downloadActivity.manga.setUrl(String.valueOf(downloadActivity.editTextUrl.getText()));
                downloadActivity.manga.setPhoto(photo);
                downloadActivity.manga.setDescription(description);
                downloadActivity.manga.setChapters(chapters);
                downloadActivity.manga.setChaptersUrl(chaptersUrl);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        DownloadActivity downloadActivity = DownloadActivity.this;

                        downloadActivity.downloadHelper.setManga(downloadActivity.manga);

                        Toast.makeText(getApplicationContext(), "Manga encontrado com sucesso!", Toast.LENGTH_SHORT).show();

                        DownloadActivity.this.progressDialog.dismiss();
                    }
                });
            }else {

                Toast.makeText(getApplicationContext(), "O Manga nao tem capitulos!", Toast.LENGTH_SHORT).show();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        DownloadActivity.this.progressDialog.dismiss();
                    }
                });
            }*/
        }else {

            /*Toast.makeText(getApplicationContext(), "Nenhum manga encontrado!", Toast.LENGTH_SHORT).show();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    DownloadActivity.this.progressDialog.dismiss();
                }
            });*/
        }
    }
}

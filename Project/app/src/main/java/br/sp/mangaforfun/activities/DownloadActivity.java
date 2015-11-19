package br.sp.mangaforfun.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.sp.mangaforfun.R;
import br.sp.mangaforfun.helper.DownloadHelper;
import br.sp.mangaforfun.webview.WebViewCustom;

public class DownloadActivity extends ActionBarActivity{

    /* Used to get the manga from host and display alerts */
    private WebViewCustom webView;
    private ProgressDialog progressDialog;

    /* Used to set datas to this view */
    private DownloadHelper downloadHelper;

    /* For Custom Dialog */
    private EditText editTextUrl;
    //private int radioOptionId = R.id.download_dialog_radio_mangareader;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_download);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        builder = createAlertDialog();

        webView = new WebViewCustom(this);

        downloadHelper = new DownloadHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.download_menu, menu);

        MenuItem menuItemSearch = menu.findItem(R.id.search);
        menuItemSearch.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(alertDialog == null)
                    alertDialog = builder.show();
                else
                    alertDialog.show();

                return false;
            }
        });

        MenuItem menuItemAdd = menu.findItem(R.id.add);
        menuItemAdd.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });

        return true;
    }

    public AlertDialog.Builder createAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.activity_download_dialog, null);

        EditText editText = (EditText) view.findViewById(R.id.download_dialog_url);
        editText.setSelection(editText.length());

        Spinner spinnerHosts  = (Spinner) view.findViewById(R.id.download_dialog_spinner_hosts);

        spinnerHosts.setPrompt("Select a host");

        String[] hosts = new String[3];
        hosts[0] = "Select a host";
        hosts[1] = "Mangareader";
        hosts[2] = "Batoto";

        ArrayAdapter<String> dataAdapterHosts = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_download_dialog_textview, hosts);

        dataAdapterHosts.setDropDownViewResource(R.layout.activity_download_dialog_dropdown);

        spinnerHosts.setAdapter(dataAdapterHosts);

        final Spinner spinnerLanguages = (Spinner) view.findViewById(R.id.download_dialog_spinner_languages);

        String[] languages = new String[5];
        languages[0] = "Select a language";
        languages[1] = "ENG";
        languages[2] = "PT-br";
        languages[3] = "SPAN";
        languages[4] = "JAP";

        final ArrayAdapter<String> dataAdapterLanguages = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_download_dialog_textview, languages);

        dataAdapterLanguages.setDropDownViewResource(R.layout.activity_download_dialog_dropdown);

        spinnerLanguages.setAdapter(dataAdapterLanguages);

        editTextUrl = editText;

        Button cancel = (Button) view.findViewById(R.id.download_dialog_button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.cancel();
            }
        });

        Button search = (Button) view.findViewById(R.id.download_dialog_button_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String url = String.valueOf(editTextUrl.getText());
                String url = "http://www.mangareader.net/fuuka";

                String match = "^((http|https)?):\\/{2,2}www\\.mangareader\\.net\\/.*";

                if (url.matches(match)) {

                    alertDialog.cancel();

                    webView.loadUrl(url);

                    progressDialog = new ProgressDialog(DownloadActivity.this, AlertDialog.THEME_HOLO_LIGHT);

                    progressDialog.setCancelable(true);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Buscando manga...");
                    progressDialog.setTitle(null);

                    progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {

                            webView.loadUrl("about:blank");
                        }
                    });

                    progressDialog.show();
                }else
                    Toast.makeText(getApplicationContext(), "URL incorreta: " + url, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view);

        return builder;
    }
}
package br.sp.mangaforfun.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.sp.mangaforfun.R;

public class ListViewAdapterServer extends ArrayAdapter<String>{

    public ListViewAdapterServer(Context context, ArrayList<String> servers) {

        super(context, 0, servers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView;
        Typeface typeface;
        String server = getItem(position);

        if(server.contains("- server")){

            server = server.replace("- server", "");

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_servers_group, parent, false);
            }

            textView = (TextView) convertView.findViewById(R.id.textview_servers_server);
            typeface = Typeface.createFromAsset(getContext().getAssets(), "default/fonts/HelveticaNeue-Bold.ttf");
        }else{

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_servers_child, parent, false);
            }

            textView = (TextView) convertView.findViewById(R.id.textview_servers_language);
            typeface = Typeface.createFromAsset(getContext().getAssets(), "default/fonts/HelveticaNeue.ttf");

            if(position % 2 == 0)
                textView.setBackgroundColor(Color.parseColor("#929292"));
            else
                textView.setBackgroundColor(Color.parseColor("#AFAFAF"));
        }

        textView.setTypeface(typeface);
        textView.setText(server);

        return convertView;
    }
}

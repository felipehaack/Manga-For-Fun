package br.sp.mangaforfun.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.sp.mangaforfun.R;

public class ExpandableListAdapterServer extends BaseExpandableListAdapter {

    private Activity activity;
    private ArrayList<Object> childtems;
    private LayoutInflater inflater;
    private ArrayList<String> parentItems;
    private ExpandableListView expandableListView;

    public ExpandableListAdapterServer(ArrayList<String> parents, ArrayList<Object> childern) {

        this.parentItems = parents;
        this.childtems = childern;
    }

    public void setInflater(LayoutInflater inflater, Activity activity, ExpandableListView expandableListView) {

        this.inflater = inflater;
        this.activity = activity;
        this.expandableListView = expandableListView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String child = ((ArrayList<String>) childtems.get(groupPosition)).get(childPosition);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.activity_main_servers_child, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.textview_servers_language);
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "default/fonts/HelveticaNeue.ttf");

        textView.setTypeface(typeface);
        textView.setText(child);

        if(childPosition % 2 == 0)
            textView.setBackgroundColor(Color.parseColor("#929292"));
        else
            textView.setBackgroundColor(Color.parseColor("#AFAFAF"));

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.activity_main_servers_group, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.textview_servers_server);
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "default/fonts/HelveticaNeue-Bold.ttf");

        textView.setTypeface(typeface);
        textView.setText(parentItems.get(groupPosition));

        expandableListView.expandGroup(groupPosition);

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return ((ArrayList<String>) childtems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return null;
    }

    @Override
    public int getGroupCount() {

        return parentItems.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {

        return 0;
    }

    @Override
    public boolean hasStableIds() {

        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return false;
    }
}
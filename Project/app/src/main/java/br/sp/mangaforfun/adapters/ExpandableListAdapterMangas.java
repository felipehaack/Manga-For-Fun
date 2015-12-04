package br.sp.mangaforfun.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.sp.mangaforfun.R;

public class ExpandableListAdapterMangas extends BaseExpandableListAdapter {

    private Activity activity;
    private ArrayList<Object> childtems;
    private LayoutInflater inflater;
    private ArrayList<Object> parentItems;
    private ExpandableListView expandableListView;

    private Typeface typeFaceLight;
    private Typeface typeFaceBold;

    private int color1;
    private int color2;

    private Drawable drawableTop;
    private Drawable drawableMiddle;
    private Drawable drawableBottom;

    public ExpandableListAdapterMangas(ArrayList<Object> parents, ArrayList<Object> childern) {

        this.parentItems = parents;
        this.childtems = childern;
    }

    public void setInflater(LayoutInflater inflater, Activity activity, ExpandableListView expandableListView) {

        this.inflater = inflater;
        this.activity = activity;
        this.expandableListView = expandableListView;

        this.typeFaceLight = Typeface.createFromAsset(activity.getAssets(), "default/fonts/HelveticaNeue.ttf");
        this.typeFaceBold = Typeface.createFromAsset(activity.getAssets(), "default/fonts/HelveticaNeue-Bold.ttf");

        this.color1 = Color.parseColor("#AFAFAF");
        this.color2 = Color.parseColor("#929292");

        this.drawableTop = activity.getResources().getDrawable(R.drawable.activity_main_mangas_radius_top);
        this.drawableMiddle = activity.getResources().getDrawable(R.drawable.activity_main_mangas_radius_middle);
        this.drawableBottom = activity.getResources().getDrawable(R.drawable.activity_main_mangas_radius_bottom);
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ArrayList child = (ArrayList) childtems.get(groupPosition);
        ArrayList<String> childInfo = (ArrayList<String>) child.get(childPosition);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.activity_main_mangas_child, null);
        }

        if(childPosition % 2 == 0)
            convertView.findViewById(R.id.linearlayout_mangas_child).setBackgroundColor(color1);
        else
            convertView.findViewById(R.id.linearlayout_mangas_child).setBackgroundColor(color2);

        TextView textView = (TextView) convertView.findViewById(R.id.textview_mangas_cap);
        textView.setTypeface(typeFaceLight);
        textView.setText(childInfo.get(0));

        TextView textView2 = (TextView) convertView.findViewById(R.id.textview_mangas_start);
        textView2.setTypeface(typeFaceLight);
        textView2.setText(childInfo.get(1));

        TextView textView3 = (TextView) convertView.findViewById(R.id.textview_mangas_end);
        textView3.setTypeface(typeFaceLight);
        textView3.setText(childInfo.get(2));

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.activity_main_mangas_group, null);
        }

        ArrayList<String> parentInformation = (ArrayList<String>) parentItems.get(groupPosition);

        TextView textView = (TextView) convertView.findViewById(R.id.textview_mangas_name);
        textView.setTypeface(typeFaceBold);
        textView.setText(parentInformation.get(0));

        textView = (TextView) convertView.findViewById(R.id.textview_mangas_count);
        textView.setTypeface(typeFaceLight);
        textView.setText(parentInformation.get(1));

        if (groupPosition == 0)
            convertView.findViewById(R.id.linearlayout_mangas_corners).setBackground(drawableBottom);

        if (groupPosition > 0 && groupPosition < parentItems.size() - 1)
            convertView.findViewById(R.id.linearlayout_mangas_corners).setBackground(drawableMiddle);

        if (groupPosition == parentItems.size() - 1)
            convertView.findViewById(R.id.linearlayout_mangas_corners).setBackground(drawableTop);

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

        return ((ArrayList) childtems.get(groupPosition)).size();
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
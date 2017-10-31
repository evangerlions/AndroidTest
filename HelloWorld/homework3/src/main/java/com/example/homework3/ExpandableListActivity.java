package com.example.homework3;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListActivity extends android.app.ExpandableListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            int[] logs = new int[]{
                    R.drawable.warrior,
                    R.drawable.mage,
                    R.drawable.priest
            };

            {
                Log.d("ExpendableListActivity", Integer.toString(logs[0]));
                Log.d("ExpendableListActivity", Integer.toString(logs[1]));
                Log.d("ExpendableListActivity", Integer.toString(logs[2]));
            }

            private String warrior = getResources().getString(R.string.warrior_name),
                    mage = getResources().getString(R.string.mage_name),
                    priest =getResources().getString(R.string.priest_name);
            private String[] heroTypes = new String[]
                    {
                            warrior, mage, priest
                    };
            private String[][] heros = new String[][]
                    {
                            {"Garen", "XinZhao", "Nasus"},
                            {"Ryze", "Veigar", "LeBlanc"},
                            {"Taric", "Soraka", "Janna"}
                    };

            @Override
            public int getGroupCount() {
                return heroTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return heros[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return groupPosition;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return heros[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) ExpandableListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.expandable_layout_parent, null);
                }
                convertView.setTag(R.layout.expandable_layout_parent, groupPosition);
                convertView.setTag(R.layout.expandable_layout_children, -1);

                TextView text = (TextView) convertView.findViewById(R.id.expandable_list_parent);
                text.setText(heroTypes[groupPosition]);

                ImageView image = (ImageView) convertView.findViewById(R.id.expandable_list_parent_image);
                image.setImageResource(logs[groupPosition]);
                return convertView;
            }

            @Override
            public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) ExpandableListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.expandable_layout_children, null);
                }
                convertView.setTag(R.layout.expandable_layout_parent, groupPosition);
                convertView.setTag(R.layout.expandable_layout_children, childPosition);
                TextView text = (TextView) convertView.findViewById(R.id.expandable_list_children);
                text.setText(heros[groupPosition][childPosition]);
                text.setTag(heros[groupPosition][childPosition]);
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       String string = heros [groupPosition][childPosition];
                        string = "您点击了"+string;
                        Toast.makeText(ExpandableListActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                });
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        setListAdapter(adapter);
    }
}

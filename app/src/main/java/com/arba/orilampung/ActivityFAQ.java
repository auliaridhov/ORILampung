package com.arba.orilampung;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityFAQ extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private ExpandableListViewAdapter expandableListViewAdapter;

    private List<String> listDataGroup;

    private HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faq);

        // initializing the views
        initViews();

        // initializing the listeners
//        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();

    }


    /**
     * method to initialize the views
     */
    private void initViews() {

        expandableListView = findViewById(R.id.expandableListView);

    }

    /**
     * method to initialize the listeners
     */
//    private void initListeners() {
//
//        // ExpandableListView on child click listener
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataGroup.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataGroup.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
//                return false;
//            }
//        });
//
//        // ExpandableListView Group expanded listener
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // ExpandableListView Group collapsed listener
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {

        // initializing the list of groups
        listDataGroup = new ArrayList<>();

        // initializing the list of child
        listDataChild = new HashMap<>();

        // initializing the adapter object
        expandableListViewAdapter = new ExpandableListViewAdapter(this, listDataGroup, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(expandableListViewAdapter);

    }

    /*
     * Preparing the list data
     *
     * Dummy Items
     */
    private void initListData() {


        // Adding group data
        listDataGroup.add(getString(R.string.text_1));
        listDataGroup.add(getString(R.string.text_2));
        listDataGroup.add(getString(R.string.text_3));
        listDataGroup.add(getString(R.string.text_4));
        listDataGroup.add(getString(R.string.text_6));
        listDataGroup.add(getString(R.string.text_7));
        listDataGroup.add(getString(R.string.text_8));

        // array of strings
        String[] array;

        // list of alcohol
        List<String> text1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_1);
        for (String item : array) {
            text1.add(item);
        }

        // list of coffee
        List<String> text2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_2);
        for (String item : array) {
            text2.add(item);
        }

        // list of pasta
        List<String> text3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_3);
        for (String item : array) {
            text3.add(item);
        }

        // list of cold drinks
        List<String> text4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_4);
        for (String item : array) {
            text4.add(item);
        }

        List<String> text6 = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_6);
        for (String item : array) {
            text6.add(item);
        }
        List<String> text7 = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_7);
        for (String item : array) {
            text7.add(item);
        }
        List<String> text8 = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_8);
        for (String item : array) {
            text8.add(item);
        }

        // Adding child data
        listDataChild.put(listDataGroup.get(0), text1);
        listDataChild.put(listDataGroup.get(1), text2);
        listDataChild.put(listDataGroup.get(2), text3);
        listDataChild.put(listDataGroup.get(3), text4);
        listDataChild.put(listDataGroup.get(4), text6);
        listDataChild.put(listDataGroup.get(5), text7);
        listDataChild.put(listDataGroup.get(6), text8);

        // notify the adapter
        expandableListViewAdapter.notifyDataSetChanged();
    }

}
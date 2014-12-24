package com.example.buhab.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME = "test_pref";
    public static final String KEY_USER_VIEW_DRAWER = "user_view_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserViewDrawer;
    private boolean mFromSavedInstanceState;
    private View drawerView;

    private DataInfoAdapter dataInfoAdapter;
    private RecyclerView recyclerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewDrawer = Boolean.valueOf(readFromoPreference(getActivity(), KEY_USER_VIEW_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.containerDrawerList);
        dataInfoAdapter = new DataInfoAdapter(getActivity(), getData());
        recyclerView.setAdapter(dataInfoAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(dataInfoAdapter.getItemCount(), 0));
        return layout;
    }

    public static List<DataInfo> getData() {
        List<DataInfo> data = new ArrayList<DataInfo>();

        int icons[] = {R.drawable.ic_next, R.drawable.ic_next, R.drawable.ic_next, R.drawable.ic_next};
        String titles[] = {"title 1", "title 2", "title 3", "title 4"};

        for (int i = 0; i < titles.length && i < icons.length; i++) {
            DataInfo currentData = new DataInfo();
            currentData.iconId = icons[i];
            currentData.title = titles[i];
            data.add(currentData);
        }

        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        drawerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserViewDrawer) {
                    mUserViewDrawer = true;
                    saveToPreference(getActivity(), KEY_USER_VIEW_DRAWER, String.valueOf(mUserViewDrawer));
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //super.onDrawerSlide(drawerView, slideOffset);
                //Log.d("BOBO", "offset: " + slideOffset);
                if (slideOffset < 0.5) {
                    toolbar.setAlpha(1 - slideOffset);
                }

            }
        };

        if (!mUserViewDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(drawerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreference(Context context, String preferneceName, String preferenceValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferneceName, preferenceValue);
        editor.apply();
    }

    public static String readFromoPreference(Context context, String preferneceName, String defaultValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(preferneceName, defaultValue);
    }
}

package com.fb.tangyc.fbtools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.tangyc.fbtools.BaseActivity;
import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.service.FBService;
import com.fb.tangyc.fbtools.utils.AndroidUtils;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SwitchCompat mScFb = (SwitchCompat) findViewById(R.id.sc_fb);
        mScFb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(MainActivity.this, FBService.class);

                intent.putExtra("isShowFB", isChecked);
                startService(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FbToolsActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        LinearLayout mLLTheme = (LinearLayout) findViewById(R.id.ll_theme);
        mLLTheme.setOnClickListener(this);
        LinearLayout mLLSetting = (LinearLayout) findViewById(R.id.ll_setting);
        mLLSetting.setOnClickListener(this);
        LinearLayout mLLToolsSetting = (LinearLayout) findViewById(R.id.ll_tools_setting);
        mLLToolsSetting.setOnClickListener(this);
      //  ((TextView)navigationView.getHeaderView(1)).setText(getResources().getString(R.string.app_name));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {
            //分享功能
            AndroidUtils.shareMsg(this, "分享", "分享", "那就去下载吧", null);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_theme: {
                Intent intent = new Intent();
                intent.setClass(this, ThemeActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.ll_setting: {
                Intent intent = new Intent();
                intent.setClass(this, FbSettingActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.ll_tools_setting: {
                Intent intent = new Intent();
                intent.setClass(this, FbToolsActivity.class);
                startActivity(intent);
            }
            break;

        }
    }
}

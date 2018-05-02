package net.lxj.bilibili;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.lxj.bilibili.view.common.BaseActivity;
import net.lxj.bilibili.widget.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navi)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public void initViews(Bundle savedInstanceState) {

    }
    @Override
    public void initToolBar() {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.game:

                break;
            case R.id.share:

                break;
            case R.id.communicate:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * item 侧滑点击和底部导航浪点击
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (item.getItemId()) {
            //侧滑点击事件
            case R.id.nav_camera:

                return true;
            case R.id.nav_gallery:

                return true;
            case R.id.nav_slideshow:

                return true;
            case R.id.nav_manage:

                return true;
            case R.id.nav_share:

                return true;
            case R.id.nav_send:

                return true;
                //底部导航栏点击事件
            case R.id.bottom_home://首页

                return true;
            case R.id.bottom_message://分区

                return true;

            case R.id.bottom_ada://动态

                return true;

            case R.id.bottom_mine://会员购

                return true;

        }
        return false;
    }


}

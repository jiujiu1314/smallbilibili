package net.lxj.bilibili;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.lxj.bilibili.utils.ConstantUtil;
import net.lxj.bilibili.utils.PreferenceUtil;
import net.lxj.bilibili.utils.ToastUtil;
import net.lxj.bilibili.view.common.BaseActivity;
import net.lxj.bilibili.view.home.HomePageFragment;
import net.lxj.bilibili.widget.BottomNavigationViewHelper;
import net.lxj.bilibili.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_navi)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.drawer_img)
    ImageView drawerImg;
    @BindView(R.id.toolbar_user_avatar)
    CircleImageView toolbarUserAvatar;
    @BindView(R.id.toolbar_search)
    TextView toolbarSearch;
    @BindView(R.id.navigation_layout)
    LinearLayout navigationLayout;
    @BindView(R.id.container)
    FrameLayout container;
    private HomePageFragment mHomePageFragment;
    private long exitTime;
    private Fragment[] fragments;
    private int currentTabIndex;
    private int index;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initFragments();
        finishTask();
    }

    @Override
    public void finishTask() {
        super.finishTask();
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

    @Override
    public void initToolBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);
        toolbarSearch.setOnClickListener(this);
        navigationLayout.setOnClickListener(view ->{
            toggleDrawer();
        });
    }

    /**
     * 初始化Fragments
     */
    private void initFragments() {
        mHomePageFragment = HomePageFragment.newInstance();
//        IFavoritesFragment mFavoritesFragment = IFavoritesFragment.newInstance();
//        HistoryFragment mHistoryFragment = HistoryFragment.newInstance();
//        AttentionPeopleFragment mAttentionPeopleFragment = AttentionPeopleFragment.newInstance();
//        ConsumeHistoryFragment mConsumeHistoryFragment = ConsumeHistoryFragment.newInstance();
//        SettingFragment mSettingFragment = SettingFragment.newInstance();
        fragments = new Fragment[]{
                mHomePageFragment
//                mFavoritesFragment,
//                mHistoryFragment,
//                mAttentionPeopleFragment,
//                mConsumeHistoryFragment,
//                mSettingFragment
        };
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mHomePageFragment)
                .show(mHomePageFragment).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mHomePageFragment != null) {
                    exitApp();
            } else {
                exitApp();
            }
            super.onBackPressed();
        }
    }


    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 双击退出App
     */
    private void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.ShortToast("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            PreferenceUtil.remove(ConstantUtil.SWITCH_MODE_KEY);
            finish();
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

        switch (id) {
            case R.id.game:
                ToastUtil.ShortToast("游戏");
                break;
            case R.id.share:
                ToastUtil.ShortToast("下载");
                break;
            case R.id.communicate:
                ToastUtil.ShortToast("消息");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Fragment切换
     */
    private void switchFragment() {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded()) {
            trx.add(R.id.container, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentTabIndex = index;
    }

    /**
     * item 侧滑点击和底部导航浪点击
     *
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
                switchFragment();
                return true;
            case R.id.bottom_message://分区

                return true;

            case R.id.bottom_ada://动态

                return true;

            case R.id.bottom_mine://会员购

                return true;
            case R.id.drawer_img:
//                ToastUtil.ShortToast("侧滑");
                toggleDrawer();
                return  true;
            case R.id.toolbar_search:
                ToastUtil.ShortToast("搜索");
                return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawer_img:
                ToastUtil.ShortToast("侧滑");
                toggleDrawer();
                break;
            case R.id.toolbar_search:
                ToastUtil.ShortToast("搜索");
                break;

        }
    }
}

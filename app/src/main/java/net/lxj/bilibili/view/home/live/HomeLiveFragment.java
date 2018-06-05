package net.lxj.bilibili.view.home.live;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import net.lxj.bilibili.R;
import net.lxj.bilibili.adapter.LiveAppIndexAdapter;
import net.lxj.bilibili.net.RetrofitHelper;
import net.lxj.bilibili.utils.SnackbarUtil;
import net.lxj.bilibili.view.common.BaseFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeLiveFragment extends BaseFragment {
    public static HomeLiveFragment newIntance() {
        return new HomeLiveFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_live;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initRefreshLayout();
        initRecyclerView();
        isPrepared = false;
    }


}

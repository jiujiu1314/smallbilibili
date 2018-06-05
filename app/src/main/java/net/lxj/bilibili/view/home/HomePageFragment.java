package net.lxj.bilibili.view.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import net.lxj.bilibili.R;
import net.lxj.bilibili.view.common.BaseFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomePageFragment extends BaseFragment {


    @BindView(R.id.moretab_indicator)
    ScrollIndicatorView moretabIndicator;
    @BindView(R.id.view_page)
    ViewPager moretabViewPager;
    Unbinder unbinder;
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private List<String> names;
    String[] titles = new String[]{"直播","推荐","追番"};
    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.home_page_fragment_layout;
    }

    @Override
    public void finishCreateView(Bundle state) {
            finishTask();
    }

    @Override
    protected void finishTask() {
        super.finishTask();
        moretabIndicator.setBackgroundColor(Color.WHITE);
        moretabIndicator.setScrollBar(new ColorBar(getActivity(), 0xFFFF4081, 4));
        moretabIndicator.setSplitAuto(true);
        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.3f;
        moretabIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFFFF4081, Color.GRAY).setSize(selectSize, unSelectSize));
        moretabViewPager.setOffscreenPageLimit(3);
        moretabViewPager.setCurrentItem(1);
        indicatorViewPager = new IndicatorViewPager(moretabIndicator, moretabViewPager);
        inflate = LayoutInflater.from(getActivity());
        names = new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            names.add(titles[i]);
        }
        indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
    }
    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(names.get(position));
            int padding = dipToPix(10);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {

//            MoreFragment f = new MoreFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("index", position);
//            bundle.putInt(MoreFragment.INTENT_INT_INDEX, data.getData().get(position).getId());
//            f.setArguments(bundle);
            Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                    : new MoreFragment();
            try {

                Log.e("test", "getItem:" + position + " from cache" + mFragmentCache.containsKey
                        (position));
                if (f.getArguments() == null) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("index", position);
//                    bundle.putInt(MoreFragment.INTENT_INT_INDEX, data.get(position).getId());
//                    f.setArguments(bundle);
                    Log.e("test", "setArguments:" + position);
                }
                mFragmentCache.put(position, f);

            }catch (Exception e){
              e.printStackTrace();
            }
            return f;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }

    }


    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

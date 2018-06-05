package net.lxj.bilibili.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.lxj.bilibili.R;
import net.lxj.bilibili.entity.live.LiveAppIndexInfo;
import net.lxj.bilibili.entity.live.MultiItem;

import java.util.List;

public class LiveAppIndexAdapter extends BaseMultiItemQuickAdapter<MultiItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public LiveAppIndexAdapter(List<MultiItem> data) {
        super(data);
        addItemType(MultiItem.TYPE_ENTRANCE, R.layout.item_live_entrance);
        addItemType(MultiItem.TYPE_LIVE_ITEM,R.layout.item_live_partition);
        addItemType(MultiItem.TYPE_PARTITION,R.layout.item_live_partition_title);
        addItemType(MultiItem.TYPE_BANNER,R.layout.item_live_banner);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItem item) {

    }
}

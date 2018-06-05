package net.lxj.bilibili.entity.live;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultiItem implements MultiItemEntity {


    //直播分类入口
    public static final int TYPE_ENTRANCE = 0;
    //直播Item
    public static final int TYPE_LIVE_ITEM = 1;
    //直播分类Title
    public static final int TYPE_PARTITION = 2;
    //直播页Banner
    public static final int TYPE_BANNER = 3;
    private int itemType;

    public MultiItem(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {
        return itemType;
    }
}

package pers.tuershen.nbtedit.panel;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class AbstractPanel {

    //页面总大小
    public static final int MAX_SLOT = 54;

    //上一页的按钮位置
    public static final int PREVIOUS_SLOT_POS = 48;

    //中间的按钮
    public static final int MIDDLE_SLOT_POS = 49;

    //下一页按钮位置
    public static final int NEXT_SLOT_POS = 50;

    //页面集合
    protected List<Inventory> inventoryList = new ArrayList<>();

    //当前页面
    protected Inventory panel;

    //页面数量
    protected int page;

    //当前页面下标
    protected int nowPage;

    /**
     * 上一页
     * @return
     */
    public Inventory thePreviousPage() {
        if ((this.nowPage - 1) < 0){
            return this.inventoryList.get(this.nowPage);
        }
        return this.inventoryList.get(--this.nowPage);
    }

    /**
     * 下一页
     * @return 界面
     */
    public Inventory nextPage() {
        if ((this.nowPage + 1) >= this.inventoryList.size()){
            return this.inventoryList.get(this.nowPage);
        }
        return this.inventoryList.get(++this.nowPage);
    }

}

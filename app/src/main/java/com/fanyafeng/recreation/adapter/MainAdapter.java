package com.fanyafeng.recreation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.recreation.R;
import com.fanyafeng.recreation.bean.MainItemBean;
import com.fanyafeng.recreation.network.Urls;
import com.fanyafeng.recreation.refreshview.recyclerview.BaseRecyclerAdapter;
import com.fanyafeng.recreation.util.ControllerListenerUtil;
import com.fanyafeng.recreation.util.DpPxConvert;
import com.fanyafeng.recreation.util.MyUtils;
import com.fanyafeng.recreation.util.StringUtil;

import java.util.List;

/**
 * Author： fanyafeng
 * Data： 16/11/9 14:52
 * Email: fanyafeng@live.cn
 */
public class MainAdapter extends BaseRecyclerAdapter<MainAdapter.MainViewHolder> {

    private Context context;
    private List<MainItemBean> mainItemBeanList;

    public MainAdapter(Context context, List<MainItemBean> mainItemBeanList) {
        this.context = context;
        this.mainItemBeanList = mainItemBeanList;
    }

    @Override
    public MainViewHolder getViewHolder(View view) {
        return new MainViewHolder(view);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_layout, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position, boolean isItem) {
        MainViewHolder mainViewHolder = holder;
        MainItemBean mainItemBean = mainItemBeanList.get(position);
        mainViewHolder.tvMainItem.setText(mainItemBean.getContent());
        if (!StringUtil.isNullOrEmpty(mainItemBean.getImage()) && !mainItemBean.getImage().equalsIgnoreCase("null")) {
            mainViewHolder.sdvMainItem.setVisibility(View.VISIBLE);
            String img = Urls.PICTURE_ITEM + String.valueOf(mainItemBean.getId()).substring(0, 5) + "/" + mainItemBean.getId() + "/medium/";
            ControllerListenerUtil.setControllerListener(mainViewHolder.sdvMainItem, img + mainItemBean.getImage(),
                    (int) (MyUtils.getScreenWidth(context) - DpPxConvert.dip2px(context, 60)));
        } else {
            mainViewHolder.sdvMainItem.setVisibility(View.GONE);
        }
    }

    @Override
    public int getAdapterItemCount() {
        return mainItemBeanList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView tvMainItem;
        SimpleDraweeView sdvMainItem;

        public MainViewHolder(View itemView) {
            super(itemView);
            tvMainItem = (TextView) itemView.findViewById(R.id.tvMainItem);
            sdvMainItem = (SimpleDraweeView) itemView.findViewById(R.id.sdvMainItem);
        }
    }
}
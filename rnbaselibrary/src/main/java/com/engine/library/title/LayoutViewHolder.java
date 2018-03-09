package com.engine.library.title;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class LayoutViewHolder {
    /**
     * Views indexed with their IDs
     * 根据id的View索引列表
     */
    private final SparseArray<View> views = new SparseArray<>();
    private View convertView;

    public LayoutViewHolder(Context context, int layoutId) {
        this.convertView = LayoutInflater.from(context).inflate(layoutId,null,false);
    }
    public LayoutViewHolder(Context context, ViewGroup parent,int layoutId) {
        this.convertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
    }
    public View getConvertView() {
        return convertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置文字显示 会根据文字是否为空决定其是否显示，以最大程度的服用布局文件
     */
    public LayoutViewHolder setTextViewText(int viewId, String text) {
        TextView view = getView(viewId);
        if(null != view) {
            if (!TextUtils.isEmpty(text)) {
                view.setVisibility(View.VISIBLE);
                view.setText(text);
            } else {
                view.setVisibility(View.GONE);
            }
        }
        return this;
    }
    public LayoutViewHolder setImageRes(int viewId, int resId) {
        ImageView view = getView(viewId);
        if(null != view) {
            if (resId != 0) {
                view.setVisibility(View.VISIBLE);
                view.setImageResource(resId);
            }else{
                view.setVisibility(View.GONE);
            }
        }
        return this;
    }
    public LayoutViewHolder setViewOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        if(null != view && null != listener){
            view.setOnClickListener(listener);
        }
        return this;
    }
    public LayoutViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        if(null != view){
            view.setTextColor(color);
        }
        return this;
    }
}

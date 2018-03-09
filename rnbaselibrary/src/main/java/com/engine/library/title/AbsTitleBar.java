package com.engine.library.title;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @description: 抽象TitleBar 依赖布局  title虽然大多数差不多，但是个别还是有区别
 */


public abstract class AbsTitleBar<P extends AbsTitleBar.Builder.Params> implements ITitleBar{
    protected P mParams;

    public AbsTitleBar(P params) {
        this.mParams = params;
        mParams.mParent.addView(mParams.mViewHolder.getConvertView(),0);
        applyParams();
    }

    public View getTitleBarView() {
        return mParams.mViewHolder.getConvertView();
    }

    /**
     * 动态操控某个View时通过该方法获取title中的具体View
     */
    public View getInsideView(int vId){
        return mParams.mViewHolder.getView(vId);
    }

    /**
     * 如果需要简单的去改变View的某些属性时，可通过该方法得到的ViewHolder去设置
     * 如改变文字内容。
     * @return
     */
    public LayoutViewHolder getViewHolder(){
        return mParams.mViewHolder;
    }

    public abstract static class Builder{

        public abstract AbsTitleBar builder();

        public  static class Params{
            public Context mContext;
            public ViewGroup mParent;
            public LayoutViewHolder mViewHolder;



            public Params(Context context,ViewGroup parent,int layoutId) {
                this.mContext = context;
                this.mParent = parent;
                mViewHolder = new LayoutViewHolder(context,layoutId);
            }
        }
    }
}

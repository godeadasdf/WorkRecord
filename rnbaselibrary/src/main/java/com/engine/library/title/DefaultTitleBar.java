package com.engine.library.title;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.engine.library.R;


public class DefaultTitleBar extends AbsTitleBar<DefaultTitleBar.DefaultBuilder.DefaultParams> {
    public DefaultTitleBar(DefaultBuilder.DefaultParams params) {
        super(params);
    }
    @Override
    public void applyParams() {
        mParams.mViewHolder.setTextViewText(R.id.tv_title_left,mParams.leftText);
        if(mParams.leftImageResId == 0){
            mParams.mViewHolder.setImageRes(R.id.iv_title_left,R.drawable.ic_back_white);
        }else{
            mParams.mViewHolder.getView(R.id.iv_title_left).setVisibility(View.VISIBLE);
            mParams.mViewHolder.setImageRes(R.id.iv_title_left,mParams.leftImageResId);
        }
        if(mParams.leftImageShow){
            mParams.mViewHolder.getView(R.id.iv_title_left).setVisibility(View.VISIBLE);
        }else{
            mParams.mViewHolder.getView(R.id.iv_title_left).setVisibility(View.GONE);
        }
        mParams.mViewHolder.setTextViewText(R.id.tv_title_title,mParams.title);
        mParams.mViewHolder.setTextViewText(R.id.tv_title_subtitle,mParams.subTitle);
        mParams.mViewHolder.setTextViewText(R.id.tv_title_right,mParams.rightText);
        mParams.mViewHolder.setImageRes(R.id.iv_title_right_1,mParams.rightImageResId1);
        mParams.mViewHolder.setImageRes(R.id.iv_title_right_2,mParams.rightImageResId2);
        if(null == mParams.leftClickListner){
            if(mParams.leftImageShow || 0 != mParams.leftImageResId || !TextUtils.isEmpty(mParams.leftText)){
                mParams.mViewHolder.setViewOnClickListener(R.id.rl_title_left_area, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null != mParams.mContext && mParams.mContext instanceof Activity){
                            ((Activity)mParams.mContext).onBackPressed();
                        }
                    }
                });
            }
        }else{
            mParams.mViewHolder.setViewOnClickListener(R.id.rl_title_left_area,mParams.leftClickListner);
        }
        mParams.mViewHolder.setViewOnClickListener(R.id.tv_title_right,mParams.rightTextClickListner);
        mParams.mViewHolder.setViewOnClickListener(R.id.iv_title_right_1,mParams.rightImage1ClickListner);
        mParams.mViewHolder.setViewOnClickListener(R.id.iv_title_right_2,mParams.rightImage2ClickListner);
        if(mParams.supportImmersion){
            if(mParams.immersionColorId == 0){
                //沉浸式
                if(null != mParams.mContext && mParams.mContext instanceof Activity){
                    StatusBarCompat.translucentStatusBar((Activity)mParams.mContext);
                    View titleBar = mParams.mViewHolder.getConvertView();
                    titleBar.setPadding(titleBar.getPaddingLeft(),titleBar.getPaddingTop()+ StatusBarCompat.getStatusBarHeight(mParams.mContext),titleBar.getPaddingRight(),titleBar.getPaddingBottom());
                }
            }else{
                //着色状态栏
                if(null != mParams.mContext && mParams.mContext instanceof Activity){
                    StatusBarCompat.setStatusBarColor((Activity)mParams.mContext,mParams.mContext.getResources().getColor(mParams.immersionColorId));
                }
            }
        }
    }

    public static class DefaultBuilder extends AbsTitleBar.Builder{

        private final DefaultParams mParams;

        public DefaultBuilder(Context context, ViewGroup parent) {
            mParams = new DefaultParams(context,parent,R.layout.title_default);
        }
        public DefaultBuilder(Context context) {
            ViewGroup parent;
            if(null != context && context instanceof Activity){
                parent = (ViewGroup) ((ViewGroup) ((Activity)context).findViewById(android.R.id.content)).getChildAt(0);
            }else{
                parent = new FrameLayout(context);
            }
            mParams = new DefaultParams(context,parent,R.layout.title_default);
        }

        public DefaultBuilder setLeftText(String leftText){
            mParams.leftText = leftText;
            return this;
        }
        public DefaultBuilder setLeftImageResourceId(int lefImageResId){
            mParams.leftImageResId = lefImageResId;
            return this;
        }
        public DefaultBuilder setLeftImageShow(boolean leftImageShow){
            mParams.leftImageShow = leftImageShow;
            return this;
        }

        public DefaultBuilder setTitle(String title){
            mParams.title = title;
            return this;
        }
        public DefaultBuilder setSubTitle(String subTitle){
            mParams.subTitle = subTitle;
            return this;
        }
        public DefaultBuilder setRightText(String rightText){
            mParams.rightText = rightText;
            return this;
        }
        public DefaultBuilder setRightImage1(int rightmageResId1){
            mParams.rightImageResId1 = rightmageResId1;
            return this;
        }
        public DefaultBuilder setRightImage2(int rightmageResId2){
            mParams.rightImageResId2 = rightmageResId2;
            return this;
        }
        public DefaultBuilder setSupportImmersion(boolean supportImmersion){
            mParams.supportImmersion = supportImmersion;
            return this;
        }
        public DefaultBuilder setImmersionColorId(int immersionColorId){
            mParams.immersionColorId = immersionColorId;
            return this;
        }
        public DefaultBuilder setLeftClickListner(View.OnClickListener leftClickListner){
            mParams.leftClickListner = leftClickListner;
            return this;
        }
        public DefaultBuilder setRightTextClickListner(View.OnClickListener rightTextClickListner){
            mParams.rightTextClickListner = rightTextClickListner;
            return this;
        }
        public DefaultBuilder setRightImage1ClickListner(View.OnClickListener rightImage1ClickListner){
            mParams.rightImage1ClickListner = rightImage1ClickListner;
            return this;
        }
        public DefaultBuilder setRightImage2ClickListner(View.OnClickListener rightImage2ClickListner){
            mParams.rightImage2ClickListner = rightImage2ClickListner;
            return this;
        }
        @Override
        public DefaultTitleBar builder() {
            DefaultTitleBar defaultTitleBar = new DefaultTitleBar(mParams);
            return defaultTitleBar;
        }

        /**
         * 只关注内容参数，动态改变属性时刻通过getView得到具体View去操作，简单操作时刻通过viewHolder去操作
         */
        public static class DefaultParams extends AbsTitleBar.Builder.Params{

            public String leftText;
            public boolean leftImageShow = true;
            public int leftImageResId;
            public String title;
            public String subTitle;
            public String rightText;
            public int rightImageResId1;
            public int rightImageResId2;

            public boolean supportImmersion;
            public int immersionColorId;

            public View.OnClickListener leftClickListner;//左边区域统一一个点击事件
            public View.OnClickListener rightTextClickListner;
            public View.OnClickListener rightImage1ClickListner;
            public View.OnClickListener rightImage2ClickListner;

            public DefaultParams(Context context, ViewGroup parent,int layoutId) {
                super(context, parent,layoutId);
            }
        }
    }
}

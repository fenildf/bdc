package com.example.a00.fragment3;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.bdcapp.R;

/**
 * 当用户要替换头像时调用本地图库或者拍摄
 */

public class GetPictureActivity extends PopupWindow{
    //上下文对象
    private Context mContext;
    //Title文字
    private String mTitle;
    //PopupWindow对象
    private PopupWindow mPopupWindow;
    //选项的文字
    private String[] options;
    //选项的颜色
    private int[] Colors;
    //点击事件
    private onPopupWindowItemClickListener itemClickListener;

    /**
     * 一个参数的构造方法，用于弹出无标题的PopupWindow
     *
     * @param context
     */
    public GetPictureActivity (Context context) {
        this.mContext = context;
    }

    /**
     * 2个参数的构造方法，用于弹出有标题的PopupWindow
     *
     * @param context
     * @param title   标题
     */
    public GetPictureActivity (Context context, String title) {
        this.mContext = context;
        this.mTitle = title;
    }

    /**
     * 设置选项的点击事件
     *
     * @param itemClickListener
     */
    public void setItemClickListener(onPopupWindowItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 设置选项文字
     */
    public void setItemText(String... items) {
        options = items;
    }

    /**
     * 设置选项文字颜色，必须要和选项的文字对应
     */
    public void setColors(int... color) {
        Colors = color;
    }


    /**
     * 添加子View
     */
    private void addView(View v) {
        LinearLayout lin_layout = (LinearLayout) v.findViewById(R.id.layout_popup_add);
        //Title
        TextView tv_pop_title = (TextView) v.findViewById(R.id.tv_popup_title);
        //取消按钮
        Button bCancel = (Button) v.findViewById(R.id.btn_cancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if (mTitle != null) {
            tv_pop_title.setText(mTitle);
        } else {
            tv_pop_title.setVisibility(View.GONE);
        }
        if (options != null && options.length > 0) {
            for (int i = 0; i < options.length; i++) {
                View item = LayoutInflater.from(mContext).inflate(R.layout.itemchoose, null);
                Button btn_txt = (Button) item.findViewById(R.id.btn_popup_option);
                btn_txt.setText(options[i]);
                if (Colors != null && Colors.length == options.length) {
                    btn_txt.setTextColor(Colors[i]);
                }
                final int finalI = i;
                btn_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null) {
                            itemClickListener.onItemClick(finalI);
                        }
                    }
                });
                lin_layout.addView(item);
            }
        }
    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow() {
        View popupWindow_view = LayoutInflater.from(mContext).inflate(R.layout.activity_get_picture, null);
        //添加子View
        addView(popupWindow_view);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlpa(false);
            }
        });
        //防止挡住虚拟键
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        show(popupWindow_view);
    }


    /**
     * 显示PopupWindow
     */
    private void show(View v) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        }
        setWindowAlpa(true);
    }


    /**
     * 消失PopupWindow
     */
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 动态设置Activity背景透明度
     *
     * @param isopen
     */
    public void setWindowAlpa(boolean isopen) {
        if (Build.VERSION.SDK_INT < 20) {
            return;
        }
        final Window window = ((Activity) mContext).getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ValueAnimator animator;
        if (isopen) {
            animator = ValueAnimator.ofFloat(1.0f, 0.5f);
        } else {
            animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        }
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                lp.alpha = alpha;
                window.setAttributes(lp);
            }
        });
        animator.start();
    }


    /**
     * 点击事件选择回调
     */
    public interface onPopupWindowItemClickListener {
        void onItemClick(int position);
    }
}


package com.wenshao.coursate.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.support.v4.view.NestedScrollingParent;

/**
 * Created by wenshao on 2017/10/14.
 * 嵌套滚动 暂时没有使用
 */

public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {
    public StickyNavLayout(Context context) {
        super(context);
    }

    public StickyNavLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyNavLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StickyNavLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes)
    {
        return true;
    }
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed)
    {
        /*boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop)
        {
            scrollBy(0, dy);
            consumed[1] = dy;
        }*/
        scrollBy(0, dy);
    }
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY)
    {
        //if (getScrollY() >= mTopViewHeight) return false;
        //fling((int) velocityY);
        return true;
    }
    /*public void fling(int velocityY)
    {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }
    @Override
    public void scrollTo(int x, int y)
    {
        if (y < 0)
        {
            y = 0;
        }
        if (y > mTopViewHeight)
        {
            y = mTopViewHeight;
        }
        if (y != getScrollY())
        {
            super.scrollTo(x, y);
        }
    }*/
}

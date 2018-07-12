package net.arvin.androidstudy.contentprovider.contact;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by arvinljw on 2018/7/11 18:38
 * Function：带divider的粘性头部
 * Desc：需自定义Header样式
 */
public class StickySectionDecoration extends DividerItemDecoration {

    private GroupInfoCallback mCallback;
    public int mHeaderHeight;

    StickySectionDecoration(Context context, GroupInfoCallback callback) {
        super(context, VERTICAL);
        this.mCallback = callback;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mCallback == null) {
            return;
        }

        int realPos = parent.getChildAdapterPosition(view);
        GroupInfo groupInfo = mCallback.getGroupInfo(realPos);
        if (groupInfo == null) {
            return;
        }

        if (groupInfo.isFirstViewInGroup()) {//如果是分组的第一个，那么外边框的上边界要往上移
            View sectionView = mCallback.getSectionView(realPos);
            sectionView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            mHeaderHeight = sectionView.getMeasuredHeight();
            outRect.top = mHeaderHeight;

            if (groupInfo.isLastViewInGroup()) {//如果同时还是最后一个，把外边框的下边界设为0，免得有divider
                outRect.bottom = 0;
            }
        } else if (groupInfo.isLastViewInGroup()) {//如果只是分组最后一个，那么上下边界都设为0，即无header也无divider
            outRect.top = 0;
            outRect.bottom = 0;
        } else {//既不是分组的第一个也不是最后一个，那么就只有divider
            outRect.top = 0;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        //因为RecyclerView是复用item的，所以这个数量就是屏幕内能显示出来的item的数量
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            drawHeaderRect(c, parent, i);
        }
    }

    /**
     * @param c      画布
     * @param parent recyclerView
     * @param pos    屏幕中itemView的位置
     */
    private void drawHeaderRect(Canvas c, RecyclerView parent, int pos) {
        if (mCallback == null) {
            return;
        }
        View view = parent.getChildAt(pos);
        int realPos = parent.getChildAdapterPosition(view);
        GroupInfo groupinfo = mCallback.getGroupInfo(realPos);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top;
        int bottom;

        if (pos != 0) {
            if (groupinfo.isFirstViewInGroup()) {//如果是分组的第一个
                top = view.getTop() - mHeaderHeight;
                bottom = view.getTop();
            } else {
                //不是屏幕的第一个并且不是分组的第一个就不需要绘制header
                return;
            }
        } else {
            top = parent.getPaddingTop();//如果是屏幕中的第一个，就应该在父容器的顶部
            if (groupinfo.isLastViewInGroup()) {//如果这时候它又是这个分组的最后一个，他就会被下一个分组顶上去
                int realTop = view.getBottom() - mHeaderHeight;
                if (realTop <= top) {
                    top = realTop;
                }
            }
            bottom = top + mHeaderHeight;
        }

        View sectionView = mCallback.getSectionView(realPos);
        sectionView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        mHeaderHeight = sectionView.getMeasuredHeight();

        sectionView.setDrawingCacheEnabled(true);
        sectionView.layout(left, top, right, bottom);
        c.drawBitmap(sectionView.getDrawingCache(), left, top, null);
    }

    public interface GroupInfoCallback {
        GroupInfo getGroupInfo(int position);

        View getSectionView(int pos);
    }
}

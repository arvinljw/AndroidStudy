package net.arvin.androidstudy.contentprovider.contact;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import net.arvin.androidstudy.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arvinljw on 2018/7/10 13:55
 * Function：
 * Desc：
 */
public class SideBarView extends View {
    private static final String TAG = "SideBarView";
    public static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    public static Map<String, Boolean> letterMap;
    public static final int DEFAULT_COLOR = Color.BLACK;
    public static final int DEFAULT_SELECTED_COLOR = Color.BLACK;
    public static final int DEFAULT_SIZE = 14;

    private Paint paint = new Paint();
    private int letterColor = DEFAULT_COLOR;
    private int letterSelectedColor = DEFAULT_SELECTED_COLOR;
    private float letterSize = DEFAULT_SIZE;

    private int letterHeight;
    private int selectLetterPos = -1;
    private int touchSlop;

    private boolean intercept;

    private float downX;
    private float downY;

    private OnLetterSelectListener onLetterSelectListener;

    public SideBarView(Context context) {
        this(context, null);
    }

    public SideBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideBarView);
        letterSize = typedArray.getDimension(R.styleable.SideBarView_sidebar_letter_size, sp2px(context, DEFAULT_SIZE));
        letterColor = typedArray.getColor(R.styleable.SideBarView_sidebar_letter_color, DEFAULT_COLOR);
        letterSelectedColor = typedArray.getColor(R.styleable.SideBarView_sidebar_letter_selected_color, DEFAULT_SELECTED_COLOR);
        typedArray.recycle();

        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        paint.setColor(letterColor);
        paint.setAntiAlias(true);
        paint.setTextSize(letterSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultWidth = (int) (letterSize + getPaddingLeft() + getPaddingRight());
        int defaultHeight = (int) (letterSize * LETTERS.length + getPaddingTop() + getPaddingBottom());
        setMeasuredDimension(getDefaultSize(defaultWidth, widthMeasureSpec), getDefaultSize(defaultHeight, heightMeasureSpec));
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = size;
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        letterHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / LETTERS.length;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                if (x >= 0 && x <= getWidth()) {
                    intercept = true;
                    chooseLetter(y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (intercept) {
                    chooseLetter(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(x - downX) < touchSlop && Math.abs(y - downY) < touchSlop) {
                    performClick();
                }
                resetStatus();
                break;
            case MotionEvent.ACTION_CANCEL:
                resetStatus();
                break;
        }
        return intercept;
    }

    private void resetStatus() {
        downX = 0;
        downY = 0;
        intercept = false;
        if (onLetterSelectListener != null) {
            //表示取消
            onLetterSelectListener.onLetterSelected("", -1);
        }
    }

    private void chooseLetter(float y) {
        int pos = (int) (y / letterHeight);
        if (pos >= 0 && pos < LETTERS.length && selectLetterPos != pos) {
            Log.w(TAG, LETTERS[pos]);
            selectLetterPos = pos;
            invalidate();
            if (onLetterSelectListener != null) {
                onLetterSelectListener.onLetterSelected(LETTERS[pos], pos);
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < LETTERS.length; i++) {
            if (selectLetterPos == i && letterMap.get(LETTERS[i])) {
                paint.setColor(letterSelectedColor);
            } else {
                paint.setColor(letterColor);
            }
            float xPos = (getWidth() - paint.measureText(LETTERS[i])) / 2;
            float yPos = letterHeight * i + (getStatusBarHeight(getContext()) + letterHeight - paint.measureText(LETTERS[i])) / 2;
            canvas.drawText(LETTERS[i], xPos, yPos, paint);
        }
    }

    public void setOnLetterSelectListener(OnLetterSelectListener onLetterSelectListener) {
        this.onLetterSelectListener = onLetterSelectListener;
    }

    public static void clearLetterMap() {
        letterMap = new HashMap<>();
        for (String letter : SideBarView.LETTERS) {
            letterMap.put(letter, false);
        }
    }

    public static void putLetterIn(String letter) {
        letterMap.put(letter, true);
    }

    private static int sp2px(Context context, int sp) {
        return (int) (context.getResources().getDisplayMetrics().scaledDensity * sp + 0.5f);
    }

    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public interface OnLetterSelectListener {
        void onLetterSelected(String letter, int pos);
    }
}

package net.arvin.androidstudy.scroll;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import net.arvin.androidstudy.R;
import net.arvin.androidstudy.utils.EasyBlur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvinljw on 2018/8/13 14:32
 * Function：
 * Desc：
 */
public class ScrollActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    private ListView list;
    private View layoutHeader;
    private View itemHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        layoutHeader = findViewById(R.id.layout_header);
        list = findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, getData()));
        list.setOnScrollListener(this);

        itemHeader = View.inflate(this, R.layout.item_header, null);
        list.addHeaderView(itemHeader);

        final ImageView imgHeader = itemHeader.findViewById(R.id.img_header);
        Glide.with(this)
                .asBitmap()
                .load("http://code.replays.net/uploads/body/img1.gamersky.com/image2016/10/20161019_yyc_325_10/icon.jpg")
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap bitmap = EasyBlur.with(ScrollActivity.this)
                                .bitmap(resource)
                                .radius(5)
                                .blur();
                        imgHeader.setImageBitmap(bitmap);
                    }
                });
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item" + i);
        }
        return data;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View child = view.getChildAt(0);
        if (child != null) {
            if (firstVisibleItem == 0) {
                int height = child.getHeight() * firstVisibleItem - child.getTop();
                Log.e("onScroll", "height=" + height);
                int headerHeight = layoutHeader.getHeight() * 3;
                if (height >= headerHeight) {
                    height = headerHeight;
                }
                float alpha = ((float) height / headerHeight) * 0.7f + 0.3f;
                layoutHeader.setAlpha(alpha);
            } else {
                layoutHeader.setAlpha(1);
            }
        }
    }
}

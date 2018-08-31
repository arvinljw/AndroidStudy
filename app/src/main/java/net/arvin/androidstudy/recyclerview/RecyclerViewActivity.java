package net.arvin.androidstudy.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.androidstudy.R;
import net.arvin.androidstudy.base.BaseActivity;
import net.arvin.itemdecorationhelper.ItemDecorationFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by arvinljw on 2018/7/23 10:37
 * Function：
 * Desc：更多ItemDecoration参考 https://github.com/arvinljw/ItemDecorationHelper 项目
 */
public class RecyclerViewActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<String> items;
    private BaseQuickAdapter<String, BaseViewHolder> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recycler_view);

        generateData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(getAdapter());
        recyclerView.addItemDecoration(new ItemDecorationFactory.DividerBuilder().dividerHeight(4).build(recyclerView));
    }

    private void generateData() {
        items = new ArrayList<>();
        Random random = new Random(47);
        for (int i = 0; i < 123; i++) {
            int column = i % 4 + 1;
            if (column == 1) {
                items.add("item->" + (i + 1) + "--float--" + random.nextFloat());
            } else if (column == 3) {
                items.add("item->" + (i + 1) + "--int--" + random.nextInt());
            } else {
                items.add("item->" + (i + 1));
            }
        }
    }

    private BaseQuickAdapter<String, BaseViewHolder> getAdapter() {
        if (adapter == null) {
            adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text, items) {
                @Override
                protected void convert(BaseViewHolder helper, String item) {
                    helper.setText(R.id.tv_text, item);
                }
            };
        }
        return adapter;
    }
}

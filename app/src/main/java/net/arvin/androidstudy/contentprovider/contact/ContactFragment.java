package net.arvin.androidstudy.contentprovider.contact;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.arvin.androidstudy.R;
import net.arvin.androidstudy.utils.IWeakHandler;
import net.arvin.androidstudy.utils.WeakHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arvinljw on 2018/7/9 19:14
 * Function：
 * Desc：
 */
public class ContactFragment extends Fragment implements OnItemClickListener, IWeakHandler {
    private static final int MSG_TEXT_GONE = 1001;
    private static final int MSG_GET_CONTACTS = 1002;

    private RecyclerView contactList;
    private ContactAdapter adapter;

    private StickySectionDecoration sectionDecoration;
    private List<ContactEntity> items = new ArrayList<>();
    private Map<String, Integer> index;
    private List<String> keys;

    private TextView tvCurr;
    private int showLetter;

    private WeakHandler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        tvCurr = rootView.findViewById(R.id.tv_curr);
        contactList = rootView.findViewById(R.id.list_contact);

        contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContactAdapter(getActivity(), items);
        adapter.setOnItemClickListener(this);
        contactList.setAdapter(adapter);
        sectionDecoration = new StickySectionDecoration(getActivity(), callback);
        contactList.addItemDecoration(sectionDecoration);

        handler = new WeakHandler(this);

        return rootView;
    }

    private String dealLetter(int i) {
        char ch = items.get(i).getPinyinName().charAt(0);
        if (ch < 'a' || ch > 'z') {
            ch = '#';
        }
        return String.valueOf(ch).toUpperCase();
    }

    StickySectionDecoration.GroupInfoCallback callback = new StickySectionDecoration.GroupInfoCallback() {
        @Override
        public GroupInfo getGroupInfo(int position) {
            String id = dealLetter(position);
            GroupInfo info = new GroupInfo(id);
            info.setPosition(position - index.get(id));
            int i = keys.indexOf(id);
            if (i < keys.size() - 1) {
                String str = keys.get(i + 1);
                info.setGroupLength(index.get(str) - index.get(id));
            } else {
                info.setGroupLength(items.size() - index.get(id));
            }
            return info;
        }

        @Override
        public View getSectionView(int pos) {
            View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_section_header, null);
            TextView tvSection = headerView.findViewById(R.id.tv_section);
            tvSection.setText(dealLetter(pos));
            return headerView;
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        contactList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    showLetter = 1;
                } else if (showLetter == 1 && newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    showLetter = 2;
                } else {
                    if (showLetter == 2) {
                        hideLetter();
                        showLetter = 0;
                    }
                }

                //校验流畅的滚动的位置
                if (mShouldScroll && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mShouldScroll = false;
                    smoothMoveToPosition(recyclerView, mToPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (showLetter == 2) {
                    int firstVisibleItemPosition = ((LinearLayoutManager) contactList.getLayoutManager()).findFirstVisibleItemPosition();
                    String letter = items.get(firstVisibleItemPosition).getName().substring(0, 1);
                    showLetter(letter);
                }
            }
        });
        contactList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    List<ContactEntity> contacts = ContactHelper.getContacts(getActivity());
                    Message msg = Message.obtain();
                    msg.what = MSG_GET_CONTACTS;
                    msg.obj = contacts;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    public void onItemClicked(View v, int position) {

    }

    @Override
    public void handlerMessage(Message msg) {
        if (getActivity() == null) {
            return;
        }
        switch (msg.what) {
            case MSG_GET_CONTACTS:
                @SuppressWarnings("unchecked")
                List<ContactEntity> contacts = (List<ContactEntity>) msg.obj;
                items.clear();
                items.addAll(contacts);
                groupInfo();
                adapter.notifyDataSetChanged();
                break;
            case MSG_TEXT_GONE:
                if (tvCurr != null) {
                    tvCurr.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void groupInfo() {
        SideBarView.clearLetterMap();
        index = new HashMap<>();
        keys = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            String ch = dealLetter(i);
            if (!index.containsKey(ch)) {
                index.put(ch, i);
                SideBarView.putLetterIn(ch);
                keys.add(ch);
            }
        }
    }

    public void scrollToLetterPosition(String letter, int letterPos) {
        if (letterPos == -1) {
            hideLetter();
        } else {
            if (index.containsKey(letter)) {
                int pos = index.get(letter);
                showLetter(letter);

                smoothMoveToPosition(contactList, pos);
//                contactList.scrollToPosition(pos);
//                LinearLayoutManager mLayoutManager = (LinearLayoutManager) contactList.getLayoutManager();
//                mLayoutManager.scrollToPositionWithOffset(pos, 0);
            }
        }
    }

    private void hideLetter() {
        if (tvCurr.getVisibility() == View.VISIBLE) {
            handler.sendEmptyMessageDelayed(MSG_TEXT_GONE, 1000);
        }
    }

    private void showLetter(String letter) {
        clearMsg();
        tvCurr.setVisibility(View.VISIBLE);
        tvCurr.setText(letter);
    }

    private boolean mShouldScroll;
    private int mToPosition;

    /**
     * 顺滑的滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            //在屏幕上方，直接滚上去就是顶部
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            //在屏幕中，直接滚动到相应位置的顶部
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                //粘性头部，会占据一定的top空间，所以真是的top位置应该是减去粘性header的高度
                int top = mRecyclerView.getChildAt(movePosition).getTop() - sectionDecoration.mHeaderHeight;
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            //在屏幕下方，需要西安滚动到屏幕内，在校验
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

    private void clearMsg() {
        if (handler.hasMessages(MSG_TEXT_GONE)) {
            handler.removeMessages(MSG_TEXT_GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearMsg();
        if (handler.hasMessages(MSG_GET_CONTACTS)) {
            handler.removeMessages(MSG_GET_CONTACTS);
        }
    }
}

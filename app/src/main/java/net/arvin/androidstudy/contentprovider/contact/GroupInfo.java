package net.arvin.androidstudy.contentprovider.contact;

/**
 * Created by arvinljw on 2018/7/11 18:49
 * Function：
 * Desc：
 */
public class GroupInfo {
    private String mTitle;
    //在分组内的位置
    private int position;
    // 组的成员个数
    private int mGroupLength;

    public GroupInfo(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public boolean isFirstViewInGroup () {
        return position == 0;
    }

    public boolean isLastViewInGroup () {
        return position == mGroupLength - 1 && position >= 0;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setGroupLength(int groupLength) {
        this.mGroupLength = groupLength;
    }
}

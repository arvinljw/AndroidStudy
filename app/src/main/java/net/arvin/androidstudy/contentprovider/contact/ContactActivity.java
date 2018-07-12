package net.arvin.androidstudy.contentprovider.contact;

import android.Manifest;
import android.os.Bundle;

import net.arvin.androidstudy.R;
import net.arvin.androidstudy.base.BaseActivity;

public class ContactActivity extends BaseActivity implements SideBarView.OnLetterSelectListener {

    private SideBarView sideBar;
    private ContactFragment contactFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        sideBar = findViewById(R.id.sidebar);

        checkPermission(new CheckPermListener() {
            @Override
            public void agreeAllPermission() {
                showContacts();
            }
        }, "需要读取联系人权限", Manifest.permission.READ_CONTACTS);

        sideBar.setOnLetterSelectListener(this);
    }

    @Override
    protected void backFromSetting() {
        showContacts();
    }

    private void showContacts() {
        contactFragment = new ContactFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_contact_list, contactFragment)
                .commit();
    }

    @Override
    public void onLetterSelected(String letter, int pos) {
        contactFragment.scrollToLetterPosition(letter, pos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

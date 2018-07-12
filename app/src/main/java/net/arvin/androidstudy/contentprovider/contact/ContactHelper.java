package net.arvin.androidstudy.contentprovider.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arvinljw on 2018/7/9 18:57
 * Function：
 * Desc：
 */
public class ContactHelper {
    private static final String[] PROJECTION = {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY,
    };

    public static List<ContactEntity> getContacts(Context context) {
        List<ContactEntity> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION,
                    null, null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
            HashMap<String, ContactEntity> contactIdMap = new HashMap<>();
            if (cursor != null) {
                cursor.moveToFirst(); // 游标移动到第一项
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    //对应PROJECTION中name和number的位置
                    String name = cursor.getString(0);
                    String number = cursor.getString(1);
                    if (!contactIdMap.containsKey(number)) {
                        ContactEntity contact = new ContactEntity();
                        contact.setName(name);
                        contact.setPinyinName(HanziToPinyin.getPinYin(name));
                        contact.setPhoneNum(number);
                        list.add(contact);
                        contactIdMap.put(number, contact);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }
}

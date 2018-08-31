package net.arvin.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.arvin.androidstudy.constraintlayout.ConstrainLayoutActivity;
import net.arvin.androidstudy.contentprovider.contact.ContactActivity;
import net.arvin.androidstudy.jni.JniActivity;
import net.arvin.androidstudy.recyclerview.RecyclerViewActivity;
import net.arvin.androidstudy.scroll.ScrollActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toConstraintLayout(View view) {
        startActivity(new Intent(this, ConstrainLayoutActivity.class));
    }

    public void toContentProvider(View view) {
        startActivity(new Intent(this, ContactActivity.class));
    }

    public void toRecyclerView(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void toScroll(View view) {
        startActivity(new Intent(this, ScrollActivity.class));
    }
    public void toJni(View view) {
        startActivity(new Intent(this, JniActivity.class));
    }
}

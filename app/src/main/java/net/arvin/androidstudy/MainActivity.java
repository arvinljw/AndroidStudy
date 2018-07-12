package net.arvin.androidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.arvin.androidstudy.constraintlayout.ConstrainLayoutActivity;
import net.arvin.androidstudy.contentprovider.contact.ContactActivity;

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
}

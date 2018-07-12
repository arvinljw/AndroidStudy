package net.arvin.androidstudy.constraintlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.arvin.androidstudy.R;

public class ConstrainLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
    }

    public void toRelativePosition(View v) {
        startActivity(new Intent(this, RelativePositionActivity.class));
    }

    public void toMargins(View view) {
        startActivity(new Intent(this, MarginActivity.class));
    }

    public void toChains(View view) {
        startActivity(new Intent(this, ChainsActivity.class));
    }

    public void toGuideLine(View view) {
        startActivity(new Intent(this, GuideLineActivity.class));
    }
}

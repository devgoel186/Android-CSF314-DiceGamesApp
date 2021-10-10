package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button mBackButton = findViewById(R.id.rules_btn_back);     // Back button to return to two or more activity

        mBackButton.setOnClickListener(this::returnToTwoOrMore);
    }

    /**
     * Returns to the TwoOrMoreActivity via an intent
     *
     * @param view the View for the activity
     */
    public void returnToTwoOrMore(View view)
    {
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
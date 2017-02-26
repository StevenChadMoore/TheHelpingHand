package davidurbina.disasterrelief;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DonateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
    }

    public void onNecessitiesClick(View view) {
        Intent intent = new Intent(DonateActivity.this, DonateNecessitiesActivity.class);
        startActivity(intent);
    }

    public void onFEMAClick(View view) {
        String url = "https://www.fema.gov/volunteer-donate-responsibly";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onRedCrossClick(View view) {
        String url = "https://www.redcross.org/donate/donation";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}

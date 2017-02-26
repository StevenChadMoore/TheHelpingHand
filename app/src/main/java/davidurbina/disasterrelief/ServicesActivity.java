package davidurbina.disasterrelief;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
    }

    public void onShelterClick(View view) {
        Intent intent = new Intent(ServicesActivity.this,VolunteerShelterActivity.class);
        startActivity(intent);
    }

    public void onAssistanceClick(View view) {
        Intent intent = new Intent(ServicesActivity.this, VolunteerNecessitiesActivity.class);
        startActivity(intent);
    }

    public void redCross(View view){
        String url = "https://www.redcross.org/volunteer/become-a-volunteer#step1";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void fema(View view){
        String url = "https://www.fema.gov/apply-assistance";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void giveBlood(View view){
        String url = "http://www.redcross.org/give-blood";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}

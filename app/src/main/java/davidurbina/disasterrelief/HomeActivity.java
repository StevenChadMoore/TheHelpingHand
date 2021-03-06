package davidurbina.disasterrelief;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onAssistanceClick(View view) {
        Intent intent = new Intent(HomeActivity.this, AssistanceActivity.class);
        startActivity(intent);
    }

    public void onServicesClick(View view) {
        Intent intent = new Intent(HomeActivity.this, ServicesActivity.class);
        startActivity(intent);
    }

    public void onDonateClick(View view) {
        Intent intent = new Intent(HomeActivity.this, DonateActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {


    }
}

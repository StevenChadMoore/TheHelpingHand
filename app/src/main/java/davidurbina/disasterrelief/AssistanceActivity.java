package davidurbina.disasterrelief;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AssistanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance);
    }

    public void onShelterClick(View view) {
        Intent intent = new Intent(AssistanceActivity.this,RequestShelterActivity.class);
        startActivity(intent);
    }

    public void onNecessitiesClick(View view) {
        Intent intent = new Intent(AssistanceActivity.this, RequestNecessitiesActivity.class);
        startActivity(intent);
    }

    public void onAssistanceClick(View view) {
        Intent intent = new Intent(AssistanceActivity.this, RequestGeneralActivity.class);
        startActivity(intent);
    }

    public void onFemaAssistance(View view){
        String url = "https://www.fema.gov/apply-assistance";
        Intent intent = new Intent(this,WebViewActivity.class);
        intent.putExtra("URL",url);
        startActivity(intent);
    }
}

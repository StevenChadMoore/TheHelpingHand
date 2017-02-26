package davidurbina.disasterrelief;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    Button submit;
    EditText username;
    EditText password;
    EditText name;
    EditText phonenumber;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        submit = (Button) findViewById(R.id.btnSubmit);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        phonenumber = (EditText) findViewById(R.id.phonenumber);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    public void createUser(){
        queue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.POST,"http://10.0.2.2:8888/Hackathon/test.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Log",response.toString());

                    MyApplication.setUsername(username.getText().toString());
                    Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
                    startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                JSONObject parameters = new JSONObject();
                JSONObject wrapper = new JSONObject();

                try {
                    wrapper.put("parameters",parameters);
                    wrapper.put("request_type","CreateUser");
                    parameters.put("username",username.getText().toString());
                    parameters.put("password",password.getText().toString());
                    parameters.put("name",name.getText().toString());
                    parameters.put("phone_number",phonenumber.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                params.put("wrapper",wrapper.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        queue.add(sr);
    }
}

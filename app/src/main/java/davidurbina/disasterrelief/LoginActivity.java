package davidurbina.disasterrelief;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


public class LoginActivity extends AppCompatActivity {

    Button login;
    Button signup;
    RequestQueue queue;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(username.getText().toString(), password.getText().toString());
            }
        });

        signup = (Button) findViewById(R.id.btnSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void login(final String username, final String password){

        queue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest sr = new StringRequest(Request.Method.POST,"http://10.0.2.2:8888/Hackathon/test.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Log",response.toString());
                if(Integer.parseInt(response.toString())>0){
                    MyApplication.setUsername(username);
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(LoginActivity.this, "Incorrect username/password", duration);
                    toast.show();
                }
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
                    wrapper.put("request_type","Login");
                    parameters.put("username",username);
                    parameters.put("password",password);
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


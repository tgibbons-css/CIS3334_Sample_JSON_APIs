package css.catfacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textViewStatus;
    TextView textViewFact;
    Button buttonGetFact;

    // ---- Remember to use Volley web library add the following to the Module build.gradle file, see https://developer.android.com/training/volley
    //      implementation 'com.android.volley:volley:1.1.1'
    private RequestQueue mRequestQueue;         // Used by Volley to do HTTP request

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStatus = findViewById(R.id.textViewStatus);
        textViewFact = findViewById(R.id.textViewFact);

        setupButtonGetFact();
    }

    private void getCatFact() {
        // Define URL to use. Using Cat Facts API here. Note number of facts set to 1
        // ---- Remember to add the following permission to the AndroidManifest.xml file
        //      <uses-permission android:name="android.permission.INTERNET" />
        String url = "https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=1";
        // Create a Volley web request to receive back a JSON object.
        // This requires two listeners for Async response, onResponse() and onErrorResponse()
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        textViewStatus.setText("Received Response ");
                        String jsonFact= response.toString();
                        // Remember to add the following to the module build.gradle file for the gson library for parsing json files
                        // implementation 'com.google.code.gson:gson:2.8.6'
                        Gson gson = new Gson();
                        Fact fact = gson.fromJson(jsonFact, Fact.class);
                        textViewFact.setText(fact.getText());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        textViewStatus.setText("ERROR Response: " + error.toString());
                    }
                });

        // Create a RequestQueue used to send web requests using Volley
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void setupButtonGetFact() {
        buttonGetFact = findViewById(R.id.buttonGetFact);
        buttonGetFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCatFact();
            }
        });
    }

}
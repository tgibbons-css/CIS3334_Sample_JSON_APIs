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

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStatus = findViewById(R.id.textViewStatus);
        textViewFact = findViewById(R.id.textViewFact);

        setupButtonGetFact();
    }

    private void getCatFact() {
        String url = "https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        textViewStatus.setText("Received Response ");
                        String jsonFact= response.toString();
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

        //RequestQueue initialized
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
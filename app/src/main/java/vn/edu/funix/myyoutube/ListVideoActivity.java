package vn.edu.funix.myyoutube;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListVideoActivity extends AppCompatActivity {
    String fullname;
    int id;

    TextView txtName;
    ArrayList<VideoYoutube> listVideo;
    VideoYoutube videoYoutube;
    YouTubeAdapter youTubeAdapter;
    ListView listviewVideo;
    Spinner spinnerHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        AddControl();
        AddEvent();
    }

    private void AddEvent() {
        listviewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListVideoActivity.this, HomeActivity.class);
                intent.putExtra("idVideo", listVideo.get(position).getIdVideo());
                startActivity(intent);
            }
        });

        spinnerHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        Update();
                        break;
                    case 2:
                        Logout();
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void AddControl() {
        Intent intent1 = getIntent();
        id = intent1.getIntExtra("id", 0);
        fullname = intent1.getStringExtra("FullName");

        txtName = (TextView) findViewById(R.id.txtName);
        txtName.setText(fullname);

        listVideo = new ArrayList<>();
        GetJsonYouTube(Auth.VideoJson);
        listviewVideo = (ListView) findViewById(R.id.listviewVideo);
        youTubeAdapter = new YouTubeAdapter(this, R.layout.row_video, listVideo);
        listviewVideo.setAdapter(youTubeAdapter);
        Intent intent = new Intent(this, HomeActivity.class);

        spinnerHome = (Spinner) findViewById(R.id.spinnerHome);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHome.setAdapter(adapter);


    }

    private void Update() {
        Intent intent1 = new Intent(this, UpdateUserActivity.class);
        intent1.putExtra("id", id);
        startActivity(intent1);
    }

    private void Logout() {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }

    private void GetJsonYouTube(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String title = "";
                        String url = "";
                        String videoID = "";
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");

                                title = jsonSnippet.getString("title");
                                url = jsonMedium.getString("url");
                                videoID = jsonResourceID.getString("videoId");

                                videoYoutube = new VideoYoutube(title, url, videoID);
                                listVideo.add(videoYoutube);
                            }

                            youTubeAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(ListVideoActivity.this, "Load video success", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListVideoActivity.this, "Load video Error", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

}

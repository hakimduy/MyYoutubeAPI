package vn.edu.funix.myyoutube;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

public class MainActivity extends AppCompatActivity {

    final String DATABASE_NAME = "dbMyYouTube.sqlite";
    SQLiteDatabase database;
    BaseAdapter adapter;
    Button btCreate;
    Intent intent;
    EditText etUser;
    EditText etPass;
    String pass;
    String user;
    SignInButton btSignIn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControl();
        AddEvent();
    }

    private void AddEvent() {
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = etUser.getText().toString();
                pass = etPass.getText().toString();
                String query = "SELECT * FROM USERS where user='" + user + "' and pass='" + pass + "'";
                try {
                    Cursor cursor = database.rawQuery(query, null);
                    cursor.moveToFirst();
                    if (cursor.getCount() > 0) {
                        intent.putExtra("id", Integer.parseInt(cursor.getString(0)));
                        intent.putExtra("FullName",cursor.getString(3));
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Ban nhap sai vui long nhap lai", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage().toString());
                }


            }
        });
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create();

            }
        });
    }

    private void Create() {
        Intent intents=new Intent(this,AddUserActivity.class);
        startActivity(intents);
    }

    private void AddControl() {
        btSignIn= (SignInButton) findViewById(R.id.btSignIn);
        btCreate = (Button) findViewById(R.id.btCreate);
        intent = new Intent(this, ListVideoActivity.class);
        etUser = (EditText) findViewById(R.id.editTextUser);
        etPass = (EditText) findViewById(R.id.editTextPass);
        database = Database.initDatabase(this, DATABASE_NAME);

    }


}

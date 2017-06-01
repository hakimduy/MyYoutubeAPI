package vn.edu.funix.myyoutube;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {
    SQLiteDatabase database;
    final String DATABASE_NAME = "dbMyYouTube.sqlite";
    BaseAdapter adapter;


    String user;
    String fullname;
    String email;
    String password;
    String repassword;

    EditText etUser;
    EditText etFullName;
    EditText etEmail;
    EditText etPass;
    EditText etRePass;

    Button btSave;
    Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        addControl();
        addEvent();
    }

    private void addControl() {

        etUser = (EditText) findViewById(R.id.edtUserName);
        etFullName = (EditText) findViewById(R.id.edtFullName);
        etEmail = (EditText) findViewById(R.id.edtEmail);
        etPass = (EditText) findViewById(R.id.edtPass);
        etRePass = (EditText) findViewById(R.id.edtRePass);
        btSave = (Button) findViewById(R.id.btSave);
        btCancel = (Button) findViewById(R.id.btCancel);
        database = Database.initDatabase(this, DATABASE_NAME);

    }

    private void addEvent() {
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();

            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    private void Save() {
        user = etUser.getText().toString();
        fullname = etFullName.getText().toString();
        email = etEmail.getText().toString();
        password = etPass.getText().toString();
        repassword = etRePass.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("pass", password);
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        try {
            if (user == null || password == null || repassword == null) {

                Toast.makeText(this, "Vui lòng nhập dầy đủ các thông tin !", Toast.LENGTH_LONG).show();
            } else {
                if (password.equals(repassword)) {
                    database.insert("users", null, contentValues);
                    Cancel();
                } else {
                    etPass.setText("");
                    etRePass.setText("");
                    Toast.makeText(this, "Mật khẩu nhập không trùng vui lòng nhập lại !", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    private void Cancel() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

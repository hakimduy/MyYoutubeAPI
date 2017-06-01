package vn.edu.funix.myyoutube;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateUserActivity extends AppCompatActivity {

    SQLiteDatabase database;
    final String DATABASE_NAME = "dbMyYouTube.sqlite";
    BaseAdapter adapter;

    int idUser;
    String user;
    String fullname;
    String email;
    String password;
    String repassword;

    TextView txtUSer;
    EditText etFullName;
    EditText etEmail;
    EditText etPass;
    EditText etRePass;

    Button btSave;
    Button btCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        addControl();
        addEvent();
    }

    private void addControl() {

        txtUSer = (TextView) findViewById(R.id.txtUserName);
        etFullName = (EditText) findViewById(R.id.edtFullName);
        etEmail = (EditText) findViewById(R.id.edtEmail);
        etPass = (EditText) findViewById(R.id.edtPass);
        etRePass = (EditText) findViewById(R.id.edtRePass);
        btSave = (Button) findViewById(R.id.btSave);
        btCancel = (Button) findViewById(R.id.btCancel);
        Intent intent = getIntent();
        idUser = intent.getIntExtra("id", -1);

        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM USers WHERE ID = ? ", new String[]{idUser + ""});
        cursor.moveToFirst();

        user = "Update user : " + cursor.getString(1).toString();
        password = cursor.getString(2).toString();
        fullname = cursor.getString(3).toString();
        email = cursor.getString(4).toString();

        txtUSer.setText(user);
        etFullName.setText(fullname);
        etEmail.setText(email);
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
        ContentValues contentValues = new ContentValues();

        fullname = etFullName.getText().toString();
        email = etEmail.getText().toString();
        password = etPass.getText().toString();
        repassword = etRePass.getText().toString();

        contentValues.put("fullName", fullname);
        contentValues.put("email", email);
        try {
            if (!password.isEmpty() && !repassword.isEmpty()) {
                if (password.equals(repassword)) {
                    contentValues.put("pass", password);
                    database.update("users", contentValues, "id = ?", new String[]{idUser + ""});
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

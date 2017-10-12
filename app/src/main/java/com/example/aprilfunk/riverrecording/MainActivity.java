package com.example.aprilfunk.riverrecording;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userid;
    private String userid_str;
    private EditText password;
    private String password_str;
    private EditText username;
    private String username_str;
    private EditText rnumber;
    private String rnumber_str;
    private Boolean rnumbercert = false;
    private RadioButton rb1;
    private RadioButton rb2;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private Spinner s;
    private int pos;
    private TextView birthday;
    private String birthday_str;
    private final int DIALOG_DATE = 1;
    private Button btnResistCancel;
    private String Location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = (Spinner)findViewById(R.id.spinner1);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Location = parent.getItemAtPosition(position).toString();
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        birthday = (TextView) findViewById(R.id.birthday);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });



        btnResistCancel = (Button)findViewById(R.id.btnResistCancel);
        btnResistCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("가입을 취소하시겠습니까?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete_values();
                        finish();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void registOk(View v) {

        userid = (EditText) findViewById(R.id.userid);
        userid_str = userid.getText().toString();
        password = (EditText) findViewById(R.id.password);
        password_str = password.getText().toString();
        username = (EditText)findViewById(R.id.username);
        username_str = username.getText().toString();
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        birthday = (TextView)findViewById(R.id.birthday);
        birthday_str = birthday.getText().toString();

        if(userid_str.equals("")) {
            Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_LONG).show();
            return;
        }
        if(userid_str.length()<4 || userid_str.length()>16) {
            Toast.makeText(getApplicationContext(), "아이디는 4자 이상 또는 16자 이하로 작성하세요.", Toast.LENGTH_LONG).show();
            return;
        }
        if(password_str.equals("")) {
            Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
            return;
        }
        if(password_str.length()<4 || password_str.length()>16) {
            Toast.makeText(getApplicationContext(), "비밀번호는 4자 이상 또는 16자 이하로 작성하세요.", Toast.LENGTH_LONG).show();
            return;
        }

        if(!rb1.isChecked()&&!rb2.isChecked())
            Toast.makeText(getApplicationContext(), "성별을 체크해 주세요.", Toast.LENGTH_LONG).show();

        cb1 = (CheckBox) findViewById(R.id.checkBox);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);

        int cbnum1 = (cb1.isChecked()) ? 1 : 0;
        int cbnum2 = (cb2.isChecked()) ? 1 : 0;
        int cbnum3 = (cb3.isChecked()) ? 1 : 0;
        int cbnum4 = (cb4.isChecked()) ? 1 : 0;
        int cbnum5 = (cb5.isChecked()) ? 1 : 0;

        if(cbnum1 + cbnum2 + cbnum3 + cbnum4 + cbnum5 == 0)
            Toast.makeText(getApplicationContext(), "취미를 선택해 주세요.", Toast.LENGTH_LONG).show();
        if(cbnum1 + cbnum2 + cbnum3 + cbnum4 + cbnum5 == 1)
            Toast.makeText(getApplicationContext(), "취미는 적어도 2개 이상 선택해 주세요.", Toast.LENGTH_LONG).show();

        if(pos == 0) {
            Toast.makeText(getApplicationContext(), "지역을 선택해 주세요 ", Toast.LENGTH_SHORT).show();
        }

    }

    public void rnumCheck(View v) {
        rnumber = (EditText) findViewById(R.id.rnumber);
        rnumber_str = rnumber.getText().toString();

        int t=2;
        int result1 = 0;
        for(int i=0; i<12; i++) {
            result1 += (Integer.parseInt(rnumber_str.substring(i, i+1)))*t;
            t++;
            if(t>9) {
                t=2;
            }
        }

        int result2 = 11 - (result1 % 11);
        if(result2 >= 10) {
            result2 %= 10;
        }

        if(result2 == Integer.parseInt(rnumber_str.substring(12, 13))) {
            rnumbercert = true;
            Toast.makeText(getApplicationContext(), "유효한 주민등록번호입니다.", Toast.LENGTH_LONG).show();
            return;
        }else {
            Toast.makeText(getApplicationContext(), "잘못된 주민등록번호입니다.", Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        if(id == 1) {
            DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    Toast.makeText(getApplicationContext(), year + "년 " + (month + 1) + "월 " + day + "일 을 선택했습니다.", Toast.LENGTH_SHORT).show();
                    birthday.setText(Integer.toString(year) + " / " + Integer.toString(month+1) + " / " + Integer.toString(day));
                }
            }, 2017, 8, 26);
            return dpd;
        }
        return super.onCreateDialog(id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("가입을 취소하시겠습니까?");
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return false;
    }

    public void layoutClick(View v) {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}

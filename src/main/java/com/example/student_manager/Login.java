package com.example.student_manager;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button login_button,sign_button,find_password_button;
    EditText User_name;
    EditText password;
    //定义数据库变量
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button=(Button)findViewById(R.id.login);
        sign_button=(Button)findViewById(R.id.sign1);
        find_password_button=(Button)findViewById(R.id.find_password_button);
        //定义按钮监听器
        ButtonListener buttonListener=new ButtonListener();
        login_button.setOnClickListener(buttonListener);
        sign_button.setOnClickListener(buttonListener);
        find_password_button.setOnClickListener(buttonListener);
        //intent在Sign和Login之间传递数据
        User_name=(EditText)findViewById(R.id.User_name);
        password=(EditText)findViewById(R.id.login_password);
        Intent intent=getIntent();
        if(intent!=null){
            User_name.setText(intent.getStringExtra("name"));
            password.setText(intent.getStringExtra("login_password"));
        }
        //创建数据库辅助类对象，指定数据库的名称和版本号
    }
    //添加switch对按钮事件进行选择
    public class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login:
                    query_User(User_name.getText().toString(),password.getText().toString());
                    break;
                case R.id.sign1:
                    Intent intent1=new Intent(Login.this,Sign.class);
                    startActivity(intent1);
                    break;
                case R.id.find_password_button:
                    Intent intent2=new Intent(Login.this,Find_Password.class);
                    startActivity(intent2);
                    break;
                    default:
                        break;
            }
        }
    }
        //查询是否存在用户
    public  void query_User(String user_id, String password){
        /*
        try {
        }catch (Exception e){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("错误！");
            builder.setMessage("用户名或密码错误");
            builder.setPositiveButton("确认",null);
        }
        */
        Intent intent=new Intent(Login.this,Home.class);
        startActivity(intent);
    }
}
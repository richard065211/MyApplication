package com.example.student_manager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Sign extends AppCompatActivity {
    Button sign_in;
    //定义activity_sign里的控件
    private EditText password;
    private EditText check_password;
    private EditText phonenumber;
    private EditText email;
    private EditText User_id;
    private RadioGroup sexgroup;
    private RadioButton malebutton;
    private RadioButton femalebutton;
    private String sexname;//设置sexname获取选中的性别
    //定义check_view里的控件
    TextView User_id1;
    TextView password1;
    TextView sex1;
    TextView phonenumber1;
    TextView email1;
    private  View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        sign_in=(Button)findViewById(R.id.sign_in);
        password=(EditText)findViewById(R.id.password);
        check_password=(EditText)findViewById(R.id.check_password);
        phonenumber=(EditText)findViewById(R.id.phonenumber);
        email=(EditText)findViewById(R.id.email);
        User_id=(EditText)findViewById(R.id.User_id);
        //设置监听器
        ButtonListener buttonListener=new ButtonListener();
        sign_in.setOnClickListener(buttonListener);

        //通过id获取check_view中所需要的控件
        User_id1=(TextView)findViewById(R.id.User_id1);
        password1=(TextView)findViewById(R.id.password1);
        sex1=(TextView)findViewById(R.id.sex1);
        phonenumber1=(TextView)findViewById(R.id.phonenumber1);
        email1=(TextView)findViewById(R.id.email1);
        view1=LayoutInflater.from(Sign.this).inflate(R.layout.check_view,null);
        //获取性别控件
        sexgroup=(RadioGroup)findViewById(R.id.sex);
        malebutton=(RadioButton)findViewById(R.id.malebutton);
        femalebutton=(RadioButton)findViewById(R.id.femalebutton);
        setlistenerforview();
    }
    //判断注册信息输入是否为空
    public void sign_null_check(){
        AlertDialog.Builder sign_null_check=new AlertDialog.Builder(this);
        sign_null_check.setTitle("确认提示！");
        sign_null_check.setMessage(checckInfo());
        sign_null_check.setPositiveButton("确认",null);
        sign_null_check.setNegativeButton("取消",null);
        sign_null_check.create().show();
    }
    public String checckInfo(){
        String name=User_id.getText().toString();
        if(name==null||"".equals(name)){
            return "用户名不能为空！";
        }
        String psd=password.getText().toString();
        if(psd==null||"".equals(psd)){
            return "密码不能为空！";
        }
        String check_psd=check_password.getText().toString();
        if(!psd.equals(check_psd)){
            return "两次密码必须一致！";
        }
        String phonenum=phonenumber.getText().toString();
        if(phonenum==null||"".equals(phonenum)){
            return "电话号码不能为空！";
        }
        String email_add=email.getText().toString();
        if(email_add==null||"".equals(email_add)){
            return "邮箱地址不能为空！";
        }
        return null;
    }
/*
    private void getString() {
        String userid=User_id.getText().toString();
        String password1=password.getText().toString();
        String phonenum=phonenumber.getText().toString();
        String email11=email.getText().toString();
    }
    */

    //向Login传值操作
    private void set_login_info(){
        Intent intent=new Intent(Sign.this,Login.class);
        intent.putExtra("name",User_id.getText().toString());
        intent.putExtra("login_password",password.getText().toString());
        startActivity(intent);
    }
    //向AlertDialog中传值方法
    private void check_view1(){
        User_id1.setText(User_id.getText().toString());
        password1.setText(password.getText().toString());
        sex1.setText(sexname);
        phonenumber1.setText(phonenumber.getText().toString());
        email1.setText(email.getText().toString());

    }
    private void setlistenerforview(){
        sexgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(malebutton.getId()==i){
                    sexname=malebutton.getText().toString();
                }else if (femalebutton.getId()==i){
                    sexname=femalebutton.getText().toString();
                }
            }
        });
    }

    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.sign_in) {
                String checkresult = checckInfo();
                if (checkresult != null) {
                    sign_null_check();
                } else {
                    //弹出告警框提示用户信息是否确认
                    AlertDialog.Builder check_info = new AlertDialog.Builder(Sign.this);
                    check_view1(); //为check_view设值的方法
                    check_info.setView(view1);
                    check_info.setTitle("确认注册信息");
                    check_info.setPositiveButton("确认", new DialogInterface.OnClickListener() {  //确认按钮调用传值方法
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            set_login_info();
                        }
                    });
                    check_info.setNegativeButton("取消", null);
                    check_info.create().show();
                    //如果数据没有错误进行插入操

                    // 用户的信息插入到User表中

                }
            }
        }
    }
}

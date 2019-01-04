package nju.joytrip.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import nju.joytrip.R;
import nju.joytrip.entity.User;

public class LoginActivity extends AppCompatActivity {

    private Button button_signUp;
    private Button button_login;
    private EditText editText_username;
    private EditText editText_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化Bmob
        Bmob.initialize(this, "f6fbdb11a6a945a3382bf9225de95646");
        //绑定控件
        button_login = (Button) findViewById(R.id.button_login);
        button_signUp = (Button) findViewById(R.id.button_signUp);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);

        //登陆状态判断-已经登陆则直接跳转到MainActivity
        if(BmobUser.isLogin()){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            this.startActivity(intent);
            this.finish();
        }
        //监听登录按钮
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                String t_name = editText_username.getText().toString();
                String t_password = editText_password.getText().toString();
                if (t_name.equals("") || t_password.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入完整信息",Toast.LENGTH_LONG).show();
                } else {
                    user.setUsername(t_name);
                    user.setPassword(t_password);
                    user.login(new SaveListener<User>() {
                        public void done(User bmobUser, BmobException e){
                            if (e == null){
                                User user = BmobUser.getCurrentUser(User.class);
                                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                LoginActivity.this.startActivity(intent);
                                LoginActivity.this.finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        //监听注册按钮
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}

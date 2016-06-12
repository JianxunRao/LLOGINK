package com.trojx.llogink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/12.
 */
public class NewTrafficInfoActivity extends AppCompatActivity {

    private EditText editText;
    private Button bt_submit;
    private EditText title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_traffic_info);

        editText = (EditText) findViewById(R.id.et_new_info);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        title = (EditText) findViewById(R.id.et_new_info_title);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVObject avObject=new AVObject("TrafficInfo");
                avObject.put("InfoTitle",title.getText());
                avObject.put("FreeText",editText.getText());
                avObject.put("MessageSendingDate",new Date());
                avObject.put("level","危险");
                avObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e==null){
                            Toast.makeText(NewTrafficInfoActivity.this,"发布成功",Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
            }
        });
    }

}

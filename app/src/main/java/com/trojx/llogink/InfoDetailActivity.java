package com.trojx.llogink;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.IllegalFormatFlagsException;

/**信息详情
 * Created by Trojx on 2016/6/4 0004.
 */
public class InfoDetailActivity extends AppCompatActivity {

    private AVObject infoItem;
    private String imgUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        Intent intent=getIntent();
        infoItem = intent.getParcelableExtra("info");
        imgUrl = intent.getStringExtra("imgUrl");

        TextView tv_title= (TextView) findViewById(R.id.tv_detail_title);
        TextView tv_label= (TextView) findViewById(R.id.tv_detail_label);
        TextView tv_time= (TextView) findViewById(R.id.tv_detail_time);
        TextView tv_content= (TextView) findViewById(R.id.tv_detail_content);
        ImageView iv_detail= (ImageView) findViewById(R.id.iv_detail);

        String title=infoItem.getString("InfoTitle");
        String label=getCateName(infoItem.getString("CategoryId"));
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        String time=sdf.format(infoItem.getDate("MessageSendingDate"));
        String content=infoItem.getString("FreeText");

        tv_title.setText(title);
        tv_label.setText(label);
        tv_time.setText(time);
        tv_content.setText(content);
        Glide.with(this).load(imgUrl).centerCrop().into(iv_detail);




    }
    //栏目代码转名称
    private String getCateName(String code){
        if(code==null){
            return infoItem.getString("ProviderName");
        }

        if(code.equals("001")){
            return "企业公告";
        }else if(code.equals("002")){
            return "企业新闻";
        }else if(code.equals("003")){
            return "行业新闻";
        }else {
            throw new IllegalFormatFlagsException("错误的栏目代码！");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}

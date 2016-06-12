package com.trojx.llogink;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class PublicInfoFragment extends Fragment {


    LinkedList<AVObject> publicInfoList=new LinkedList<>();
    ArrayList<String> infoImgList=new ArrayList<>();
    public static final String TAG="PublicInfoFragment";
    private PublicInfoAdapter adapter;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new PublicInfoAdapter();

        AVQuery<AVObject> query=new AVQuery<>("PublicInfo");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(e==null){
                    publicInfoList= (LinkedList<AVObject>) list;
                    adapter.notifyDataSetChanged();
                }else {
                    Log.e(TAG,e.toString());
                }
            }
        });

        for(int i=1;i<=10;i++){
            infoImgList.add("http://7xla0x.com1.z0.glb.clouddn.com/llogink/imgs/"+i+".jpg");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_public_info,container,false);

        listView = (ListView) view.findViewById(R.id.lv_public_info);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),InfoDetailActivity.class);
                AVObject info=publicInfoList.get(position);
                String imgUrl=infoImgList.get(position%10);
                intent.putExtra("info",info);
                intent.putExtra("imgUrl",imgUrl);
                startActivity(intent);

            }
        });
        return view;
    }

    class PublicInfoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return publicInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return publicInfoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            AVObject info=publicInfoList.get(position);
            if(convertView==null){
                view=LayoutInflater.from(PublicInfoFragment.this.getActivity()).inflate(R.layout.item_info,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.imageView= (ImageView) view.findViewById(R.id.iv_info_item);
                viewHolder.tv_title= (TextView) view.findViewById(R.id.tv_info_item_title);
                viewHolder.tv_content= (TextView) view.findViewById(R.id.tv_info_item_content);
                viewHolder.tv_tag= (TextView) view.findViewById(R.id.tv_info_item_tag);
                viewHolder.tv_time= (TextView) view.findViewById(R.id.tv_info_item_time);
                view.setTag(viewHolder);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) view.getTag();
            }

            viewHolder.tv_title.setText(info.getString("InfoTitle"));
//            viewHolder.tv_content.setText(info.getString("FreeText"));
            viewHolder.tv_content.setVisibility(View.GONE);
            viewHolder.tv_tag.setText(info.getString("ProviderName"));

            SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
            viewHolder.tv_time.setText(sdf.format(info.getDate("MessageSendingDate")));
            Glide.with(PublicInfoFragment.this).load(infoImgList.get(position%10)).centerCrop().into(viewHolder.imageView);

            return view;
        }
        class ViewHolder {
            TextView tv_title,tv_content,tv_tag,tv_time;
            ImageView imageView;
        }

    }
//    //栏目代码转名称
//    private String getCateName(String code){
//        if(code.equals("001")){
//            return "企业公告";
//        }else if(code.equals("002")){
//            return "企业新闻";
//        }else if(code.equals("003")){
//            return "行业新闻";
//        }else {
//            throw new IllegalFormatFlagsException("错误的栏目代码！");
//        }
//    }
}

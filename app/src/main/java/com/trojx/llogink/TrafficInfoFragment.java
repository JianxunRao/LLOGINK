package com.trojx.llogink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class TrafficInfoFragment extends Fragment {

    private LinkedList<AVObject> trafficInfoList=new LinkedList<>();
    private MyTrafficAdapter adapter;
    public static final String TAG="TrafficInfoFragment";
    private ListView listView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MyTrafficAdapter();

        getData();

    }

    private void getData(){
        AVQuery<AVObject> query=new AVQuery<>("TrafficInfo");
        query.orderByDescending("MessageSendingDate");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(e==null){
                    trafficInfoList= (LinkedList<AVObject>) list;
                    adapter.notifyDataSetChanged();
                    if(refreshLayout!=null){
                        refreshLayout.setRefreshing(false);
                    }
                }else {
                    Log.e(TAG,e.toString());
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_traffic_info,container,false);

        listView = (ListView) view.findViewById(R.id.lv_traffic_info);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //...

            }
        });

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_traffic_info);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });


        return view;
    }
    class MyTrafficAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return trafficInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return trafficInfoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            AVObject info=trafficInfoList.get(position);
            String level=info.getString("level");
            if(convertView==null){
                view=LayoutInflater.from(TrafficInfoFragment.this.getActivity()).inflate(R.layout.item_traffic_info,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.tv_title= (TextView) view.findViewById(R.id.tv_traffic_info_title);
                viewHolder.tv_level= (TextView) view.findViewById(R.id.tv_traffic_info_level);
                viewHolder.tv_time= (TextView) view.findViewById(R.id.tv_traffic_info_time);
                viewHolder.tv_content= (TextView) view.findViewById(R.id.tv_traffic_info);

                view.setTag(viewHolder);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) view.getTag();
            }

            viewHolder.tv_level.setText(level);
            switch (level){
                case "一般":
                    viewHolder.tv_level.setBackgroundColor(getResources().getColor(R.color.green));
                    break;
                case "警告":
                    viewHolder.tv_level.setBackgroundColor(getResources().getColor(R.color.yellow));
                    break;
                case "危险":
                    viewHolder.tv_level.setBackgroundColor(getResources().getColor(R.color.red));
                    break;
                default:
                    break;
            }
            viewHolder.tv_title.setText(info.getString("InfoTitle"));
            SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
            Date date=info.getDate("MessageSendingDate");
            viewHolder.tv_time.setText(sdf.format(date));
            viewHolder.tv_content.setText(info.getString("FreeText"));

            return view;
        }
        class ViewHolder{
            TextView tv_level;
            TextView tv_title;
            TextView tv_time;
            TextView tv_content;
        }
    }

}

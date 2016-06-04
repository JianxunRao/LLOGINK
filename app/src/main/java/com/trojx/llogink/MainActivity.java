package com.trojx.llogink;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("公共信息服务中心");

        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab_main);
        MainPagerAdapter pagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new CompanyInfoFragment(),"企业动态");
        pagerAdapter.addFragment(new CompanyInfoFragment(),"道路通阻");
        pagerAdapter.addFragment(new CompanyInfoFragment(),"公共信息");
        pagerAdapter.addFragment(new CompanyInfoFragment(),"我的");
        if (viewPager != null) {
            viewPager.setAdapter(pagerAdapter);
        }
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
//            tabLayout.getTabAt(0).setIcon()
        }


//        initdata();
    }

    private void initdata() {
//        AVObject companyInfo=new AVObject("CompanyInfo");
//        companyInfo.put("LogisticsExchangeCode","LEC000007");
//        companyInfo.put("InfoTitle","2015年全球十大集装箱港排名出炉");
//        companyInfo.put("MessageSendingDate",new Date());
//        companyInfo.put("DocumentName","企业动态信息上传单");
//        companyInfo.put("FreeText","近日，中港网发布2015年全球十大集装箱港吞吐量统计排名表显示，2015年，全球十大集装箱港排名座次中，包括香港港在内的中国港口共包揽七席，余下的第二、第六、第九名分别由新加坡港、韩国釜山港、阿联酋迪拜港摘得。前十大港口中，中国港口“军团”完成的集装箱吞吐量所占比重占到七成，为69.53%，较上年68.61%的分量进一步加重。\n" +
//                "按吞吐量计，2015年，全球十大集装箱港口排序依次为上海港、新加坡港、深圳港、宁波-舟山港、香港港、釜山港、青岛港、广州港、迪拜港、天津港。2015年全球前十港口队列中未有新面孔出现，位次变化也仅有宁波-舟山港和香港港互相交换位置，其余港口仍保持原位。宁波-舟山港继2014年超越韩国釜山港、首次跻身世界前五后， 2015年又一鼓作气超越香港港，集装箱吞吐量多出香港港50多万标箱，坐上第四名的宝座，香港港则由2014年的第四名滑落至第五名，釜山港虽位居第六，但以70万标箱左右的差距，大有重新夺回世界第五之势。\n" +
//                "2015年世界第一大集装箱港口仍为我国的上海港，上海港以3653.7万标箱的吞吐量，连续6年坐稳全球第一的宝座。排名第二的新加坡港在2015年大幅下降8.7%，上海港增长3.5%，被上海港甩出一条街，差距逾500万标箱，多年贴身近搏争夺世界第一，彻底梦碎。从最近四年来看，上海港与新加坡港的差距呈逐步拉大之势，据chineseport.cn数据跟踪，2012年上海港与新加坡港差距为80余万标箱，2013年扩大到100余万标箱，2014年差距再拉大到约140万标箱，而2015年更将差距锁定在560万标箱左右。");
//        companyInfo.put("MessageReferenceNumber","");
//        companyInfo.put("CategoryId","003");
//        companyInfo.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                if(e==null){
//
//                }else {
//                    Log.e(TAG,e.toString());
//                }
//            }
//        });

//        AVObject trafficInfo=new AVObject("TrafficInfo");
//        trafficInfo.put("LogisticsExchangeCode","LEC000001");
//        trafficInfo.put("InfoTitle","杭州湾环线(杭甬) 往杭州 事故");
//        trafficInfo.put("MessageSendingDate",new Date());
//        trafficInfo.put("DocumentName","道路通阻信息上传单");
//        trafficInfo.put("FreeText","06-03 15:05 G92杭州湾环线(杭甬)往杭州方向过沽渚枢纽1公里有事故,占据第一、二车道。");
//        trafficInfo.put("MessageReferenceNumber","");
//        trafficInfo.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                if(e==null){
//
//                }else {
//                    Log.e(TAG,e.toString());
//                }
//            }
//        });

    }
    class MainPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> fragments=new ArrayList<>();
        private ArrayList<String> tags=new ArrayList<>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tags.get(position);
        }

        public void addFragment(Fragment fragment, String tag){
            fragments.add(fragment);
            tags.add(tag);
        }
    }


}

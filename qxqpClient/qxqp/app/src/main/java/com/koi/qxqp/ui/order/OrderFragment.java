package com.koi.qxqp.ui.order;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.koi.qxqp.R;
import com.koi.qxqp.widget.MyActionBar;

public class OrderFragment extends Fragment {
    private Activity myActivity;
    private String[] title={"全部","待审核","已审核"};
    private int[] toAudit={-1,0,1};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order,container,false);
        //保证getActivity()可以获取到上下文
        if (getActivity()==null){
            return view;
        }
        myActivity=getActivity();

        TabLayout tabLayout=view.findViewById(R.id.tl_order_title);
        ViewPager2 viewPager2=view.findViewById(R.id.vp_order_container);
        MyActionBar myActionBar=view.findViewById(R.id.myActionBar);
        myActionBar.setData("订单",0,"",0,1,null);
        MyFragmentStateAdapter myFragmentStateAdapter=new MyFragmentStateAdapter(getActivity());
        viewPager2.setAdapter(myFragmentStateAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(title[position]);
            }
        }).attach();
        initView();
        return view;
    }
    /*页面初始化*/
    private void initView() {

    }
    class MyFragmentStateAdapter extends FragmentStateAdapter{
        //存放Fragment
        Fragment[]fragments;

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            fragments=new Fragment[title.length];
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (fragments[position]==null){
                Bundle bundle=new Bundle();
                bundle.putInt("toAudit",toAudit[position]);
                //创建Fragment
                OrderListFragment orderListFragment=new OrderListFragment();
                //设置参数
                orderListFragment.setArguments(bundle);
                fragments[position]=orderListFragment;

            }
            return fragments[position];
        }

        @Override
        public int getItemCount() {
            return fragments.length;
        }
    }
}

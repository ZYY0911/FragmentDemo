package com.example.fragmentdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment = new Fragment();
    private LinearLayout bottomLayout;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initLister();
        switchFragment(fragments.get(0)).commit();
    }

    private void initData() {
        fragments=new ArrayList<>();
        fragments.add(Fragment1.newInstance());
        fragments.add(Fragment2.newInstance());
        fragments.add(Fragment3.newInstance());
    }

    private void initLister() {
        for (int i=0;i<bottomLayout.getChildCount();i++){
            View childAt = bottomLayout.getChildAt(i);
            final int finalI = i;
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchFragment(fragments.get(finalI)).commit();
                    switchBottonView(v);
                }
            });
        }
    }

    private void switchBottonView(View view){
        for (int i=0;i<bottomLayout.getChildCount();i++){
            TextView childAt= (TextView) bottomLayout.getChildAt(i);
            if (view==childAt){
                childAt.setTextColor(getResources().getColor(R.color.bottomselectcolor));
            }else {
                childAt.setTextColor(getResources().getColor(R.color.bottomtextcolor));
            }
        }
    }

    private void initView() {
        bottomLayout=findViewById(R.id.bottom_layout);
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.mian_frag, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }
}

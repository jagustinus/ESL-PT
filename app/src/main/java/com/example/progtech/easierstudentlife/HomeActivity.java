package com.example.progtech.easierstudentlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.progtech.easierstudentlife.fragment.HomeFragment;
import com.example.progtech.easierstudentlife.fragment.ProfileFragment;
import com.example.progtech.easierstudentlife.fragment.SettingFragment;
import com.example.progtech.easierstudentlife.fragment.SubjectFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class HomeActivity extends AppCompatActivity {

    AnimatedBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomBar = findViewById(R.id.bottom_nav);

        bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                switch (tab1.getId()){
                    case R.id.mi_home:
                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, homeFragment);
                        fragmentTransaction.commit();
                        break;

                    case R.id.mi_mata_kuliah:
                        SubjectFragment subjectFragment = new SubjectFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, subjectFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.mi_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, profileFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.mis_setting:
                        SettingFragment settingFragment = new SettingFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, settingFragment);
                        fragmentTransaction.commit();
                        break;
                }

            }

            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {
                switch (tab.getId()){
                    case R.id.mi_home:
                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, homeFragment);
                        fragmentTransaction.commit();
                        break;

                    case R.id.mi_mata_kuliah:
                        SubjectFragment subjectFragment = new SubjectFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, subjectFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.mi_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, profileFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.mis_setting:
                        SettingFragment settingFragment = new SettingFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment_container, settingFragment);
                        fragmentTransaction.commit();
                        break;
                }
            }
        });

        bottomBar.selectTabById(R.id.mi_home, true);

    }

}

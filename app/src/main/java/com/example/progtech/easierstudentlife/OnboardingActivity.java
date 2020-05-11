package com.example.progtech.easierstudentlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.progtech.easierstudentlife.onboarding.OnboardingItemScreen;
import com.example.progtech.easierstudentlife.onboarding.OnboardingViewPagerAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private Button btnNext, btnGetStarted;
    TabLayout tabIndicator;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        tabIndicator = findViewById(R.id.onboarding_tab_indicatior);


        btnNext = findViewById(R.id.onboarding_btn_next);
        btnGetStarted = findViewById(R.id.onboarding_btn_get_started);

//        ViewPager viewPager = findViewById(R.id.onboarding_viewPager);
//        LinearLayout indicator = findViewById()

        final List<OnboardingItemScreen> mList = new ArrayList<>();
        mList.add(new OnboardingItemScreen("Welcome", "Tahukah kamu?\n" +
                "\n" +
                "Aplikasi ESL bisa bantu kamu ngatur\n" +
                "\n" +
                "waktu kamu jadi lebih produktif loo!", R.drawable.ic_undraw_unexpected_friends_tg6k));
        mList.add(new OnboardingItemScreen("Welcome", "Kamu bebas isi kegiatan apapun di\n" +
                "\n" +
                "kalender. Nah! Prioritaskan edukasi\n" +
                "\n" +
                "ya.", R.drawable.ic_undraw_online_calendar_kvu2));
        mList.add(new OnboardingItemScreen("Welcome", "Kamu juga bisa tambahin tugas-tugas\n" +
                "\n" +
                "dan catatan kamu di kegiatan\n" +
                "\n" +
                "tertentu.", R.drawable.ic_undraw_add_tasks_mxew));
        mList.add(new OnboardingItemScreen("Notification", "Tenang aja! Kalau ada kegiatan atau\n" +
                "\n" +
                "tugas yang udah deket ‘deadline’,\n" +
                "\n" +
                "ESL bakal ingetin kamu kok!", R.drawable.ic_undraw_online_calendar_kvu2));


        final ViewPager screenpager = findViewById(R.id.onboarding_viewPager);
        OnboardingViewPagerAdapter onboardingViewPagerAdapter = new OnboardingViewPagerAdapter(this, mList);
        screenpager.setAdapter(onboardingViewPagerAdapter);


        tabIndicator.setupWithViewPager(screenpager);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenpager.getCurrentItem();
                if(position < mList.size()){
                    position++;
                    screenpager.setCurrentItem(position);
                }

                if(position == mList.size()-1){
                    loadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                } else{
                    btnNext.setVisibility(View.VISIBLE);
                    btnGetStarted.setVisibility(View.INVISIBLE);
                    tabIndicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNext();
            }
        });

    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
    }

    private void launchNext() {
        Intent i = new Intent(OnboardingActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }


}

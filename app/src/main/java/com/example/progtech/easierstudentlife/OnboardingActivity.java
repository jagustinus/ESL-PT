package id.co.jeteas.easierstudentlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.jeteas.easierstudentlife.onboarding.OnboardingItemScreen;
import id.co.jeteas.easierstudentlife.onboarding.OnboardingViewPagerAdapter;

public class OnboardingActivity extends AppCompatActivity {

    OnboardingViewPagerAdapter onboardingViewPagerAdapter;
    TabLayout tabLayout;
    Button btnNext, btnGetStarted;
    int position = 0;
    private ViewPager screenPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_onboarding);

        btnNext = findViewById(R.id.onboarding_btn_next);
        tabLayout = findViewById(R.id.onboarding_tab_indicatior);

        final List<OnboardingItemScreen> mList = new ArrayList<>();
        mList.add(new OnboardingItemScreen("Pesan Dokter", "Catat aktifitas kuliah kamu\n" +
                "dan juga aktifitas lain", R.drawable.onboarding_1));
        mList.add(new OnboardingItemScreen("Pengingat Obat", "Agenda dalam genggaman\n" +
                "tidak perlu lupa jadwal", R.drawable.onboarding_2));
        mList.add(new OnboardingItemScreen("Riwayat Kesehatan", "Lengkapi dengan analisis\n" +
                "penggunaan apps\n" +
                "ke posisi kamu", R.drawable.onboarding_3));

        screenPager = findViewById(R.id.onboarding_viewPager);
        onboardingViewPagerAdapter = new OnboardingViewPagerAdapter(this, mList);
        screenPager.setAdapter(onboardingViewPagerAdapter);

        tabLayout.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size()) {
                    // TODO : Show Masuk button and hide indicator
                    loadLastScreen();
                }
//                if(position )
            }
        });
    }

    private void loadLastScreen() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
//        Preferences.setKeyOnboarding(this);
        finish();
    }
}

package id.co.jeteas.easierstudentlife.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import id.co.jeteas.easierstudentlife.R;

public class OnboardingViewPagerAdapter extends PagerAdapter {
    Context mContext;
    List<OnboardingItemScreen> mScreenItems;

    public OnboardingViewPagerAdapter(Context mContext, List<OnboardingItemScreen> mScreenItems) {
        this.mContext = mContext;
        this.mScreenItems = mScreenItems;
    }

    @Override
    public int getCount() {
        return mScreenItems.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.onboarding_item_layout, null);

        ImageView image = layoutScreen.findViewById(R.id.onboarding_img);
        TextView title = layoutScreen.findViewById(R.id.onboarding_txt_title);
        TextView desc = layoutScreen.findViewById(R.id.onboarding_txt_description);

        title.setText(mScreenItems.get(position).getTitle());
        desc.setText(mScreenItems.get(position).getDescription());
        image.setImageResource(mScreenItems.get(position).getImage());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

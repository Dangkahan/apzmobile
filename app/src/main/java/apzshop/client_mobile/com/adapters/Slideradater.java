package apzshop.client_mobile.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import apzshop.client_mobile.com.R;

public class Slideradater extends PagerAdapter {

    Context context;

    LayoutInflater layoutInflater;

    public Slideradater(Context context){
        this.context = context;
    }

    int[] imagesArray = {

            R.drawable.slideimg1,
            R.drawable.slideimg2,
            R.drawable.slideim3

    };

    int[] headingArray = {

            R.string.first_slide,
            R.string.second_slide,
            R.string.third_slide

    };

    int[] descriptionArray = {

            R.string.description,
            R.string.description,
            R.string.description

    };


    @Override
    public int getCount() {
        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slideview,container,false);

        ImageView imageView = view.findViewById(R.id.slider_img);
        TextView header = view.findViewById(R.id.heading);
        TextView description = view.findViewById(R.id.description);

        imageView.setImageResource(imagesArray[position]);
        header.setText(headingArray[position]);
        description.setText(descriptionArray[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

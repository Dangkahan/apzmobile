package apzshop.client_mobile.com.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import apzshop.client_mobile.com.R;
import apzshop.client_mobile.com.adapters.Slideradater;


public class HomeAct extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    Slideradater slideradater;

    Button btn;

    Toolbar toolbar1;

    static TextView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_home);


        viewPager =  findViewById(R.id.slider);
        dotsLayout =  findViewById(R.id.dots);

        addDots(0);

        slideradater = new Slideradater(this);
        viewPager.setAdapter(slideradater);


        btn=findViewById(R.id.get_started_btn);

        viewPager.addOnPageChangeListener(changeListener);

        Animation animation;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAct.this,MainActivity.class));
                finish();
            }
        });
    }


    private void addDots(int postion) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);

        }
        if (dots.length > 0){
            dots[postion].setTextColor(getResources().getColor(R.color.black));
        }

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);

            if(position == 0.){

                btn.setVisibility(View.INVISIBLE);

            }else if (position == 1){
                btn.setVisibility(View.INVISIBLE);
            }else{
                Animation animation = AnimationUtils.loadAnimation(HomeAct.this, R.anim.slider_animation);
                btn.setAnimation(animation);
                btn.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

}


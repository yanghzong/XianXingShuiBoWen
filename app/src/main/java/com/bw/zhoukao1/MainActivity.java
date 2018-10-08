package com.bw.zhoukao1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private WaveView wv;
    private ImageView imgShow;
    private ViewPager vpZhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgShow = findViewById(R.id.img_show);
        wv = findViewById(R.id.wv);
        /*vpZhu = findViewById(R.id.vp_zhu);
        vpZhu.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
*/
       WaveView.OnWaveViewClicklistener listener = new WaveView.OnWaveViewClicklistener() {
           @Override
           public void onChanged(float y) {

               RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) imgShow.getLayoutParams();
               layoutParams.setMargins(0,0,0, (int) y);
               imgShow.setLayoutParams(layoutParams);

           }
       };

       wv.setOnWaveViewClicklistener(listener);
    }
}

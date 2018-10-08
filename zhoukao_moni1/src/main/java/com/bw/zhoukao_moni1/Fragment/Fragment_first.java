package com.bw.zhoukao_moni1.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.zhoukao_moni1.R;
import com.bw.zhoukao_moni1.WaveView;

/**
 * Created by 择木 on 2018/10/8.
 */

public class Fragment_first  extends Fragment{

    private ImageView imgShow;
    private WaveView wv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.first_item,container,false);
        imgShow = v.findViewById(R.id.img_show);
        wv = v.findViewById(R.id.wv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wv.setOnWaveViewClickListener(new WaveView.OnWaveViewClickListener() {
            @Override
            public void onChange(float y) {
                RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) imgShow.getLayoutParams();
                layoutParams.bottomMargin= (int) -y;
                imgShow.setLayoutParams(layoutParams);

            }
        });


    }
}

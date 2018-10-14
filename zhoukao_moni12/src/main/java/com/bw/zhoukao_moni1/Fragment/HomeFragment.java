package com.bw.zhoukao_moni1.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.zhoukao_moni1.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 择木 on 2018/10/13.
 */

public class HomeFragment extends Fragment {
    private TextView tvSao;
    private ViewPager vpBanner;
    private EditText edText;
    private Button btnEr;
    private ImageView imgMa;
    public static final int REQUEST_CODE = 111;
    private TableLayout tbShow;
    private List<String> imglist;
    public static final int FLAG = 123;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==FLAG){
                int current = vpBanner.getCurrentItem();
                if (current<imglist.size()-1){
                    current++;
                }else{
                    current=0;
                }
                vpBanner.setCurrentItem(current);
                sendEmptyMessageDelayed(FLAG,2000);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.home_fragment,container,false);
        tvSao = v.findViewById(R.id.tv_sao);
        vpBanner = v.findViewById(R.id.vp_banner);
        edText =v. findViewById(R.id.ed_text);
        btnEr = v.findViewById(R.id.btn_er);
        imgMa = v.findViewById(R.id.img_ma);
        return  v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imglist = new ArrayList<>();

        imglist.add("http://01.imgmini.eastday.com/mobile/20180512/20180512072505_0fe08f494e7c090764244e3581b3e5ca_5_mwpm_03200403.jpg");
        imglist.add("http://01.imgmini.eastday.com/mobile/20180512/20180512072505_0fe08f494e7c090764244e3581b3e5ca_1_mwpm_03200403.jpg");
        imglist.add("http://06.imgmini.eastday.com/mobile/20180512/20180512_38f5183808987be3783b180740d12a2a_cover_mwpm_03200403.jpg");


        vpBanner.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imglist.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView img=new ImageView(getActivity());
                Glide.with(getActivity()).load(imglist.get(position)).into(img);
                container.addView(img);
                return  img;

            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        handler.sendEmptyMessageDelayed(FLAG,2000);

        tvSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);


            }
        });
        btnEr.setOnClickListener(new View.OnClickListener() {

            private Bitmap mBitmap;

            @Override
            public void onClick(View v) {
                String textContent = edText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(getActivity(), "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                edText.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
                imgMa.setImageBitmap(mBitmap);

            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}


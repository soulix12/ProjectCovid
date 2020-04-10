package com.stfalcon.chatkit.sample.features.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.stfalcon.chatkit.sample.R;
import com.stfalcon.chatkit.sample.features.demo.custom.holder.CustomHolderDialogsActivity;
import com.stfalcon.chatkit.sample.features.demo.custom.layout.CustomLayoutDialogsActivity;
import com.stfalcon.chatkit.sample.features.demo.custom.media.CustomMediaMessagesActivity;
import com.stfalcon.chatkit.sample.features.demo.def.DefaultDialogsActivity;
import com.stfalcon.chatkit.sample.features.demo.styled.StyledDialogsActivity;
import com.stfalcon.chatkit.sample.features.main.adapter.DemoCardFragment;
import com.stfalcon.chatkit.sample.features.main.adapter.MainActivityPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

/*
 * Created by troy379 on 04.04.17.
 */
public class MainActivity extends AppCompatActivity
        implements DemoCardFragment.OnActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DefaultDialogsActivity.open(this);
    }

    @Override
    public void onAction(int id) {
        DefaultDialogsActivity.open(this);
    }
}

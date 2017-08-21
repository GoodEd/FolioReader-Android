package com.folioreader.ui.folio.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.R;
import com.folioreader.ui.folio.fragment.HighlightFragment;
import com.folioreader.ui.tableofcontents.view.TableOfContentFragment;
import com.folioreader.util.UiUtil;

public class ContentHighlightActivity extends AppCompatActivity {
    private boolean mIsNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_highlight);
        getSupportActionBar().hide();
        mIsNightMode = Config.getConfig().isNightMode();
        initViews();
    }

    private void initViews() {
        UiUtil.setColorToImage(this,Config.getConfig().getThemeColor(),((ImageView) findViewById(R.id.btn_close)).getDrawable());
        findViewById(R.id.layout_content_highlights).setBackgroundDrawable(UiUtil.getShapeDrawable(this, Config.getConfig().getThemeColor()));
        if (mIsNightMode) {
            findViewById(R.id.toolbar).setBackgroundColor(Color.BLACK);
            findViewById(R.id.btn_contents).setBackgroundDrawable(UiUtil.convertColorIntoStateDrawable(this, Config.getConfig().getThemeColor(), R.color.black));
            findViewById(R.id.btn_highlights).setBackgroundDrawable(UiUtil.convertColorIntoStateDrawable(this, Config.getConfig().getThemeColor(), R.color.black));
            ((TextView) findViewById(R.id.btn_contents)).setTextColor(UiUtil.getColorList(this, R.color.black, Config.getConfig().getThemeColor()));
            ((TextView) findViewById(R.id.btn_highlights)).setTextColor(UiUtil.getColorList(this, R.color.black, Config.getConfig().getThemeColor()));

        } else {
            ((TextView) findViewById(R.id.btn_contents)).setTextColor(UiUtil.getColorList(this, R.color.white, Config.getConfig().getThemeColor()));
            ((TextView) findViewById(R.id.btn_highlights)).setTextColor(UiUtil.getColorList(this, R.color.white, Config.getConfig().getThemeColor()));
            findViewById(R.id.btn_contents).setBackgroundDrawable(UiUtil.convertColorIntoStateDrawable(this, Config.getConfig().getThemeColor(), R.color.white));
            findViewById(R.id.btn_highlights).setBackgroundDrawable(UiUtil.convertColorIntoStateDrawable(this, Config.getConfig().getThemeColor(), R.color.white));
        }


        loadContentFragment();
        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_contents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadContentFragment();
            }
        });

        findViewById(R.id.btn_highlights).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHighlightsFragment();
            }
        });
    }

    private void loadContentFragment() {
        findViewById(R.id.btn_contents).setSelected(true);
        findViewById(R.id.btn_highlights).setSelected(false);
        TableOfContentFragment contentFrameLayout
                = TableOfContentFragment.newInstance(getIntent().getStringExtra(Constants.CHAPTER_SELECTED));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.parent, contentFrameLayout);
        ft.commit();
    }

    private void loadHighlightsFragment() {
        findViewById(R.id.btn_contents).setSelected(false);
        findViewById(R.id.btn_highlights).setSelected(true);
        HighlightFragment highlightFragment = new HighlightFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.parent, highlightFragment);
        ft.commit();
    }

}

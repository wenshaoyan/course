package com.wenshao.coursate.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wenshao.coursate.util.ToolBarHelper;


/**
 * Created by wenshao on 2017/3/17.
 * ToolBar
 */

public abstract class ToolBarActivity extends AppCompatActivity {
    private ToolBarHelper mToolBarHelper ;
    public Toolbar toolbar ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setContentView(int layoutResID) {

        mToolBarHelper = new ToolBarHelper(this,layoutResID) ;
        toolbar = mToolBarHelper.getToolBar() ;
        setContentView(mToolBarHelper.getContentView());
        /*把 toolbar 设置到Activity 中*/
        setSupportActionBar(toolbar);
        /*自定义的一些操作*/
        onCreateCustomToolBar(toolbar) ;
    }
    public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

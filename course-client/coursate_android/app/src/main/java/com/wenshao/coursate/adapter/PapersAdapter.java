package com.wenshao.coursate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wenshao.coursate.R;
import com.wenshao.coursate.bean.PaperBean;
import com.wenshao.coursate.listener.PaperListener;

import java.util.List;

/**
 * Created by wenshao on 2018/1/7.
 * 试卷列表
 */

public class PapersAdapter extends BaseAdapter{
    private Context mContext;
    private List<PaperBean>  mDataList;

    private PaperListener paperListener;

    public PapersAdapter(List<PaperBean> dataList,Context context) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public PaperBean getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PaperBean item = getItem(position);
        View view = LayoutInflater.from(mContext).inflate(R.layout.papers_item_view, null);
        TextView papers_item_title =(TextView) view.findViewById(R.id.papers_item_title);

        papers_item_title.setText(item.getTitle());
        papers_item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paperListener!=null) paperListener.onPaperOpenListener();
            }
        });
        return view;
    }
    public void setPaperListener(PaperListener listener){
        paperListener = listener;
    }
}

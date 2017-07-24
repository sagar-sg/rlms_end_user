package com.rlms.adapters;

/**
 * Created by user on 22/2/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rlms.R;
import com.rlms.callback.OnBtnClikListnr;
import com.rlms.constants.Params;
import com.rlms.model.Complaint;
import com.rlms.utils.StringUtils;

import java.util.List;

public class ComplaintsRecyclerAdapter extends RecyclerView.Adapter<ComplaintsRecyclerAdapter.SimpleViewHolder> {
    private List<Complaint> mData;
    private Context context;
    private int viewId = 0;
    private String TAG = "ComplaintsAdapter";
    private OnBtnClikListnr onBtnClikListnr;

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView complainTitleTv, addressTv, detailsTv, pendingTv, getTechDetailsTv;
        ImageView statusIv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            complainTitleTv = (TextView) itemView.findViewById(R.id.complaints_title_name_tv);
            detailsTv = (TextView) itemView.findViewById(R.id.details_tv);
            pendingTv = (TextView) itemView.findViewById(R.id.pending_tv);
            getTechDetailsTv = (TextView) itemView.findViewById(R.id.get_Tech_details_tv);
            statusIv = (ImageView) itemView.findViewById(R.id.status_iv);
        }
    }
    public ComplaintsRecyclerAdapter(Context context, List<Complaint> arr, OnBtnClikListnr onBtnClikListnr) {
        this.mData = arr;
        this.context = context;
        this.onBtnClikListnr = onBtnClikListnr;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.complaints_row_item, parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        if (mData.size() > 0) {
            final Complaint complain = mData.get(position);
//            if(record.isActive()) {
            holder.complainTitleTv.setText("" + complain.getTitle());
            holder.addressTv.setText("" + complain.getLiftAddress());
            holder.pendingTv.setText(StringUtils.getConvertedDate(complain.getRegistrationDate()));
            holder.detailsTv.setText(complain.getRemark());

            Log.d(TAG,"status ="+complain.getStatus()+ " position  = "+position);

            if(complain.getStatus().equalsIgnoreCase(Params.ASSIGNED)
                    || complain.getStatus().equalsIgnoreCase(Params.PENDING)){

                holder.statusIv.setImageResource(R.drawable.red_indicator);

                Log.d(TAG,"status assigned or pending");
                holder.getTechDetailsTv.setAlpha(0.5f);
                holder.getTechDetailsTv.setClickable(false);
                holder.getTechDetailsTv.setOnClickListener(null);

            }else{

                holder.statusIv.setImageResource(R.drawable.green_indicator);

                Log.d(TAG,"status not assigned or pending");
                holder.getTechDetailsTv.setAlpha(1f);
                holder.getTechDetailsTv.setClickable(true);

                holder.getTechDetailsTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onBtnClikListnr!=null){
                            onBtnClikListnr.OnBtnClick(view,position);
                        }
                    }
                });

            }

        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

}
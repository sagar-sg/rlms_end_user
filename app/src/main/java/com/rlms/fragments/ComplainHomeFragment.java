package com.rlms.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rlms.R;
import com.rlms.adapters.ComplaintsRecyclerAdapter;
import com.rlms.callback.OnBtnClikListnr;
import com.rlms.callback.OnFetchItemsFinishCallback;
import com.rlms.customviews.EmptySupportRecyclerView;
import com.rlms.model.Complaint;
import com.rlms.model.MemberRole;
import com.rlms.model.RLMSAPIResponse;
import com.rlms.model.Technician;
import com.rlms.model.TechnicianRequest;
import com.rlms.model.User;
import com.rlms.network.RetrofitBuilder;
import com.rlms.network.webapi.APIGetComplaints;
import com.rlms.network.webapi.APIGetTechnicianDetails;
import com.rlms.utils.Log;
import com.rlms.utils.NetworkUtils;
import com.rlms.utils.Parser;
import com.rlms.utils.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainHomeFragment extends Fragment implements OnFetchItemsFinishCallback {

    @BindView(R.id.recyclerView1)
    EmptySupportRecyclerView complaintsRecyclerView;
    @BindView(R.id.empty_view)
    TextView emptyViewTv;
    private Unbinder unbinder;

    private static final String TAG = "ComplainHomeFragment";
    private ArrayList<Complaint> complaintsArrayList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    ComplaintsRecyclerAdapter mAdapter;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    Log Log = new Log();
    private NetworkUtils mNetworkUtils;

    public ComplainHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getActivity();
        mNetworkUtils = new NetworkUtils(mContext);

        View v = inflater
                .inflate(R.layout.fragment_complaint, container, false);
        unbinder = ButterKnife.bind(this, v);
        gridLayoutManager = new GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false);
        complaintsRecyclerView.setHasFixedSize(true);
        complaintsRecyclerView.setLayoutManager(gridLayoutManager);
        complaintsRecyclerView.setEmptyView(emptyViewTv);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);

        fetchComplaints();
    }

    private void fetchComplaints() {

        mProgressDialog.setMessage(mContext.getString(R.string.loading_complaints));
        mProgressDialog.show();

        APIGetComplaints apiGetComplaints = RetrofitBuilder.getClient().create(APIGetComplaints.class);
        Preferences pref = new Preferences(getActivity());
        User user = pref.getStoredUserDetails();
        int memberId =0;
        if(user!=null) {
            memberId = user.getMemberId();
        }
        Call<RLMSAPIResponse> call = apiGetComplaints.getAllComplaintsFor(new MemberRole(""+memberId));
        Log.err(TAG, "apiGetComplaints send call: " + call.request().url() + " mRetry:");

        call.enqueue(new Callback<RLMSAPIResponse>() {

            @Override
            public void onResponse(Call<RLMSAPIResponse> call, Response<RLMSAPIResponse> response) {

                mProgressDialog.dismiss();

                int statusCode = response.code();
                RLMSAPIResponse RLMSAPIResponse = response.body();

                Log.err(TAG, "send onResponse: statusCode " + statusCode);
                Log.err(TAG, "send onResponse: message " + response.message());
//                Log.d(TAG, "success RLMSAPIResponse = " + RLMSAPIResponse.toString());

                if (statusCode == 200 || statusCode == 201) {

                    Log.d(TAG, "success fecthed complaints");

                    if (RLMSAPIResponse.isStatus()) {

                        if (RLMSAPIResponse.getResponse().length() != 0) {

                            Log.d(TAG, "not != 0 complaints response string = " + RLMSAPIResponse.getResponse());
                            complaintsArrayList = Parser.getParsedComplaintsList(RLMSAPIResponse.getResponse());
                            mAdapter = new ComplaintsRecyclerAdapter(mContext, complaintsArrayList, btnClikListnr);
                            complaintsRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                        }
                    } else {
                        Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.err(TAG, "not 200 something went wrong");
                    Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RLMSAPIResponse> call, Throwable t) {

                mProgressDialog.dismiss();

                Log.d(TAG, "on failure getting complaints value  = " + t.getMessage());
                t.printStackTrace();
                if (mNetworkUtils.isNetworkAvailable()) {

                    Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, getResources().getString(R.string.network_connection_error), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onFetchItemsCallback(List<Complaint> fetchedComplaintsList) {

        Log.d(TAG, "onFetchItemsCallbackcalled fetchedCategoryList size = " + fetchedComplaintsList.size());
        complaintsArrayList = (ArrayList<Complaint>) fetchedComplaintsList;
        mAdapter = new ComplaintsRecyclerAdapter(mContext, complaintsArrayList, btnClikListnr);
        complaintsRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    OnBtnClikListnr btnClikListnr = new OnBtnClikListnr() {

        @Override
        public void OnBtnClick(View v, int position) {
            //TODO Technician Api to fetch technician details.
            fetchtechnicianDetails(""+complaintsArrayList.get(position).getComplaintTechMapId());
        }
    };

    private void fetchtechnicianDetails(String complaintId) {

        mProgressDialog.setMessage(mContext.getString(R.string.loading_complaints));
        mProgressDialog.show();

        APIGetTechnicianDetails apiGetTechnicianDetails = RetrofitBuilder.getClient().create(APIGetTechnicianDetails.class);

        if(complaintId.equalsIgnoreCase("")){
            complaintId = "0";
        }
        Call<RLMSAPIResponse> call = apiGetTechnicianDetails.getTechnicianDetailsFor(new TechnicianRequest(complaintId));
        Log.err(TAG, "apiGetComplaints send call: " + call.request().url() + " mRetry:");

        call.enqueue(new Callback<RLMSAPIResponse>() {

            @Override
            public void onResponse(Call<RLMSAPIResponse> call, Response<RLMSAPIResponse> response) {

                mProgressDialog.dismiss();

                int statusCode = response.code();
                RLMSAPIResponse RLMSAPIResponse = response.body();

                Log.err(TAG, "send onResponse: statusCode " + statusCode);
                Log.err(TAG, "send onResponse: message " + response.message());
//                Log.d(TAG, "success RLMSAPIResponse = " + RLMSAPIResponse.toString());

                if (statusCode == 200 || statusCode == 201) {

                    Log.d(TAG, "success fecthed complaints");

                    if (RLMSAPIResponse.isStatus()) {

                        if (RLMSAPIResponse.getResponse().length() != 0) {

                            Log.d(TAG, "not != 0 techdetails response string = " + RLMSAPIResponse.getResponse());
                            Gson gson = new Gson();
                            try {
                                JSONObject jsonObject = new JSONObject(RLMSAPIResponse.getResponse());
                                Technician technician = gson.fromJson(RLMSAPIResponse.getResponse(),Technician.class);
//                                Toast.makeText(getActivity(),technician.toString(),Toast.LENGTH_LONG).show();
                                showTechnicianDetails(technician);
//                            complaintsArrayList = Parser.getParsedComplaintsList(RLMSAPIResponse.getResponse());
//                            mAdapter = new ComplaintsRecyclerAdapter(mContext, complaintsArrayList, btnClikListnr);
//                            complaintsRecyclerView.setAdapter(mAdapter);
//                            mAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    } else {
                        Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.err(TAG, "not 200 something went wrong");
                    Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RLMSAPIResponse> call, Throwable t) {

                mProgressDialog.dismiss();

                Log.d(TAG, "on failure getting techdetails value  = " + t.getMessage());
                t.printStackTrace();
                if (mNetworkUtils.isNetworkAvailable()) {

                    Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, getResources().getString(R.string.network_connection_error), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void showTechnicianDetails(Technician technician){
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.technician_details,null);
        alertDialog.setView(layout);
        final TextView nameTv = (TextView) layout.findViewById(R.id.name_tv);
        final TextView emailTv = (TextView) layout.findViewById(R.id.email_tv);
        final TextView contactTv = (TextView) layout.findViewById(R.id.contact_tv);
        final Button okBtn = (Button)layout.findViewById(R.id.ok_btn);

        nameTv.setText(""+technician.getFirstName()+" "+technician.getLastName());
        emailTv.setText(""+technician.getEmailId());
        contactTv.setText(""+technician.getContactNumber());
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
        alertDialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

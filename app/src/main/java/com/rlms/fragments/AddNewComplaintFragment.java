package com.rlms.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rlms.R;
import com.rlms.activities.MainActivity;
import com.rlms.adapters.ComplaintsRecyclerAdapter;
import com.rlms.adapters.SpinnerCustomAdapter;
import com.rlms.callback.OnFetchItemsFinishCallback;
import com.rlms.callback.RecyclerViewItemClickListener;
import com.rlms.constants.Params;
import com.rlms.customviews.EmptySupportRecyclerView;
import com.rlms.model.Complaint;
import com.rlms.model.LiftDetails;
import com.rlms.model.RLMSAPIResponse;
import com.rlms.model.RegisterComplaint;
import com.rlms.model.SpinnerModel;
import com.rlms.model.User;
import com.rlms.model.UserRole;
import com.rlms.network.RetrofitBuilder;
import com.rlms.network.webapi.APIGetAllLifts;
import com.rlms.network.webapi.APIGetComplaints;
import com.rlms.network.webapi.APIRegisterComplaint;
import com.rlms.network.webapi.APIToMarkAsResolved;
import com.rlms.utils.Log;
import com.rlms.utils.NetworkUtils;
import com.rlms.utils.Parser;
import com.rlms.utils.Preferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewComplaintFragment extends Fragment {

    //    @BindView(R.id.empty_view)
//    TextView emptyViewTv;
    private Unbinder unbinder;
    @BindView(R.id.lift_spinner)
    Spinner spinnerLift;
    @BindView(R.id.title_spinner)
    Spinner spinnerTitle;
    @BindView(R.id.description_edit_tv)
    EditText descriptionEditTv;
    @BindView(R.id.proceed_btn)
    Button proceedBtn;
    List<LiftDetails> liftDetailsList;
    String liftResult = "";
    int selectedLiftCustomerMapId = 0;

    private static final String TAG = "AddNewComplaintFragment";
    private Context mContext;
    private ProgressDialog mProgressDialog;
    com.rlms.utils.Log Log = new Log();
    private NetworkUtils mNetworkUtils;
    private String liftSpinnerStr = "";
    private String complaintTitleStr = "";

    public AddNewComplaintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getActivity();
        mNetworkUtils = new NetworkUtils(mContext);

        View v = inflater
                .inflate(R.layout.activity_register_complaint, container, false);
        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
//
        Preferences pref = new Preferences(getActivity());
        User user = pref.getStoredUserDetails();
//        fetchComplaints();
        getAllLiftDetails(""+user.getMemberId());


        proceedBtn.setOnClickListener(onProceedClicked);

    }

    View.OnClickListener onProceedClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Log.d(TAG, "clicked on proceed liftSpinnerStr = " + liftSpinnerStr + " complaintTitleStr = " + complaintTitleStr);
            if (validateRequiredFields()) {
                callRegisterNewComplaint();
            } else {

                Toast.makeText(mContext, "" + getString(R.string.check_all_fields), Toast.LENGTH_SHORT).show();

            }

        }
    };

    /*
    * Check all required fields are not empty and not selected
    * */
    private boolean validateRequiredFields() {

        if (descriptionEditTv.getText().toString().trim().length() == 0) {
            Toast.makeText(mContext, "" + getString(R.string.enter_description), Toast.LENGTH_SHORT).show();

            return false;
        } /*else if (complaintTitleStr.length() == 0 || complaintTitleStr.equalsIgnoreCase(getString(R.string.select_title))) {
            Toast.makeText(mContext, "" + getString(R.string.select_title), Toast.LENGTH_SHORT).show();

            return false;
        }*/ else if (liftSpinnerStr.length() == 0 || liftSpinnerStr.equalsIgnoreCase(getString(R.string.select_lift))) {
            Toast.makeText(mContext, "" + getString(R.string.select_lift), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    /*
    * Call API to add new complaint after button is clicked
    * */
    private void callRegisterNewComplaint() {

        mProgressDialog.setMessage(mContext.getString(R.string.adding_complaint));
        mProgressDialog.show();

        APIRegisterComplaint apiRegisterComplaint = RetrofitBuilder.getClient().create(APIRegisterComplaint.class);

        // TODO Removed hard coded values
        Preferences pref = new Preferences(getActivity());
        User user = pref.getStoredUserDetails();
        int memberId = 0,liftCustMapId =0;
        if (user != null) {
            memberId = user.getMemberId();
//            liftCustMapId = user.getBranchCustoMapId();

        }
        RegisterComplaint registerComplaint = new RegisterComplaint();
        registerComplaint.setMemberId(memberId);
        registerComplaint.setLiftCustomerMapId(selectedLiftCustomerMapId);
        registerComplaint.setRegistrationType(30);
        registerComplaint.setComplaintsTitle(""+complaintTitleStr);
        registerComplaint.setComplaintsRemark("" + descriptionEditTv.getText().toString().trim());

        Log.d(TAG, "sent param string = " + registerComplaint.toString());

        Call<RLMSAPIResponse> call = apiRegisterComplaint.validateAndRegisterComplaint(registerComplaint);
        Log.err(TAG, "apiRegisterComplaint send call: " + call.request().url() + " mRetry:");

        call.enqueue(new Callback<RLMSAPIResponse>() {

            @Override
            public void onResponse(Call<RLMSAPIResponse> call, Response<RLMSAPIResponse> response) {

                mProgressDialog.dismiss();

                int statusCode = response.code();
                RLMSAPIResponse rlmsapiResponse = response.body();

                Log.err(TAG, "apiRegisterComplaint onResponse: statusCode " + statusCode);
                Log.err(TAG, "apiRegisterComplaint onResponse: message " + response.message());

                if (statusCode == 200 || statusCode == 201) {

                    if (rlmsapiResponse != null) {
                        Log.d(TAG, "string rlmsapiResponse = " + rlmsapiResponse.toString());
                        if (rlmsapiResponse.isStatus()) {
                            Log.d(TAG, "success added complaint");
                            Toast.makeText(mContext, "" + getString(R.string.successfully_added_complaint), Toast.LENGTH_SHORT).show();
                        } else {
                            Log.err(TAG, "flag false");
                            Toast.makeText(mContext, "" + rlmsapiResponse.getResponse(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.err(TAG, "flag false");
                        Toast.makeText(mContext, "" + getString(R.string.failed_to_add_complaint), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.err(TAG, "apiRegisterComplaint not 200 something went wrong");
                    Toast.makeText(mContext, "" + getString(R.string.failed_to_add_complaint), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RLMSAPIResponse> call, Throwable t) {

                mProgressDialog.dismiss();

                Log.d(TAG, "apiRegisterComplaint failure adding complaint value  = " + t.getMessage());
                t.printStackTrace();
                if (mNetworkUtils.isNetworkAvailable()) {

                    Toast.makeText(mContext, "" + getString(R.string.failed_to_add_complaint), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, getResources().getString(R.string.network_connection_error), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    private void setLiftSpinnerWithData() {

        Log.d(TAG,"setLiftSpinnerWithData called");

        liftDetailsList = new ArrayList<>();
        // Spinner Drop down elements
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<LiftDetails>>() {
        }.getType();
        liftDetailsList = gson.fromJson(liftResult, collectionType);
        Log.d(TAG,"liftDetailsList size  = "+liftDetailsList.size());
        ArrayList<SpinnerModel> list = new ArrayList<>();
        final String[] liftArray = getResources().getStringArray(R.array.array_lift);

        if(liftDetailsList!=null) {
            if(liftDetailsList.size()>0) {
                SpinnerModel modelfirst = new SpinnerModel();
                modelfirst.setTitle("Select Lift");
                list.clear();
                list.add(modelfirst);
                for (int i = 0; i < liftDetailsList.size(); i++) {
                    SpinnerModel model = new SpinnerModel();
                    model.setTitle(liftDetailsList.get(i).getLiftNumber());
                    list.add(model);
                }
            }
        }

        if(list.size()==0){
            SpinnerModel model = new SpinnerModel();
            model.setTitle("Default");
            list.clear();
            list.add(model);
            spinnerLift.setEnabled(false);
            Log.d(TAG,"list.size()==0");
        }else {
            spinnerLift.setEnabled(true);
            Log.d(TAG,"list.size() is not 0");
        }
        // Create custom adapter object ( see below CustomAdapter.java )
        SpinnerCustomAdapter adapter1 = new SpinnerCustomAdapter(getActivity(), R.layout.spinner_rows, list);

        // Set adapter to spinner
        spinnerLift.setAdapter(adapter1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLift.setSelection(0);

        // Spinner click listener
        spinnerLift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                Log.d(TAG, "onItemSelected pos = " + position);
                liftSpinnerStr = liftArray[position];
                if(liftDetailsList!=null && liftDetailsList.size()>0){
                    if(position>0) {
                        selectedLiftCustomerMapId = liftDetailsList.get(position - 1).getLiftCustomerMapId();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Log.d(TAG, "onNothingSelected");
                liftSpinnerStr = "";
            }
        });

    }

    private void setTitleSpinnerWithData() {

        // Spinner Drop down elements
        ArrayList<SpinnerModel> list = new ArrayList<>();
        final String[] titleArray = getResources().getStringArray(R.array.array_complaint_title);

        for (int i = 0; i < titleArray.length; i++) {
            SpinnerModel model = new SpinnerModel();
            model.setTitle(titleArray[i]);
            list.add(model);
        }

        // Create custom adapter object ( see below CustomAdapter.java )
        SpinnerCustomAdapter adapter1 = new SpinnerCustomAdapter(getActivity(), R.layout.spinner_rows, list);

        // Set adapter to spinner
        spinnerTitle.setAdapter(adapter1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTitle.setSelection(0);

        // Spinner click listener
        spinnerTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                Log.d(TAG, "onItemSelected pos = " + position);
                complaintTitleStr = titleArray[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Log.d(TAG, "onNothingSelected");
                complaintTitleStr = "";
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void getAllLiftDetails(String memberId) {

//        mProgressDialog.setMessage(mContext.getString(R.string.loading_complaints));
//        mProgressDialog.show();

        APIGetAllLifts apiGetAllLifts = RetrofitBuilder.getClient().create(APIGetAllLifts.class);

        Call<RLMSAPIResponse> call = apiGetAllLifts.getAllLiftsFor(new UserRole(memberId));
        android.util.Log.e(TAG, "apiGetAllLifts send call: " + call.request().url() + " mRetry:");

        call.enqueue(new Callback<RLMSAPIResponse>() {

            @Override
            public void onResponse(Call<RLMSAPIResponse> call, Response<RLMSAPIResponse> response) {

//                mProgressDialog.dismiss();

                int statusCode = response.code();
                RLMSAPIResponse RLMSAPIResponse = response.body();

                android.util.Log.e(TAG, "send onResponse: statusCode " + statusCode);
                android.util.Log.e(TAG, "send onResponse: message " + response.message());
                android.util.Log.d(TAG, "success RLMSAPIResponse = " + RLMSAPIResponse.toString());

                if (statusCode == 200 || statusCode == 201) {

                    android.util.Log.d(TAG, "success fecthed Lifts");
//                    Toast.makeText(mContext, "" + getString(R.string.successfully_fetched_complaints), Toast.LENGTH_SHORT).show();

                    if(RLMSAPIResponse.isStatus()) {

                        if (RLMSAPIResponse.getResponse().length() != 0) {

                            android.util.Log.d(TAG, "not != 0 complaints response string = " + RLMSAPIResponse.getResponse());
                            liftResult = RLMSAPIResponse.getResponse();
                            setLiftSpinnerWithData();
                            setTitleSpinnerWithData();
                        }
                    }else{
                        Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    android.util.Log.e(TAG, "not 200 something went wrong");
                    Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RLMSAPIResponse> call, Throwable t) {

//                mProgressDialog.dismiss();

                android.util.Log.d(TAG, "on failure getting complaints value  = " + t.getMessage());
                t.printStackTrace();
                setLiftSpinnerWithData();
//                setTitleSpinnerWithData();
//                if (mNetworkUtils.isNetworkAvailable()) {

                Toast.makeText(mContext, "" + getString(R.string.try_again), Toast.LENGTH_SHORT).show();
//                } else {
                Toast.makeText(mContext, getResources().getString(R.string.network_connection_error), Toast.LENGTH_SHORT).show();

//                }
            }

        });

    }

}


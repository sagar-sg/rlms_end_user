package com.rlms.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.WindowManager;

import com.rlms.R;
import com.rlms.callback.OnFetchItemsFinishCallback;
import com.rlms.model.Complaint;
import com.rlms.model.Complaints;

import java.util.ArrayList;

public class FetchDataAsyncTask extends AsyncTask<Void,Void,Void> {

    Context cxt;
    ArrayList<Complaint> complaintsArrayList = new ArrayList<>();
    OnFetchItemsFinishCallback onFetchedAdsCallback;
    String TAG = "FetchAdsAysnTask";
    ProgressDialog progDialog;

    public FetchDataAsyncTask(Context cxt, OnFetchItemsFinishCallback onFetchedAdsCallback){

        this.cxt = cxt;
        this.onFetchedAdsCallback = onFetchedAdsCallback;

    }

    @Override
    protected void onPreExecute() {

        // time out for internet checking
        // setTimeOut(8000);

        progDialog = ProgressDialog.show(cxt, "", "Loading...");
        // progDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//			progDialog.setContentView(R.layout.login_loading_dialog);
        // progDialog.getWindow().setBackgroundDrawable(new
        // ColorDrawable(0));

        WindowManager.LayoutParams WMLP = progDialog.getWindow()
                .getAttributes();
        WMLP.dimAmount = (float) 0.8;
        WMLP.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
//			mMsgTextView = (TextView) progDialog.findViewById(R.id.tempTV);
//			mMsgTextView.setText(getString(R.string.login_user_signing_in));
//			mMsgTextView.setVisibility(View.VISIBLE);
        progDialog.getWindow().setAttributes(WMLP);
        progDialog.show();
        progDialog.setCanceledOnTouchOutside(false);
        progDialog.setCancelable(false);

        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        Complaint complaints = new Complaint();
        complaints.setComplaintId(1);
        complaints.setTitle("Hospital");
        complaintsArrayList.add(complaints);

         complaints = new Complaint();
        complaints.setComplaintId(2);
        complaints.setTitle("ATM");
        complaintsArrayList.add(complaints);

         complaints = new Complaint();
        complaints.setComplaintId(3);
        complaints.setTitle("Hotel");
        complaintsArrayList.add(complaints);

         complaints = new Complaint();
        complaints.setComplaintId(4);
        complaints.setTitle("Electricians");
        complaintsArrayList.add(complaints);

         complaints = new Complaint();
        complaints.setComplaintId(5);
        complaints.setTitle("Water Tank");
        complaintsArrayList.add(complaints);

        complaints = new Complaint();
        complaints.setComplaintId(6);
        complaints.setTitle("Petrol Pump");
        complaintsArrayList.add(complaints);

        complaints = new Complaint();
        complaints.setComplaintId(7);
        complaints.setTitle("Plumber");
        complaintsArrayList.add(complaints);

        complaints = new Complaint();
        complaints.setComplaintId(8);
        complaints.setTitle("Gym");
        complaintsArrayList.add(complaints);

        complaints = new Complaint();
        complaints.setComplaintId(9);
        complaints.setTitle("Garage");
        complaintsArrayList.add(complaints);

        complaints = new Complaint();
        complaints.setComplaintId(10);
        complaints.setTitle("Bakery");
        complaintsArrayList.add(complaints);

//        fetchedAdsList.add("Modi Attends Wedding of Bhajji");
//        fetchedAdsList.add("Messi Now Brand Ambassidor");
//        fetchedAdsList.add("SA beats India in ODI series");
//        fetchedAdsList.add("Plan Crash in Russia");

//        try {
//
//            JSONObject jsonObject = WebConnector.getJSONResponse(WebService.BASE_FETCH_ADS_URL + WebService.FILE_ADS);
//
//            fetchedAdsList = new CustomAd().getParsedAdsList(jsonObject);
//            Log.d(TAG, "fetched ads list size = " + fetchedAdsList.size());
//
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        progDialog.dismiss();
        onFetchedAdsCallback.onFetchItemsCallback(complaintsArrayList);
//        CustomToast.showToast((Activity)cxt,"Finished asyn task display ads now", Toast.LENGTH_SHORT);

    }
}

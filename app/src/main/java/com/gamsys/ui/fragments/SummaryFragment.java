package com.gamsys.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gamsys.localdb.DbHandler;
import com.gamsys.localdb.models.Log;
import com.gamsys.localdb.models.Logs;
import com.gamsys.localdb.models.TreatmentLog;
import com.gamsys.R;
import com.gamsys.localdb.AppLocalDataOperations;
import com.gamsys.localdb.models.TreatmentLog;
import com.gamsys.ui.adapters.TreatmentLogsAdapter;

import java.util.ArrayList;


public class SummaryFragment extends Fragment {

    public SummaryFragment() {
        // Required empty public constructor
    }

    private RecyclerView rvTreatmentLogs;
    private TextView tvMessage;
    private ArrayList<Logs> mTreatmentLogs;
    private DbHandler dbHandler;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_summary, container, false);

        tvMessage= rootView.findViewById(R.id.tv_logs_message);

        rvTreatmentLogs= rootView.findViewById(R.id.rv_logs);

        rvTreatmentLogs.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onResume() {
            super.onResume();
            mTreatmentLogs = new ArrayList<>();

        dbHandler = new DbHandler(getContext());
        final String userId = dbHandler.addUser("true").getUser_id();
        mTreatmentLogs = (ArrayList<Logs>) dbHandler.getAllUsers(userId);
        rvTreatmentLogs.setAdapter(new TreatmentLogsAdapter(mTreatmentLogs, getActivity()));
            populateTreatmentLogs();

    }

    private void populateTreatmentLogs() {
        try {
            final String username = dbHandler.addUser("true").getUser_id();
            mTreatmentLogs= (ArrayList<Logs>) dbHandler.getInstance(getActivity()).getAllUsers(username);

            if (mTreatmentLogs.isEmpty()){
                tvMessage.setVisibility(View.VISIBLE);
                rvTreatmentLogs.setVisibility(View.GONE);
            }else{
                tvMessage.setVisibility(View.GONE);
                rvTreatmentLogs.setVisibility(View.VISIBLE);
                rvTreatmentLogs.setAdapter(new TreatmentLogsAdapter(mTreatmentLogs, getActivity()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SummaryFragment newInstance() {
        return new SummaryFragment();
    }
}

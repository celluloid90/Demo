package com.shahin.demo.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.shahin.demo.Adapter.DashboardImageRecycleAdapter;
import com.shahin.demo.Data.Constants;
import com.shahin.demo.Data.DashboardDataModel;
import com.shahin.demo.R;
import com.shahin.demo.Util.Utils;

import java.util.ArrayList;

/**
 * Created by Shahin on 9/18/2017.
 */
public class DashboardFragment extends Fragment {

    ArrayList<DashboardDataModel> mDashboardDataModelList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private DashboardDataModel mDashboardDataModel = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View actionBarView = LayoutInflater.from(getActivity()).inflate(R.layout.menu_buttons, null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(actionBarView);

        ImageButton cameraBtn = (ImageButton) actionBarView.findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, Constants.CAMERA_REQUEST);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Utils.saveImageToExternalStorage(getActivity(), photo);

            mDashboardDataModel = new DashboardDataModel();
            mDashboardDataModel.setImage(photo);
            openDialogForImageInfo();
        }
    }

    private void openDialogForImageInfo() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity());
        View mView = layoutInflaterAndroid.inflate(R.layout.image_info_input, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
        alertDialogBuilderUserInput.setView(mView);

        final EditText imageTitleEditText = (EditText) mView.findViewById(R.id.et_image_title);
        final EditText imageDescriptionEditText = (EditText) mView.findViewById(R.id.et_image_title);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(getActivity().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        mDashboardDataModel.setImageTitle(imageTitleEditText.getText().toString());
                        mDashboardDataModel.setImageDescription(imageDescriptionEditText.getText().toString());
                        mDashboardDataModelList.add(mDashboardDataModel);

                        mRecyclerView.setHasFixedSize(true);
                        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
                        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        if (mDashboardDataModelList.size() > 0 & mRecyclerView != null) {
                            mRecyclerView.setAdapter(new DashboardImageRecycleAdapter(getActivity(), mDashboardDataModelList));
                        }
                        mRecyclerView.setLayoutManager(MyLayoutManager);
                    }
                })

                .setNegativeButton(getActivity().getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }
}

package com.bodychart;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bodychart.databinding.ActivityMainBinding;
import com.bodychart.databinding.DialogBodyBinding;
import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Sunasara Mohsinali
 * @Date: 14-Oct-22
 */
public class BodyChartActivity extends AppCompatActivity {
    private final ArrayList<BodyChartInfo> mBodyChartInfo = new ArrayList<>();
    private final List<String> mTempBodyInfo = new ArrayList<>();
    private ActivityMainBinding binding;
    private Context mContext;
    private VectorDrawableCompat.VFullPath vFullPath;
    private BodyPartAdapter mBodyPartAdapter;
    private VectorChildFinder childFinder;
    private boolean isAdded = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mContext = this;

        intView();
    }

    private void intView() {

        binding.icNotifications.setOnPathClickListener(richPath -> {
            try {
                L.showError(richPath.getName());
                L.showToast(mContext, richPath.getName());

                if (!isExitSelectedPartInArray(richPath.getName())) {
                    mTempBodyInfo.add(richPath.getName());
                    setPartSelected(richPath.getName());

                }
            } catch (NullPointerException e) {
                L.showError(e.toString());
            }
        });
    }

    private void setPartSelected(String name) {
        childFinder = new VectorChildFinder(this, R.drawable.body, binding.icNotifications);

        for (int i = 0; i < mTempBodyInfo.size(); i++) {
            vFullPath =
                    childFinder.findPathByName(mTempBodyInfo.get(i));
            vFullPath.setFillColor(Color.RED);
        }
        showBottomSheetDialogForBodyChart(name);
    }

    private boolean isExitSelectedPartInArray(String partName) {
        boolean isExit = false;
        for (int i = 0; i < mBodyChartInfo.size(); i++) {
            if (mBodyChartInfo.get(i).getBodyPartName().equalsIgnoreCase(partName)) {
                isExit = true;
            }
        }
        return isExit;
    }

    private void showBottomSheetDialogForBodyChart(String name) {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext, R.style.BottomSheetDialog);
        DialogBodyBinding dialogBodyBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R
                        .layout.dialog_body,
                null, false);
        mBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        dialogBodyBinding.rvBodyPart.setNestedScrollingEnabled(false);
        mBottomSheetDialog.setContentView(dialogBodyBinding.getRoot());

        RVLayoutManager.setVerticalLayoutManager(mContext, dialogBodyBinding.rvBodyPart);
        addOnSelectedBodyPart(dialogBodyBinding);

        dialogBodyBinding.btnSelect.setOnClickListener(view -> {
            if (!isExitSelectedPartInArray(name)) {
                dialogBodyBinding.rvBodyPart.setVisibility(View.VISIBLE);
                BodyChartInfo bodyChartInfo = new BodyChartInfo();
                bodyChartInfo.setBodyPartName(name);
                bodyChartInfo.setNumberOfPain(String.valueOf(dialogBodyBinding.seekbar.getProgress()));
                mBodyChartInfo.add(bodyChartInfo);
                mBodyPartAdapter.notifyDataSetChanged();
                L.showError(mBodyChartInfo.toString());
                isAdded = true;
                //  mBottomSheetDialog.dismiss();
            }

        });


        mBodyPartAdapter = new BodyPartAdapter(mContext, mBodyChartInfo, bodyChartInfo -> {
            removePartInToList(bodyChartInfo.getBodyPartName());
        });
        dialogBodyBinding.rvBodyPart.setAdapter(mBodyPartAdapter);

        dialogBodyBinding.btnCancel.setOnClickListener(view -> {
            removePartInToList(name);
            mBottomSheetDialog.dismiss();
        });
        L.showError(String.valueOf(dialogBodyBinding.seekbar.getProgress()));
        dialogBodyBinding.imgClose.setOnClickListener(v -> {
                    L.showError(String.valueOf(dialogBodyBinding.seekbar.getProgress()));
                    mBottomSheetDialog.dismiss();
                }
        );
        mBottomSheetDialog.setOnDismissListener(dialog ->
                {
                    if (!isAdded) {
                        setRemoveSelectedBodyPart(name);
                    }
                    isAdded=false;
                }
        );

        mBottomSheetDialog.show();
    }


    private void addOnSelectedBodyPart(DialogBodyBinding dialogBodyBinding) {
        mBodyPartAdapter = new BodyPartAdapter(mContext, mBodyChartInfo, bodyChartInfo -> {
            removePartInToList(bodyChartInfo.getBodyPartName());
        });
        dialogBodyBinding.rvBodyPart.setAdapter(mBodyPartAdapter);
    }


    private void removePartInToList(String bodyName) {
        for (int i = 0; i < mBodyChartInfo.size(); i++) {
            if (mBodyChartInfo.get(i).getBodyPartName().equalsIgnoreCase(bodyName)) {
                mBodyChartInfo.remove(i);
                mBodyPartAdapter.notifyDataSetChanged();
            }
        }

        for (int i = 0; i < mTempBodyInfo.size(); i++) {
            if (bodyName.equalsIgnoreCase(mTempBodyInfo.get(i))) {
                mTempBodyInfo.remove(i);
            }
        }

        setRemoveSelectedBodyPart(bodyName);
    }

    private void setRemoveSelectedBodyPart(String bodyName) {

        childFinder = new VectorChildFinder(this, R.drawable.body, binding.icNotifications);
        vFullPath =
                childFinder.findPathByName(bodyName);
        vFullPath.setFillColor(Color.GRAY);

        childFinder = new VectorChildFinder(this, R.drawable.body, binding.icNotifications);
        for (int i = 0; i < mBodyChartInfo.size(); i++) {
            vFullPath =
                    childFinder.findPathByName(mBodyChartInfo.get(i).getBodyPartName());
            vFullPath.setFillColor(Color.RED);
        }

        for (int i = 0; i < mTempBodyInfo.size(); i++) {
            if (bodyName.equalsIgnoreCase(mTempBodyInfo.get(i))) {
                mTempBodyInfo.remove(i);
            }
        }
    }


}

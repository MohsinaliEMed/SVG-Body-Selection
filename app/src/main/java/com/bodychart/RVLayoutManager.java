package com.bodychart;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RVLayoutManager {
    public static void setGridLayoutManager(Context context, RecyclerView recyclerView, int count) {
        recyclerView.setLayoutManager(new GridLayoutManager(context, count));
    }

    public static void setVerticalLayoutManager(Context context, RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public static void setHorizaontaLayoutManager(Context context, RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}


package com.supervideo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.supervideo.MainActivity;
import com.supervideo.R;
import com.supervideo.common.GetTabListResult;
import com.supervideo.common.Utils;
import com.supervideo.common.tablist;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.supervideo.common.Settings.loginUrl;

/**
 * Created by Gacyou on 2017/10/17.
 */

public class AppStart  extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static Context mContext;
    private static Activity mActivity;

    LinearLayout loadingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstart);

        mContext = this;
        mActivity = this;
        loadingView = (LinearLayout)findViewById(R.id.loading);

        String url = loginUrl ;
        new GetTabListTask().execute(url);
    }

    private class GetTabListTask extends AsyncTask<String, Integer, GetTabListResult> {

        @Override
        protected  void onPreExecute(){
            loadingView.setVisibility(View.VISIBLE);}

        @Override
        protected GetTabListResult doInBackground(String... params) {
            GetTabListResult r = new GetTabListResult();

            try {
                Thread.sleep(1000);
                String retSrc = Utils.getJson(params[0]);
                JSONObject json = new JSONObject(retSrc);


                JSONArray t = new JSONArray(json.getString("tablist"));
                List<tablist> tmp = new ArrayList<tablist>();

                for (int i = 0; i < t.length(); ++i) {
                    tablist tl = new tablist();
                    tl.Id = t.getJSONObject(i).getInt("Id");
                    tl.MenuId = t.getJSONObject(i).getInt("MenuId");
                    tl.TabName = t.getJSONObject(i).getString("TabName");
                    tmp.add(tl);
                }

                r.ReturnMsgNo = json.getInt("ReturnMsgNo");
                r.ReturnMsg = json.getString("ReturnMsg");
                r.t = tmp;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return r;

        }

        @Override
        protected void onPostExecute(GetTabListResult result) {

            loadingView.setVisibility(View.GONE);

            if (result.ReturnMsgNo != 1) {
                new AlertDialog.Builder(mContext)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle("Error")
                        .setCancelable(false)
                        .setMessage("Error")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            } else {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("GetTabListResult", result);
                mActivity.startActivity(intent);

                finish();
            }
        }

    }

}

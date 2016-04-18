package com.mj.core.update;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.mj.core.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    private UpdateManager updateMan;
    private ProgressDialog updateProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //updateMan = new UpdateManager(this, appUpdateCb);
        //updateMan.checkUpdate();
    }
/*
    UpdateManager.UpdateCallback appUpdateCb = new UpdateManager.UpdateCallback()
    {

        public void downloadProgressChanged(int progress) {
            if (updateProgressDialog != null
                    && updateProgressDialog.isShowing()) {
                updateProgressDialog.setProgress(progress);
            }

        }

        public void downloadCompleted(Boolean sucess, CharSequence errorMsg) {
            if (updateProgressDialog != null
                    && updateProgressDialog.isShowing()) {
                updateProgressDialog.dismiss();
            }
            if (sucess) {
                updateMan.update();
            } else {
                DialogHelper.Confirm(MainActivity.this,
                        R.string.dialog_error_title,
                        R.string.dialog_downfailed_msg,
                        R.string.dialog_downfailed_btnnext,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                updateMan.downloadPackage();

                            }
                        }, R.string.dialog_downfailed_btnnext, null);
            }
        }

        public void downloadCanceled()
        {
            // TODO Auto-generated method stub

        }

        public void checkUpdateCompleted(Boolean hasUpdate,
                                         CharSequence updateInfo) {
            if (hasUpdate) {
                DialogHelper.Confirm(MainActivity.this,
                        getText(R.string.dialog_update_title),
                        getText(R.string.dialog_update_msg).toString()
                                +updateInfo+
                                getText(R.string.dialog_update_msg2).toString(),
                        getText(R.string.dialog_update_btnupdate),
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                updateProgressDialog = new ProgressDialog(
                                        MainActivity.this);
                                updateProgressDialog
                                        .setMessage(getText(R.string.dialog_downloading_msg));
                                updateProgressDialog.setIndeterminate(false);
                                updateProgressDialog
                                        .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                updateProgressDialog.setMax(100);
                                updateProgressDialog.setProgress(0);
                                updateProgressDialog.show();

                                updateMan.downloadPackage();
                            }
                        },getText( R.string.dialog_update_btnnext), null);
            }

        }
    };
*/
}

package com.mj.core.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.mj.core.R;
public class DialogUtils extends Activity {

    public static ProgressDialog showProgress(Context context, String message, boolean modal) {
        ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(!modal);
        mProgressDialog.show();
        return mProgressDialog;
    }

    /**
     * 显示带OK按钮的对话框
     *
     * @param context
     * @param title
     * @param message
     */
    public static void show(Context context, String title, String message) {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
        localBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        context.getResources().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface mDialogInterface, int mInt) {
                                mDialogInterface.cancel();
                            }
                        })
                .create()
                .show();
    }

    public static Dialog createDialog(Context context, int layoutId, int themeId) {
        Dialog ret;
        ret = new Dialog(context, themeId);
        ret.setContentView(layoutId);
        return ret;
    }
}


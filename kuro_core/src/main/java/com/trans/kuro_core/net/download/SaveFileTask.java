package com.trans.kuro_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.trans.kuro_core.app.Kuro;
import com.trans.kuro_core.net.callback.IRequst;
import com.trans.kuro_core.net.callback.ISuccess;
import com.trans.kuro_core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import androidx.core.app.NavUtils;
import okhttp3.ResponseBody;

/**
*@author TRS透明
*Created on 2019/8/15
*function : 由于download是Streaming边下边写所以一定要异步
*/
public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private final IRequst REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequst REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir= (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body= (ResponseBody) params[2];
        final String name= (String) params[3];

        final InputStream is=body.byteStream();
        if (downloadDir==null||downloadDir.equals("")){
            downloadDir ="down_loads";
        }
        if (extension ==null||extension.equals("")){
            extension="";
        }
        if (name==null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST!=null){
            REQUEST.onRequstEnd();
        }

        autoInstallApk(file);
    }

    /**
     * 如果直接是一个apk文件，就直接安装
     * @param file
     */
    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install=new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Kuro.getApplication().startActivity(install);
        }
    }
}

package com.trans.kuro_core.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.trans.kuro_core.R;
import com.trans.kuro_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatDialog;

public class KuroLoader {

    private static final int LOAD_SIZE_SCALE=8;
    private static final int LOAD_OFFSET_SCALE=10;

    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();

    private static final String DEFAULT_LOADER=LoaderStyle.BallClipRotateIndicator.name();//loader默认样式

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }
    public static void showLoading(Context context,String type){

        final AppCompatDialog dialog=new AppCompatDialog(context,R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.create(type,context);

        dialog.setContentView(avLoadingIndicatorView);

        int deviceWeight=DimenUtil.getScreenWidth();
        int deviceHeight=DimenUtil.getScreenHeight();

        final Window dialogWindow= dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams lp=dialogWindow.getAttributes();
            lp.width=deviceWeight/LOAD_SIZE_SCALE;
            lp.height=deviceHeight/LOAD_SIZE_SCALE;
            lp.height=lp.height+deviceHeight/LOAD_OFFSET_SCALE;
            lp.gravity=Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }



    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS){
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}

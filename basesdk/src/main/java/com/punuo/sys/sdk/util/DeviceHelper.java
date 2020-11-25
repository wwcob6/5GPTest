package com.punuo.sys.sdk.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.punuo.sys.sdk.PnApplication;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by han.chen.
 * Date on 2019/4/2.
 **/
public class DeviceHelper {

    /**
     * 判断当前应用是否是debug状态
     */
    public static boolean isApkInDebug() {
        try {
            ApplicationInfo info = PnApplication.getInstance().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getVersionName() {
        PackageManager packageManager = PnApplication.getInstance().getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(PnApplication.getInstance().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packInfo == null) {
            return "获取版本失败";
        }
        return "当前版本:v" + packInfo.versionName;
    }

    // 是否安装微信
    public static boolean isWechatInstalled(Context context, String wxAppId) {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context.getApplicationContext(), wxAppId, false);
        wxapi.registerApp(wxAppId);
        boolean isInstalled = wxapi.isWXAppInstalled();
        wxapi.detach();

        //出厂预装了微信，如果被禁用了，则也返回false。
        boolean isEnable = true;
        try {
            PackageManager manager = context.getPackageManager();
            if (manager != null && manager.getApplicationEnabledSetting("com.tencent.mm")
                    == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER) {
                isEnable = false;
            }
        } catch (Exception e) {

        }
        return isInstalled & isEnable;
    }
}

package net.afpro.fakegpsxposedmodule.hooks;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class WifiInfoHook extends XC_MethodHook {
  public void hookOn(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", loadPackageParam.classLoader, "getMacAddress", this);
    XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", loadPackageParam.classLoader, "getSSID", this);
    XposedHelpers.findAndHookMethod("android.net.wifi.WifiInfo", loadPackageParam.classLoader, "getBSSID", this);
  }
}

package net.afpro.fakegpsxposedmodule.hooks;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class WifiManagerHook extends BaseHook {
  public WifiManagerHook() {
    super("android.net.wifi.WifiManager");
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getScanResults");
    hook("getWifiState");
    hook("isWifiEnabled");
  }

  private void before_getScanResults(MethodHookParam param) throws Throwable {

  }

  private void before_getWifiState(MethodHookParam param) throws Throwable {
  }

  private void before_isWifiEnabled(MethodHookParam param) throws Throwable {
  }
}

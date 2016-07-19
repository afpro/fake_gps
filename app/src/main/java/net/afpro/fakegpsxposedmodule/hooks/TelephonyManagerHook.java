package net.afpro.fakegpsxposedmodule.hooks;

import android.os.Build;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TelephonyManagerHook extends BaseHook {
  public TelephonyManagerHook() {
    super("android.telephony.TelephonyManager");
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getNetworkOperatorName");
    hook("getSimOperatorName");
    hook("getSimOperator");
    hook("getNetworkOperator");
    hook("getSimCountryIso");
    hook("getNetworkCountryIso");
    if (Build.VERSION.SDK_INT < 23) {
      hook("getNeighboringCellInfo");
    }
    if (Build.VERSION.SDK_INT > 16) {
      hook("getAllCellInfo");
    }
    hook("getCellLocation");
    hook("getNetworkType");
    hook("getPhoneType");
    hook("getCurrentPhoneType");
    hook("getSimState");
    hook("getDataState");
    if (Build.VERSION.SDK_INT > 22) {
      hook("getPhoneCount");
    }
  }

  private void before_getNetworkOperatorName(MethodHookParam param) throws Throwable {
  }

  private void before_getSimOperatorName(MethodHookParam param) throws Throwable {
  }

  private void before_getSimOperator(MethodHookParam param) throws Throwable {
  }

  private void before_getNetworkOperator(MethodHookParam param) throws Throwable {
  }

  private void before_getSimCountryIso(MethodHookParam param) throws Throwable {
  }

  private void before_getNetworkCountryIso(MethodHookParam param) throws Throwable {
  }

  private void before_getNeighboringCellInfo(MethodHookParam param) throws Throwable {
  }

  private void before_getAllCellInfo(MethodHookParam param) throws Throwable {
  }

  private void before_getCellLocation(MethodHookParam param) throws Throwable {
  }

  private void before_getNetworkType(MethodHookParam param) throws Throwable {
  }

  private void before_getPhoneType(MethodHookParam param) throws Throwable {
  }

  private void before_getCurrentPhoneType(MethodHookParam param) throws Throwable {
  }

  private void before_getSimState(MethodHookParam param) throws Throwable {
  }

  private void before_getDataState(MethodHookParam param) throws Throwable {
  }

  private void before_getPhoneCount(MethodHookParam param) throws Throwable {
  }
}

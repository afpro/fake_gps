package net.afpro.fakegpsxposedmodule.hooks;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LocationHook extends BaseHook {
  public LocationHook() {
    super("android.location.Location");
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getLatitude");
    hook("getLongitude");
    hook("getSpeed");
    hook("getAccuracy");
    hook("getBearing");
    hook("getAltitude");
  }

  private void before_getLatitude(MethodHookParam param) throws Throwable {
    param.setResult(30.0);
  }

  private void before_getLongitude(MethodHookParam param) throws Throwable {
    param.setResult(30.0);
  }

  private void before_getSpeed(MethodHookParam param) throws Throwable {
  }

  private void before_getAccuracy(MethodHookParam param) throws Throwable {
  }

  private void before_getBearing(MethodHookParam param) throws Throwable {
  }

  private void before_getAltitude(MethodHookParam param) throws Throwable {
  }
}

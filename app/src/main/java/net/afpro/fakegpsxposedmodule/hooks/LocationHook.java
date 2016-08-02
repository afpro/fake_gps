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
    param.setResult(40.75773);
  }

  private void before_getLongitude(MethodHookParam param) throws Throwable {
    param.setResult(-73.985708);
  }

  private void before_getSpeed(MethodHookParam param) throws Throwable {
    param.setResult(0f);
  }

  private void before_getAccuracy(MethodHookParam param) throws Throwable {
    param.setResult(100f);
  }

  private void before_getBearing(MethodHookParam param) throws Throwable {
    param.setResult(0.0f);
  }

  private void before_getAltitude(MethodHookParam param) throws Throwable {
    param.setResult(0.0);
  }
}

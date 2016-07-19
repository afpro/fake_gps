package net.afpro.fakegpsxposedmodule.hooks;

import android.location.GpsStatus;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LocationManagerHook extends BaseHook {
  public LocationManagerHook() {
    super("android.location.LocationManager");
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    XposedBridge.hookAllMethods(targetType(), "requestLocationUpdates", this);
    hook("getGpsStatus", GpsStatus.class);
  }

  private void before_requestLocationUpdates(MethodHookParam param) throws Throwable {

  }

  private void before_getGpsStatus(MethodHookParam param) throws Throwable {

  }
}

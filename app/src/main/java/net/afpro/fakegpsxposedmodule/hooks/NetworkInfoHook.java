package net.afpro.fakegpsxposedmodule.hooks;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class NetworkInfoHook extends BaseHook {
  public NetworkInfoHook() {
    super("android.net.NetworkInfo");
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getTypeName");
    hook("getType");
    hook("isConnected");
    hook("isAvailable");
  }

  private void before_getTypeName(MethodHookParam param) throws Throwable {

  }

  private void before_getType(MethodHookParam param) throws Throwable {

  }

  private void before_isConnected(MethodHookParam param) throws Throwable {

  }

  private void before_isAvailable(MethodHookParam param) throws Throwable {

  }
}

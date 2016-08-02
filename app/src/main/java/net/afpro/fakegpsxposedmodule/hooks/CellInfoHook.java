package net.afpro.fakegpsxposedmodule.hooks;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class CellInfoHook extends BaseHook {
  public CellInfoHook() {
    super("android.telephony.CellInfo");
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("isRegistered");
  }

  private void before_isRegistered(MethodHookParam param) throws Throwable {
    param.setResult(false);
  }
}

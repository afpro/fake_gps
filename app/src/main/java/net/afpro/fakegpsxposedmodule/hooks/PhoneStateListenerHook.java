package net.afpro.fakegpsxposedmodule.hooks;

import android.os.Build;
import android.telephony.CellLocation;

import java.util.List;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class PhoneStateListenerHook extends BaseHook {
  public PhoneStateListenerHook() {
    super("android.telephony.PhoneStateListener");
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    if (Build.VERSION.SDK_INT > 16) {
      hook("onCellInfoChanged", List.class);
    }
    hook("onCellLocationChanged", CellLocation.class);
  }

  private void before_onCellInfoChanged(MethodHookParam param) throws Throwable {
  }

  private void before_onCellLocationChanged(MethodHookParam param) throws Throwable {
  }
}

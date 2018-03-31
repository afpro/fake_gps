package hack.LocationProtector.hooks;

import android.telephony.TelephonyManager;

import java.util.Collections;

import hack.LocationProtector.BaseHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TelephonyManagerHook extends BaseHook {
  public TelephonyManagerHook() {
    super(TelephonyManager.class);
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getNeighboringCellInfo");
  }

  private void before_getNeighboringCellInfo(MethodHookParam param) {
    param.setResult(Collections.emptyList());
  }
}

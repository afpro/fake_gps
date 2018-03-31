package hack.LocationProtector.hooks;

import android.telephony.gsm.GsmCellLocation;

import hack.LocationProtector.BaseHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class GsmCellLocationHook extends BaseHook {
  public GsmCellLocationHook() {
    super(GsmCellLocation.class);
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getLac");
    hook("getCid");
  }

  private void before_getLac(MethodHookParam param) {
    param.setResult(-1);
  }

  private void before_getCid(MethodHookParam param) {
    param.setResult(-1);
  }
}

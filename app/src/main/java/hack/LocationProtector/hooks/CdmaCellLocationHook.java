package hack.LocationProtector.hooks;

import android.telephony.cdma.CdmaCellLocation;

import hack.LocationProtector.BaseHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class CdmaCellLocationHook extends BaseHook {
  public CdmaCellLocationHook() {
    super(CdmaCellLocation.class);
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getNetworkId");
    hook("getBaseStationId");
  }

  private void before_getNetworkId(MethodHookParam param) {
    param.setResult(-1);
  }

  private void before_getBaseStationId(MethodHookParam param) {
    param.setResult(-1);
  }
}

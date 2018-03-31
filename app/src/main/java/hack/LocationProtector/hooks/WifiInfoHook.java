package hack.LocationProtector.hooks;

import android.net.wifi.WifiInfo;
import android.text.TextUtils;

import hack.LocationProtector.BaseHook;
import hack.LocationProtector.GpsHackEntry;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class WifiInfoHook extends BaseHook {
  public WifiInfoHook() {
    super(WifiInfo.class);
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getSSID");
    hook("getBSSID");
  }

  private void before_getSSID(MethodHookParam param) {
    String ssid = GpsHackEntry.getSSID();
    if (!TextUtils.isEmpty(ssid)) {
      param.setResult(ssid);
    }
  }

  private void before_getBSSID(MethodHookParam param) {
    param.setResult("");
  }
}

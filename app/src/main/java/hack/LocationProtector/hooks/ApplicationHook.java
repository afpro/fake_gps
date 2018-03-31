package hack.LocationProtector.hooks;

import android.app.Application;

import hack.LocationProtector.BaseHook;
import hack.LocationProtector.GpsHackEntry;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ApplicationHook extends BaseHook {
  public ApplicationHook() {
    super(Application.class);
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("onCreate");
  }

  public void before_onCreate(MethodHookParam param) {
    GpsHackEntry.getInstance().setApp((Application) param.thisObject);
  }
}

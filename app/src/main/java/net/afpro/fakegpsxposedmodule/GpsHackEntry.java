package net.afpro.fakegpsxposedmodule;

import net.afpro.fakegpsxposedmodule.hooks.CellInfoHook;
import net.afpro.fakegpsxposedmodule.hooks.LocationHook;
import net.afpro.fakegpsxposedmodule.hooks.LocationManagerHook;
import net.afpro.fakegpsxposedmodule.hooks.NetworkInfoHook;
import net.afpro.fakegpsxposedmodule.hooks.PhoneStateListenerHook;
import net.afpro.fakegpsxposedmodule.hooks.TelephonyManagerHook;
import net.afpro.fakegpsxposedmodule.tasks.ScreenCaptureTask;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class GpsHackEntry implements IXposedHookLoadPackage {
  @Override
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
    for (IEntry entry : entries()) {
      entry.enter(loadPackageParam);
    }
  }

  private IEntry[] entries() {
    return new IEntry[]{
        // hooks
        new TelephonyManagerHook(),
        new PhoneStateListenerHook(),
        new LocationHook(),
        new NetworkInfoHook(),
        new LocationManagerHook(),
        new CellInfoHook(),

        // tasks
        new ScreenCaptureTask(),
    };
  }
}

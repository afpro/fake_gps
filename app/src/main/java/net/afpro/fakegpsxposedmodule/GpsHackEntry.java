package net.afpro.fakegpsxposedmodule;

import net.afpro.fakegpsxposedmodule.hooks.CellInfoHook;
import net.afpro.fakegpsxposedmodule.hooks.LocationHook;
import net.afpro.fakegpsxposedmodule.hooks.LocationManagerHook;
import net.afpro.fakegpsxposedmodule.hooks.NetworkInfoHook;
import net.afpro.fakegpsxposedmodule.hooks.PhoneStateListenerHook;
import net.afpro.fakegpsxposedmodule.hooks.TelephonyManagerHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class GpsHackEntry implements IXposedHookLoadPackage {
  @Override
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
    new TelephonyManagerHook().hookOn(loadPackageParam);
    new PhoneStateListenerHook().hookOn(loadPackageParam);
    new LocationHook().hookOn(loadPackageParam);
    new NetworkInfoHook().hookOn(loadPackageParam);
    new LocationManagerHook().hookOn(loadPackageParam);
    new CellInfoHook().hookOn(loadPackageParam);
  }
}


package net.afpro.fakegpsxposedmodule;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public interface IEntry {
  void enter(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable;
}

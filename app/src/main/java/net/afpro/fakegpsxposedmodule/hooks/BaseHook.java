package net.afpro.fakegpsxposedmodule.hooks;

import android.util.Log;

import net.afpro.fakegpsxposedmodule.IEntry;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class BaseHook extends XC_MethodHook implements IEntry {
  public static final String TAG = "BaseHook";

  private final String target;
  private final ThreadLocal<XC_LoadPackage.LoadPackageParam> loading = new ThreadLocal<>();

  public BaseHook(String target) {
    this.target = target;
  }

  protected boolean shouldHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    return true;
  }

  @Override
  public void enter(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    if (shouldHook(loadPackageParam)) {
      loading.set(loadPackageParam);
      try {
        onHook(loadPackageParam);
      } finally {
        loading.remove();
      }
    }
  }

  protected Class<?> targetType() {
    return XposedHelpers.findClass(target, loading.get().classLoader);
  }

  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
  }

  @Override
  protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
    try {
      XposedHelpers.callMethod(this, "before_" + param.method.getName(), param);
    } catch (NoSuchMethodError ignored) {
    }
    Log.d(TAG, String.format("beg %s", param.method));
  }

  @Override
  protected void afterHookedMethod(MethodHookParam param) throws Throwable {
    try {
      XposedHelpers.callMethod(this, "after_" + param.method.getName(), param);
    } catch (NoSuchMethodError ignored) {
    }
    Log.d(TAG, String.format("end %s => %s", param.method, param.getResultOrThrowable()));
  }

  protected void hook(String method, Object...types) {
    Object[] buf = new Object[types == null ? 1 : types.length + 1];
    if (types != null) {
      System.arraycopy(types, 0, buf, 0, buf.length - 1);
    }
    buf[buf.length - 1] = this;

    XposedHelpers.findAndHookMethod(
        target, loading.get().classLoader,
        method,
        buf);
  }
}

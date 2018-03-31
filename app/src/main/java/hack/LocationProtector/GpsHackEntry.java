package hack.LocationProtector;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.lang.ref.WeakReference;

import hack.LocationProtector.hooks.ApplicationHook;
import hack.LocationProtector.hooks.CdmaCellLocationHook;
import hack.LocationProtector.hooks.GsmCellLocationHook;
import hack.LocationProtector.hooks.LocationHook;
import hack.LocationProtector.hooks.TelephonyManagerHook;
import hack.LocationProtector.hooks.WifiInfoHook;
import hack.LocationProtector.services.ILocationProvider;
import hack.LocationProtector.services.LocationResult;
import hack.LocationProtector.services.LocationService;
import hack.LocationProtector.tasks.ScreenCaptureTask;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class GpsHackEntry implements IXposedHookLoadPackage {
  private static GpsHackEntry instance = null;
  private static final String TAG = "GpsHackEntry";

  private WeakReference<Application> app;
  private ILocationProvider locationProvider;

  public GpsHackEntry() {
    instance = this;
  }

  public static GpsHackEntry getInstance() {
    return instance;
  }

  @Override
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
    for (IEntry entry : entries()) {
      entry.enter(loadPackageParam);
    }
  }

  private IEntry[] entries() {
    return new IEntry[]{
        // application
        new ApplicationHook(),

        // sim info
        new GsmCellLocationHook(),
        new CdmaCellLocationHook(),

        // neighbour cell info
        new TelephonyManagerHook(),

        // wifi info
        new WifiInfoHook(),

        // location result
        new LocationHook(),

        // tasks
        new ScreenCaptureTask(),
    };
  }

  public Application getApp() {
    WeakReference<Application> ref = app;
    return ref == null ? null : ref.get();
  }

  public void setApp(Application app) {
    this.app = new WeakReference<>(app);

    try {
      Context packageContext = app.createPackageContext(BuildConfig.APPLICATION_ID, 0);
      app.bindService(new Intent(packageContext, LocationService.class),
          new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
              locationProvider = ILocationProvider.Stub.asInterface(service);
              Log.d(TAG, "got location provider");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
              locationProvider = null;
              Log.d(TAG, "location provider disconnected");
            }
          },
          Context.BIND_AUTO_CREATE);
    } catch (PackageManager.NameNotFoundException ignored) {
    }
  }

  public static LocationResult getLocation() {
    GpsHackEntry inst = instance;
    if (inst == null) {
      Log.d(TAG, "GpsHackEntry instance is null");
      return null;
    }

    ILocationProvider provider = inst.locationProvider;
    if (provider == null) {
      Log.d(TAG, "provider is null");
      return null;
    }

    try {
      return provider.getLocationResult();
    } catch (RemoteException e) {
      Log.w(TAG, "remote exception", e);
      return null;
    }
  }

  public static String getSSID() {
    GpsHackEntry inst = instance;
    if (inst == null) {
      Log.d(TAG, "GpsHackEntry instance is null");
      return null;
    }

    ILocationProvider provider = inst.locationProvider;
    if (provider == null) {
      Log.d(TAG, "provider is null");
      return null;
    }

    try {
      return provider.getSSID();
    } catch (RemoteException e) {
      Log.w(TAG, "remote exception", e);
      return null;
    }
  }
}

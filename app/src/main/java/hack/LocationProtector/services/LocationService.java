package hack.LocationProtector.services;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import java.util.List;
import java.util.Random;

public class LocationService extends Service {
  public static LocationResult result = new LocationResult();

  private ILocationProvider provider = new LocationProviderImpl();

  static {
    // time square
    result.lat = 40.75773;
    result.lon = -73.985708;

    // not so accurate, static
    result.speed = 0;
    result.accuracy = 100;
  }

  @Override
  public IBinder onBind(Intent intent) {
    return provider.asBinder();
  }

  private class LocationProviderImpl extends ILocationProvider.Stub {
    private ActivityManager activityManager = null;
    private PackageManager packageManager = null;

    private String ssid;

    private LocationProviderImpl() {
      String pool = "abcdefghijklmnopqrstuvwxyz";
      Random rng = new Random();
      int len = rng.nextInt(5) + 8;
      char[] buf = new char[len];
      for (int i = 0; i < len; i++) {
        buf[i] = pool.charAt(rng.nextInt(pool.length()));
      }
      ssid = new String(buf);
    }

    @Override
    public String getSSID() throws RemoteException {
      return ssid;
    }

    @Override
    public LocationResult getLocationResult() throws RemoteException {
      return result;
    }

    private ActivityManager getActivityManager() {
      if (activityManager == null) {
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
      }
      return activityManager;
    }

    private List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() {
      return getActivityManager().getRunningAppProcesses();
    }

    private PackageManager getPackageManager() {
      if (packageManager == null) {
        packageManager = LocationService.this.getPackageManager();
      }
      return packageManager;
    }

    PackageInfo getCallingPackage() throws RemoteException {
      String packageName = null;
      int pid = Binder.getCallingPid();
      for (ActivityManager.RunningAppProcessInfo process : getRunningAppProcesses()) {
        if (process.pid == pid) {
          packageName = process.processName.split(":")[0];
          break;
        }
      }

      if (TextUtils.isEmpty(packageName)) {
        String[] pkgs = getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (pkgs != null && pkgs.length > 0) {
          packageName = pkgs[0];
        }
      }

      try {
        return getPackageManager().getPackageInfo(
            packageName,
            PackageManager.GET_PERMISSIONS);
      } catch (PackageManager.NameNotFoundException ignored) {
      }

      throw new RemoteException();
    }
  }
}

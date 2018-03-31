package hack.LocationProtector.hooks;

import android.location.Location;
import android.util.Log;

import hack.LocationProtector.BaseHook;
import hack.LocationProtector.GpsHackEntry;
import hack.LocationProtector.services.LocationResult;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LocationHook extends BaseHook {
  private LocationResult locationResult = null;
  private long nextLocationUpdate = 0;

  public LocationHook() {
    super(Location.class);
  }

  @Override
  protected void onHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    hook("getLatitude");
    hook("getLongitude");
    hook("getSpeed");
    hook("getAccuracy");
    hook("getBearing");
    hook("getAltitude");
  }

  private LocationResult getLocationResult() {
    long now = System.currentTimeMillis();
    if (now >= nextLocationUpdate) {
      nextLocationUpdate = now + 5000;
      locationResult = GpsHackEntry.getLocation();
    }
    return locationResult;
  }

  private void runIfHasLocationResult(LocationAction action) {
    if (action == null) {
      return;
    }

    LocationResult locationResult = getLocationResult();
    if (locationResult == null) {
      return;
    }

    Log.d(getClass().getName(), "hook location to " + locationResult);

    action.withLocation(locationResult);
  }

  private void before_getLatitude(final MethodHookParam param) throws Throwable {
    runIfHasLocationResult(new LocationAction() {
      @Override
      public void withLocation(LocationResult locationResult) {
        param.setResult(locationResult.lat);
      }
    });
  }

  private void before_getLongitude(final MethodHookParam param) throws Throwable {
    runIfHasLocationResult(new LocationAction() {
      @Override
      public void withLocation(LocationResult locationResult) {
        param.setResult(locationResult.lon);
      }
    });
  }

  private void before_getSpeed(final MethodHookParam param) throws Throwable {
    runIfHasLocationResult(new LocationAction() {
      @Override
      public void withLocation(LocationResult locationResult) {
        param.setResult(locationResult.speed);
      }
    });
  }

  private void before_getAccuracy(final MethodHookParam param) throws Throwable {
    runIfHasLocationResult(new LocationAction() {
      @Override
      public void withLocation(LocationResult locationResult) {
        param.setResult(locationResult.accuracy);
      }
    });
  }

  private void before_getBearing(MethodHookParam param) throws Throwable {
    param.setResult(0.0f);
  }

  private void before_getAltitude(MethodHookParam param) throws Throwable {
    param.setResult(0.0);
  }

  private interface LocationAction {
    void withLocation(LocationResult locationResult);
  }
}

package hack.LocationProtector.tasks;

import hack.LocationProtector.IEntry;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class BaseTask implements IEntry {
  @Override
  public void enter(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
    if (shouldScheduleOn(loadPackageParam)) {
      new Thread() {
        @Override
        public void run() {
          worker(loadPackageParam);
        }
      }.start();
    }
  }

  protected void worker(XC_LoadPackage.LoadPackageParam loadPackageParam) {
  }

  protected boolean shouldScheduleOn(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    return false;
  }
}

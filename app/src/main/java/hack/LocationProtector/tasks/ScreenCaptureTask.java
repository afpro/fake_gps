package hack.LocationProtector.tasks;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.FileOutputStream;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ScreenCaptureTask extends BaseTask {
  @Override
  protected boolean shouldScheduleOn(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    return loadPackageParam.packageName.equals("com.android.systemui");
  }

  @Override
  protected void worker(XC_LoadPackage.LoadPackageParam loadPackageParam) {
    Log.d("fuck", "system ui worker started");

    while (true) {
      try {
        loop(loadPackageParam);
      } catch (Throwable e) {
        Log.e("fuck", "loop failed", e);
      }

      try {
        Thread.sleep(5000);
      } catch (InterruptedException ignored) {
      }
    }
  }

  private void loop(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
    Class cSurfaceControl = XposedHelpers.findClass("android.view.SurfaceControl", loadPackageParam.classLoader);
    Bitmap bitmap = (Bitmap) XposedHelpers.callStaticMethod(
        cSurfaceControl,
        "screenshot",
        768,
        1024);

    if (bitmap == null) {
      Log.d("fuck", "capture screen failed");
      return;
    }

    FileOutputStream fos = new FileOutputStream("/sdcard/fuck.png");
    bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
    fos.close();

    Log.d("fuck", "saved to /sdcard/fuck.png");
  }
}

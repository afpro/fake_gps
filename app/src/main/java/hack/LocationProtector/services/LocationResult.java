package hack.LocationProtector.services;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationResult implements Parcelable {
  public double lat;
  public double lon;
  public float accuracy;
  public float speed;

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(this.lat);
    dest.writeDouble(this.lon);
    dest.writeFloat(this.accuracy);
    dest.writeFloat(this.speed);
  }

  public LocationResult() {
  }

  protected LocationResult(Parcel in) {
    this.lat = in.readDouble();
    this.lon = in.readDouble();
    this.accuracy = in.readFloat();
    this.speed = in.readFloat();
  }

  @Override
  public String toString() {
    return "LocationResult{" +
        "lat=" + lat +
        ", lon=" + lon +
        ", accuracy=" + accuracy +
        ", speed=" + speed +
        '}';
  }

  public static final Parcelable.Creator<LocationResult> CREATOR = new Parcelable.Creator<LocationResult>() {
    @Override
    public LocationResult createFromParcel(Parcel source) {
      return new LocationResult(source);
    }

    @Override
    public LocationResult[] newArray(int size) {
      return new LocationResult[size];
    }
  };
}

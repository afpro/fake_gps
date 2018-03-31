package hack.LocationProtector.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hack.LocationProtector.R;
import hack.LocationProtector.services.LocationService;

public class SettingsActivity extends AppCompatActivity {

  EditText lat, lon;
  Button change;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    lat = (EditText) findViewById(R.id.latitude);
    lon = (EditText) findViewById(R.id.longitude);
    change = (Button) findViewById(R.id.change);

    lat.setText(Double.toString(LocationService.result.lat));
    lon.setText(Double.toString(LocationService.result.lon));

    change.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        LocationService.result.lat = Double.parseDouble(lat.getText().toString());
        LocationService.result.lon = Double.parseDouble(lon.getText().toString());
      }
    });
  }
}

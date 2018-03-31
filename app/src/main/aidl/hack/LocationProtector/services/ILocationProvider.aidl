package hack.LocationProtector.services;

import hack.LocationProtector.services.LocationResult;

interface ILocationProvider {
    String getSSID();
    LocationResult getLocationResult();
}

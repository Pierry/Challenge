package com.github.pierry.arctouchcallenge.ui.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.ui.common.NotifyHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

@EFragment(R.layout.fragment_map) public class MapFragment extends Fragment
    implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

  @Bean NotifyHelper notifyHelper;

  SupportMapFragment mSupportMapFragment;

  private GoogleMap mMap;

  @AfterViews void init() {
    mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
    if (mSupportMapFragment == null) {
      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      mSupportMapFragment = SupportMapFragment.newInstance();
      fragmentTransaction.replace(R.id.map, mSupportMapFragment).commit();
    }

    if (mSupportMapFragment != null) {
      mSupportMapFragment.getMapAsync(this);
    }
  }

  void getByLatLng(double latitude, double longitude) throws IOException {
    Geocoder geocoder;
    List<Address> addresses;
    geocoder = new Geocoder(getActivity(), Locale.getDefault());
    addresses = geocoder.getFromLocation(latitude, longitude, 1);
    String address = addresses.get(0).getAddressLine(0);
    notifyHelper.warning(address);
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
  }

  @UiThread void move(double latitude, double longitude) {
    LatLng sydney = new LatLng(latitude, longitude);
    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
  }

  @Override public void onMapClick(LatLng latLng) {
    try {
      move(latLng.latitude, latLng.longitude);
      getByLatLng(latLng.latitude, latLng.longitude);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

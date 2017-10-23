package com.blogbasbas.tombolpentinggorontalo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.blogbasbas.tombolpentinggorontalo.R;
import com.blogbasbas.tombolpentinggorontalo._sliders.FragmentSlider;
import com.blogbasbas.tombolpentinggorontalo._sliders.SliderIndicator;
import com.blogbasbas.tombolpentinggorontalo._sliders.SliderPagerAdapter;
import com.blogbasbas.tombolpentinggorontalo._sliders.SliderView;
import com.blogbasbas.tombolpentinggorontalo.adapter.AdapterList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;


    String nama[] = {"Rs Aloe Saboe", " Rs Bunda",
            "Rs Islam", "Rs Bersalin Siti Hadijah",
            "Polres Kota", "D A M K A R",
            "P L N ", "P D A M"};
    ListView listTempat;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //TODO 8 ADMOB
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("7535AF4EB2936B47ED5B7A27A0547F7F")
                .build();
        //banner
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(adRequest);


        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        23);

                // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        listTempat = (ListView) findViewById(R.id.listViewTempat);
        AdapterList adapter = new AdapterList(this, nama);
        listTempat.setAdapter(adapter);

        listTempat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        try {
                            String nomor[] = {"tel:0435822753", "tel:0435827573", "tel:0435830820", "tel:0435821258",
                                    "tel:0435821110", "0435", "tel:0435822221", "tel:0435825398"};
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(nomor[i]));


                            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED) {

                            }
                            startActivity(callIntent);

                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Gagal " , Toast.LENGTH_SHORT).show();
                        }
                    }
                });




        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        setupSlider();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private int showRsbunda() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0222034953"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        startActivity(callIntent);
        return 0;
    }

    private void setupSlider() {
        sliderView.setDurationScroll(500);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://s1.postimg.org/1a1f0ime6n/gorontalo1.png"));
        fragments.add(FragmentSlider.newInstance("https://s1.postimg.org/99jt7kfrxr/gorontalo2.png"));
        fragments.add(FragmentSlider.newInstance("https://s1.postimg.org/1afgci4qjj/gorontalo3.png"));




        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            exitMethod();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            AlertDialog.Builder al = new AlertDialog.Builder(this);
            al.setTitle("ABaout App");
            al.setMessage(" Aplikasi ini menampilkan informasi kontak penting di Gorontalo");
            al.setIcon(R.mipmap.ic_call);
            al.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            al.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {


            showAlert();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            showAlert2();

        } else if (id == R.id.nav_slideshow) {
            showAlert3();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAlert3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi ");
        builder.setMessage(" Halaman Ini Membutuhkan Internet ? ");
        builder.setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /*startActivity(new Intent(MainActivity.this,WebKotaActivity.class));*/
            }
        });

        builder.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    private void showAlert2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi ");
        builder.setMessage(" Halaman Ini Membutuhkan Internet ? ");
        builder.setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /*startActivity(new Intent(MainActivity.this,KontakActivity.class));*/
            }
        });

        builder.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi ");
        builder.setMessage(" Halaman Ini Membutuhkan Internet ? ");
        builder.setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              /*  startActivity(new Intent(MainActivity.this,KamusGorontaloActivity.class));*/
            }
        });

        builder.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    public void exitMethod (){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(" Exit Konfirm");
    builder.setMessage("Apakah  Ingin Keluar ?");
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            System.exit(0);
            moveTaskToBack(true);
        }
    });

    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    });

    builder.show();
}


    }


package com.example.artistaapp.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.artistaapp.R;
import com.example.artistaapp.utils.BottomNavigationViewHelper;
import com.example.artistaapp.utils.GridImageAdapter;
import com.example.artistaapp.utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 3;
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this;
    private ProgressBar mProgressBar;
    private ImageView mProfilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();

        // Set up the temporary grid
        tempGridSetup();
    }

    private void tempGridSetup() {
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://images.squarespace-cdn.com/content/v1/587ed62137c581c9c42fee61/1487623780844-OFR1P50ERT41SIO76EIV/ke17ZwdGBToddI8pDm48kMXRibDYMhUiookWqwUxEZ97gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z4YTzHvnKhyp6Da-NYroOW3ZGjoBKy3azqku80C789l0luUmcNM2NMBIHLdYyXL-Jww_XBra4mrrAHD6FMA3bNKOBm5vyMDUBjVQdcIrt03OQ/The+salon%E2%80%99s+mirrors+are+made+from+repurposed+douglas+fir+that+was+originally+used+to+build+a+Pasadena+home+in+1917.?format=750w");
        imgURLs.add("https://beuhairsalon.com/wp-content/uploads/2017/09/beuhair-interior-11-min.jpg");
        imgURLs.add("https://news.rutgers.edu/sites/medrel/files/inline-img/Top%20Left%20Salon.jpg");
        imgURLs.add("https://d2dfxqxblmblx4.cloudfront.net/images/hero/homepage.jpg");
        imgURLs.add("https://images.squarespace-cdn.com/content/v1/587ed62137c581c9c42fee61/1487623780844-OFR1P50ERT41SIO76EIV/ke17ZwdGBToddI8pDm48kMXRibDYMhUiookWqwUxEZ97gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z4YTzHvnKhyp6Da-NYroOW3ZGjoBKy3azqku80C789l0luUmcNM2NMBIHLdYyXL-Jww_XBra4mrrAHD6FMA3bNKOBm5vyMDUBjVQdcIrt03OQ/The+salon%E2%80%99s+mirrors+are+made+from+repurposed+douglas+fir+that+was+originally+used+to+build+a+Pasadena+home+in+1917.?format=750w");
        imgURLs.add("https://beuhairsalon.com/wp-content/uploads/2017/09/beuhair-interior-11-min.jpg");
        imgURLs.add("https://news.rutgers.edu/sites/medrel/files/inline-img/Top%20Left%20Salon.jpg");
        imgURLs.add("https://d2dfxqxblmblx4.cloudfront.net/images/hero/homepage.jpg");
        imgURLs.add("https://images.squarespace-cdn.com/content/v1/587ed62137c581c9c42fee61/1487623780844-OFR1P50ERT41SIO76EIV/ke17ZwdGBToddI8pDm48kMXRibDYMhUiookWqwUxEZ97gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z4YTzHvnKhyp6Da-NYroOW3ZGjoBKy3azqku80C789l0luUmcNM2NMBIHLdYyXL-Jww_XBra4mrrAHD6FMA3bNKOBm5vyMDUBjVQdcIrt03OQ/The+salon%E2%80%99s+mirrors+are+made+from+repurposed+douglas+fir+that+was+originally+used+to+build+a+Pasadena+home+in+1917.?format=750w");
        imgURLs.add("https://beuhairsalon.com/wp-content/uploads/2017/09/beuhair-interior-11-min.jpg");
        imgURLs.add("https://news.rutgers.edu/sites/medrel/files/inline-img/Top%20Left%20Salon.jpg");
        imgURLs.add("https://d2dfxqxblmblx4.cloudfront.net/images/hero/homepage.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgURLs) {
        GridView gridView = (GridView) findViewById(R.id.gridViewProfileGallery);

        // Set up the Square Grid images
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview,"", imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setupActivityWidgets() {
        mProgressBar = (ProgressBar)findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        mProfilePhoto = (ImageView)findViewById(R.id.profile_photo);
    }

    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile photo.");
        String imgURL = "drawable://" + R.drawable.trump_default;
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, mProgressBar, "");
    }

    /**
     * ToolBar for the profile Setup
     */
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating do account settings.");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Bottom Navigation View Setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

        // Get the menu items by indexes
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}

package com.mundoanimal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.mundoanimal.AnimalFragment.OnListFragmentInteractionListener;
import com.mundoanimal.ImageListFragment.OnImageListFragmentInteractionListener;
import com.mundoanimal.AnimalContent.AnimalItem;
import com.mundoanimal.ImageListContent.ImageListItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener, OnImageListFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_main)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                onMainActivityBackPress();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onMainActivityBackPress() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                Intent intent = new Intent(getApplicationContext(), LaunchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            } else {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 20000);
            }
        }
    }

    @Override
    public void onListFragmentInteraction(AnimalItem item) {

    }

    @Override
    public void OnImageListFragmentInteraction(ImageListItem item) {

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 121:
                onRequestPermission();
                saveImage(item.getGroupId());
                return true;
            case 122:
                onRequestPermission();
                shareImage(item.getGroupId());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void displayMessage(String message) {
        Snackbar.make(findViewById(R.id.nav_host_fragment), message, Snackbar.LENGTH_SHORT).show();
    }

    public void onRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Snackbar.make(findViewById(R.id.nav_host_fragment), "Permiso denegado, no se puede guardar la imagen", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImage(int imageId) {
       String externalStorage = Environment.getExternalStorageState();
       if (externalStorage.equals(Environment.MEDIA_MOUNTED)) {
          // String storageDirectory = Environment.getExternalStorageDirectory().toString() + "/MundoAnimal";
           File storageDir  = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
           try {
               File file = File.createTempFile(String.valueOf(imageId), ".jpg", storageDir);
               boolean created = file.mkdir();
               OutputStream stream = new FileOutputStream(file);
               Drawable image = ContextCompat.getDrawable(this, imageId);
               Bitmap bitmap = ((BitmapDrawable)image).getBitmap();
               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
               stream.flush();
               stream.close();
               displayMessage("Imagen salvada");
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }

       }
    }

    private void shareImage(int imageId) {
        Drawable image = ContextCompat.getDrawable(this, imageId);
        Bitmap bitmap = ((BitmapDrawable)image).getBitmap();
        File storageDir  = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            File file = File.createTempFile(String.valueOf(imageId), ".jpg", storageDir);
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
            file.setReadable(true,false);
            Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("image/jpg");
            Intent chooser = Intent.createChooser(intent, "Enviar imagen");
            List<ResolveInfo> resInfoList = this.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                this.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            startActivity(chooser);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Archivo no disponible", Toast.LENGTH_SHORT).show();
        }
    }
}

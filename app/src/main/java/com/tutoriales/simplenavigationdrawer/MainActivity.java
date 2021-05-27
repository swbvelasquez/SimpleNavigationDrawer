package com.tutoriales.simplenavigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout lyDrawer;
    NavigationView nvNavigation;
    Toolbar tlbNavigation;
    ActionBarDrawerToggle adtNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazar vista
        lyDrawer = findViewById(R.id.lyDrawer);
        nvNavigation = findViewById(R.id.nvNavigation);
        tlbNavigation = findViewById(R.id.tlbNavigation);

        //Inicializo el primer fragment
        getSupportFragmentManager().beginTransaction().add(R.id.lyContent,new HomeFragment()).commit();
        setTitle("Home");

        //configurar el toggle
        adtNavigation= new ActionBarDrawerToggle(this,lyDrawer,tlbNavigation,R.string.drawer_open,R.string.drawer_close);
        lyDrawer.addDrawerListener(adtNavigation);

        //indicar cual sera el toolbar
        setSupportActionBar(tlbNavigation);

        //configurar el item select del navigation
        nvNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Evita que el icono del drawer se cierre o cambie
        adtNavigation.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Notificar el cambio de orientacion
        adtNavigation.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //para abrir o cerrar el toggle
        if(adtNavigation.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Con el fragment manager inicio la transaccion
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //segun el seleccionado reemplazo el contenido del lyContent
        if(item.getItemId()==R.id.itmHome){
            fragmentTransaction.replace(R.id.lyContent, new HomeFragment()).commit();
        }else{
            if(item.getItemId()==R.id.itmProfile){
                fragmentTransaction.replace(R.id.lyContent, new ProfileFragment()).commit();
            }else{
                if(item.getItemId()==R.id.itmEvent){
                    fragmentTransaction.replace(R.id.lyContent, new EventFragment()).commit();
                }else{
                    if(item.getItemId()==R.id.itmNotification){
                        fragmentTransaction.replace(R.id.lyContent, new NotificationFragment()).commit();
                    }else{
                        if(item.getItemId()==R.id.itmContact){
                            fragmentTransaction.replace(R.id.lyContent, new ContactFragment()).commit();
                        }
                    }
                }
            }
        }

        //pongo el titulo del item seleccionado
        setTitle(item.getTitle());
        //cierro el drawer
        lyDrawer.closeDrawers();

        return true;
    }


}
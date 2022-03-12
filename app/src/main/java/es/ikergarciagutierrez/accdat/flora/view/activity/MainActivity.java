package es.ikergarciagutierrez.accdat.flora.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.ikergarciagutierrez.accdat.flora.R;
import es.ikergarciagutierrez.accdat.flora.model.entity.Flora;
import es.ikergarciagutierrez.accdat.flora.view.adapter.Adapter;
import es.ikergarciagutierrez.accdat.flora.viewmodel.MainActivityViewModel;

/**
 * Clase que principal que contiene el RecyclerView para los objetos Flora
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Campos de la clase
     */
    private Context context;

    private RecyclerView recyclerView;
    private MainActivityViewModel mavm;
    private Adapter adapter;

    private ImageView ivEmpty;
    private TextView tvEmpty;

    private FloatingActionButton fabAdd;

    private GoogleSignInClient mGoogleSignInClient;
    static int RC_SIGN_IN = 100;

    private MenuItem item;
    private boolean signedIn;

    /**
     * Método que coloca el menú customizado creado mediante el recurso menu.xml.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        item = menu.findItem(R.id.action_account);
        if (signedIn) {
            item.setTitle(R.string.logout_account);
        } else {
            item.setTitle(R.string.login_account);
        }

        return true;
    }

    /**
     * Método que controla todas las opciones del menu. Al pulsar una de ellas se iniciará su
     * correspondiente activity, todas recogidas en el package menu.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item = this.item;
        if (item.getTitle().equals("Iniciar sesión")) {
            signIn();
        }
        if (item.getTitle().equals("Cerrar sesión")) {
            mGoogleSignInClient.signOut();
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPreferences.edit().clear();
            prefEditor.apply();
            Toast.makeText(context, "Sesión cerrada", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void signIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPreferences.edit();
            prefEditor.putString("correo", account.getEmail());
            prefEditor.apply();

            // Signed in successfully, show authenticated UI.
            Toast.makeText(context, "Sesión inicida", Toast.LENGTH_SHORT).show();
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("xyzyx", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    /**
     * Método que infla el layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initialize();
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    //Change UI according to user data.
    public void updateUI(GoogleSignInAccount account){
        if(account != null){
            // Sesión iniciada
            signedIn = true;
            fabAdd.setVisibility(View.VISIBLE);
            tvEmpty.setText(R.string.tvEmpty);
        }else {
            // Sesión no iniciada
            signedIn = false;
            fabAdd.setVisibility(View.INVISIBLE);
            tvEmpty.setText("Inicia sesión para acceder a tu jardín");
        }

    }

    /**
     * Método que recarga el layout al volver a esta activty
     */
    protected void onResume() {
        super.onResume();
        mavm.getFlora();
        initialize();
    }

    /**
     * Método que inicializa los componentes del layout y los métodos de los listener
     */
    private void initialize() {

        recyclerView = findViewById(R.id.rvFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new Adapter(context);
        recyclerView.setAdapter(adapter);

        fabAdd = findViewById(R.id.fabAdd);

        ivEmpty = findViewById(R.id.ivEmpty);
        tvEmpty = findViewById(R.id.tvEmpty);

        mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFlora();
        mavm.getFloraLiveData().observe(this, floras -> {
            adapter.setFloraList(floras);
            if (floras.isEmpty()) {
                ivEmpty.setVisibility(View.VISIBLE);
                tvEmpty.setVisibility(View.VISIBLE);
            } else {
                ivEmpty.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.GONE);
            }
        });

        defineFABAddListener();
        defineFloraListener();
    }

    /**
     * Listener para los item del RecyclerView. Abre la activity para editar objetos Flora
     */
    private void defineFloraListener() {
        adapter.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditFloraActivity.class);
            intent.putExtra("idFlora", adapter.getItem(recyclerView.getChildAdapterPosition(view)));
            startActivity(intent);
        });
    }

    /**
     * Listener del fab FABAdd. Abre la activity para añadir objetos Flora
     */
    private void defineFABAddListener() {
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddFloraActivity.class);
            startActivity(intent);
        });
    }

}
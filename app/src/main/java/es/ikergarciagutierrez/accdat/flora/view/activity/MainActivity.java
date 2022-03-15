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
import android.view.LayoutInflater;
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
import com.google.android.material.snackbar.Snackbar;

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

    private TextView tvAccount;
    private MenuItem item;
    private boolean signedIn;

    /**
     * Método que coloca el menu customizado creado mediante el recurso menu.xml
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
     * Método que controla los items del menu
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item = this.item;
        if (item.getTitle().equals("Iniciar sesión")) {
            Snackbar.make(getWindow().getDecorView(), R.string.snackbar_login, Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(getResources().getColor(R.color.primary_color))
                    .setAction("Aceptar", view1 -> {
                        signIn();
                    })
                    .show();
        }
        if (item.getTitle().equals("Cerrar sesión")) {
            mGoogleSignInClient.signOut();
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPreferences.edit().clear();
            prefEditor.apply();
            showToast(R.string.toast_logged_out);
            refreshActivity();
        }
        return true;
    }

    /**
     * Método que inicia la sesión con una cuenta de Google
     */
    private void signIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    /**
     * Método que permite al usuario seleccionar una cuenta de Google ya registrada en la aplicación
     * o registrar una nueva
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Resultado devuelto al lanzar el intent de GoogleSignInClient
        if (requestCode == RC_SIGN_IN) {
            try {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                String dominio[] = task.getResult().getEmail().split("@");
                if (dominio[1].trim().equals("ieszaidinvergeles.org")) {
                    handleSignInResult(task);
                } else {
                    showToast(R.string.toast_invalidEmail);
                    mGoogleSignInClient.signOut();
                }
            } catch (RuntimeException e) {

            }
        }
    }

    /**
     * Método que guarda los datos necesarios del usuario en la preferencias compartidas
     *
     * @param completedTask
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Guardamos los datos del usuario en las preferencias compartidas
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPreferences.edit();
            prefEditor.putString("correo", account.getEmail());
            prefEditor.putString("name", account.getDisplayName());
            prefEditor.apply();

            // Se ha iniciado sesión correctamente, mostramos un mensaje el usuario
            showToast(R.string.toast_logged_in);
            refreshActivity();
            updateUI(account);
        } catch (ApiException e) {
            // Ha habido um problema con el inicio de sesión
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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initialize();
        context = this;
    }

    /**
     * Método que se ejecuta al iniciar la aplicación, comprobando si hay una cuenta logueda
     */
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    /**
     * Método que actualiza la UI según si se a iniciado sesión o no
     *
     * @param account
     */
    public void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            // Sesión iniciada
            signedIn = true;
            fabAdd.setVisibility(View.VISIBLE);
            tvEmpty.setText(R.string.tvEmpty);
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE);
            tvAccount.setText("¡Hola " + sharedPreferences.getString("name", "") + "!");
            tvAccount.setVisibility(View.VISIBLE);
        } else {
            // Sesión no iniciada
            signedIn = false;
            fabAdd.setVisibility(View.INVISIBLE);
            tvEmpty.setText(R.string.tvNoLogin);
            tvAccount.setVisibility(View.INVISIBLE);
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

        tvAccount = findViewById(R.id.tvAccount);

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

    /**
     * Método que recarga el layout
     */
    private void refreshActivity() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    /**
     * Método que muestra un Toast personalizado
     *
     * @param message Mensaje que queremos que aparezca en el Toast
     */
    private void showToast(int message) {
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView tvToast = view.findViewById(R.id.tvMessage);
        tvToast.setText(message);
        toast.setView(view);
        toast.show();
    }

}

package com.ezediaz.loginconarchivos.ui.registro;
import static android.app.Activity.RESULT_OK;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ezediaz.loginconarchivos.model.Usuario;
import com.ezediaz.loginconarchivos.request.ApiClient;
import com.ezediaz.loginconarchivos.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> mUsuario;
    private MutableLiveData<Uri> mFoto;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Usuario> getMUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public LiveData<Uri> getMFoto() {
        if(mFoto == null){
            mFoto = new MutableLiveData<>();
        }
        return mFoto;
    }

    public void cargar(Intent intent){
        ApiClient api = new ApiClient();
        int existe = intent.getFlags();
        Usuario usuario = new Usuario();
        if(existe != -1){
            usuario = api.leer(getApplication());
        }
        mUsuario.setValue(usuario);
    }

    public void guardar(String dni, String nombre, String apellido, String email, String password) {
        if (!dni.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            Usuario usuario = new Usuario(dni, nombre, apellido, email, password);
            ApiClient api = new ApiClient();
            api.guardar(getApplication(), usuario);
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        } else {
            Toast.makeText(getApplication(), "Debe ingresar datos en todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}

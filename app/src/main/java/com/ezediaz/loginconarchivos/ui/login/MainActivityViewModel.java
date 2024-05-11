package com.ezediaz.loginconarchivos.ui.login;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ezediaz.loginconarchivos.model.Usuario;
import com.ezediaz.loginconarchivos.request.ApiClient;
import com.ezediaz.loginconarchivos.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> mEmail;
    private MutableLiveData<String> mPassword;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMEmail(){
        if(mEmail == null){
            mEmail = new MutableLiveData<>();
        }
        return mEmail;
    }

    public LiveData<String> getMPassword(){
        if(mPassword == null){
            mPassword = new MutableLiveData<>();
        }
        return mPassword;
    }
    public void logearse(String email, String password) {
        ApiClient api = new ApiClient();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplication().getApplicationContext(), "Ingrese un email y una contraseña", Toast.LENGTH_LONG).show();
        }else {
            Usuario usuario = api.login(getApplication().getApplicationContext(), email, password);

            if (usuario != null) {
                Intent intent = new Intent(getApplication(), RegistroActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(intent);
            } else {
                Toast.makeText(getApplication(), "Email o contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void registrarse(){
        Intent intent = new Intent(getApplication(), RegistroActivity.class);
        intent.addFlags(-1);
        intent.putExtra("usuario", false);
        getApplication().startActivity(intent);
    }
}

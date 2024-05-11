package com.ezediaz.loginconarchivos.request;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.ezediaz.loginconarchivos.model.Usuario;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class ApiClient {

    public Usuario login(Context context, String email, String password){
        File archivo = new File(context.getFilesDir(), "datos.dat");
        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Usuario usuario = (Usuario) ois.readObject();
            if(usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                return usuario;
            } else {
                return null;
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario leer(Context context){
        File archivo = new File(context.getFilesDir(), "datos.dat");
        Usuario usuario = null;
        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(bis);
            usuario = (Usuario) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public void guardar(Context context, Usuario usuario){
        File archivo = new File(context.getFilesDir(), "datos.dat");
        try {
            FileOutputStream fo = new FileOutputStream(archivo);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            ObjectOutputStream oos = new ObjectOutputStream(bo);
            oos.writeObject(usuario);
            bo.flush();
            oos.close();
        } catch (IOException e) {
            Toast.makeText(context, "Error al acceder al archivo", Toast.LENGTH_LONG).show();
        }
    }
}

package at.ums.luna.volleynuevacabeceraalbaran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView estado;
    Button boton;
    String url_consulta = "http://192.168.1.100/lieboch/insertar_cabecera_albaran.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estado = (TextView)findViewById(R.id.tvEstado);
        boton = (Button)findViewById(R.id.bn);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idTrabajador, codigoAlbaran, fecha, recogida, id, idCliente;

                id = "160004";
                idTrabajador = "JJ";
                codigoAlbaran = idTrabajador + id;
                fecha = "15.11.2016";
                recogida = "abholung";
                idCliente = "1";

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        url_consulta,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MainActivity.this, "Respuesta: " + response, Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("JJ", error.toString());

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("id", id);
                        params.put("idTrabajador", idTrabajador);
                        params.put("codigoAlbaran", codigoAlbaran);
                        params.put("idCliente", idCliente);
                        params.put("fecha", fecha);
                        params.put("recogida", recogida);

                        return params;
                    }
                };
                MySingleton.getInstance(MainActivity.this).addToRequestque(stringRequest);
            }
        });
    }
}

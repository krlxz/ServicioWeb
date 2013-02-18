package com.example.servicioweb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	private ProgressDialog pd;
	private TextView ciudad;
	private TextView pais;
	private Button boton1;
	private String res;
	private Context context;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        
        ciudad=(TextView)findViewById(R.id.ciudad);
        pais=(TextView)findViewById(R.id.pais);
        boton1=(Button)findViewById(R.id.consultar);
        //Se establece listener para nuestro boton
        boton1.setOnClickListener(listener);
      
    }
    
	private OnClickListener listener = new OnClickListener() {
	
		public void onClick(View arg0) {
		     //	Usamos un AsyncTask, para poder mostrar una ventana de por favor espere, mientras se consulta el servicio web
			 new DownloadTask2().execute("");
			 pd = ProgressDialog.show(context, "Por favor espere","Consultando Clima", true, false);
			
		}
	};
	
    //Tarea en Background
	private class DownloadTask2 extends AsyncTask<String, Void, Object> {
		protected Integer doInBackground(String... args) {
			CargaDatosWS ws=new CargaDatosWS();
			//Se invoca nuestro metodo
			res=ws.getClima(ciudad.getText().toString(), pais.getText().toString());
			return 1;
		}

	protected void onPostExecute(Object result) {
			//Se elimina la pantalla de por favor espere.
			pd.dismiss();
			//Se muestra mensaje con la respuesta del servicio web
			String repuesta=res;
			System.out.println(repuesta);
			Toast.makeText(context,"Clima: "+res,Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
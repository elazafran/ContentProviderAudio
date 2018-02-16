package com.example.elaza.contentprovideraudio;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView texto;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.buttonret);
        texto = (TextView) findViewById(R.id.contactos);
        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "pulsado boton", Toast.LENGTH_SHORT).show();
                String[] projection = new String[] {
                        MediaStore.Audio.AudioColumns.ALBUM,
                        MediaStore.Audio.AudioColumns.TITLE };
                Uri contentUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
                Cursor cursor = getContentResolver().query(contentUri,
                        projection, null, null, null);
                // obtenemos el indice de la columna que necesitamos
                int albumIdx = cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM);
                int titleIdx = cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE);
                // Inicializamos el array donde guardamos los datos del resultado.
                String[] result = new String[cursor.getCount()];
                /*
                 * Recorremos el cursor mostrando cada nombre de album y el titulo de la cancion
                 */
                while (cursor.moveToNext()) {
                    // Extraemos el nombre de la canción.
                    String title = cursor.getString(titleIdx);
                    // Extraemos el nombre del album.
                    String album = cursor.getString(albumIdx);
                    result[cursor.getPosition()] = title + " (" + album + ")";
                    //en cada interación añadimos el texto al textview
                    texto.append(title + " (" + album + ")\n");
                }
                // cerramos el Cursor.
                cursor.close();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el mneu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
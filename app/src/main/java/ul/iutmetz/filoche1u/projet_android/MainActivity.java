package ul.iutmetz.filoche1u.projet_android;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list_act;
        list_act = (ListView) findViewById(R.id.liste_acteur);
        final String[] acteurs = {"Eliza Taylor", "Thomas McDonell", "Marie Avgeropoulos", "Henry Ian Cusick", "Isaiah Washington", "Bob Morley"};
        final ArrayAdapter<String> adapter_act = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, acteurs);
        list_act.setAdapter(adapter_act);

        final MainActivity self = this;
        // Standard click listener
        list_act.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Log.d("OKKKKKKKKKKKKKKKKKKKKK", acteurs[position]);
            }
        });
        // Long click listener
        list_act.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, int position, long id) {
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(self);
                final EditText edittext = new EditText(self);
                edittext.setText(acteurs[position]);
                self.position = position;
                alert.setView(edittext);
                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        acteurs[self.position] = edittext.getText().toString();
                        adapter_act.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();
                return true;
            }
        });


        ListView list_sai;
        list_sai = (ListView) findViewById(R.id.Liste_saison);
        String[] saisons = {"Saison 1", "Saison 2", "Saison 3", "Saison 4"};
        ArrayAdapter<String> adapter_sai = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, saisons);
        list_sai.setAdapter(adapter_sai);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void onStart(Bundle savedInstanceState) {

        System.out.println();
        System.out.print("bonjour on start");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
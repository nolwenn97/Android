package ul.iutmetz.filoche1u.projet_android;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
    private int position = 0;
    private AlertDialog.Builder alertBox = null;
    private final MainActivity self = this;
    // VIEWS
    private ListView list_act;
    private ListView list_sai;
    // VARIABLES
    private String[] acteurs= {"Eliza Taylor", "Thomas McDonell", "Marie Avgeropoulos", "Henry Ian Cusick", "Isaiah Washington", "Bob Morley"};
    private String[] saisons = {"Saison 1", "Saison 2", "Saison 3", "Saison 4"};
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // OVERRIDE
        super.onCreate(savedInstanceState);
        // AJOUT DE LAYOUT MAIN
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // RECUPERATION DES VIEWS
        list_act = (ListView) findViewById(R.id.liste_acteur);
        list_sai = (ListView) findViewById(R.id.Liste_saison);
        // CREATION DES ARRAYADAPTER
        final ArrayAdapter<String> adapter_act = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, acteurs);
        final ArrayAdapter<String> adapter_sai = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, saisons);
        // AJOUT DES ARRAY AUX VIEWS
        list_act.setAdapter(adapter_act);
        list_sai.setAdapter(adapter_sai);
        // LONG CLICK LISTENER
        list_act.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, int position, long id) {
                alertBox = new AlertDialog.Builder(self);
                final EditText edittext = new EditText(self);
                edittext.setText(acteurs[position]);
                self.position = position;
                alertBox.setView(edittext);
                alertBox.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        acteurs[self.position] = edittext.getText().toString();
                        adapter_act.notifyDataSetChanged();
                    }
                });
                alertBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alertBox.show();
                return true;
            }
        });
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

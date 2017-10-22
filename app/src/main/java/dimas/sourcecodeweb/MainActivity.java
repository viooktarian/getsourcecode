package dimas.sourcecodeweb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectInternetTask c1;
    static TextView myText;

    ConnectivityManager myConnManager;
    NetworkInfo myInfo;
    private Spinner sp;
    EditText ed;
    String edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.myResult);
        sp = (Spinner)findViewById(R.id.spinner);
        ed = (EditText)findViewById(R.id.editText);

        myConnManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        myInfo = myConnManager.getActiveNetworkInfo();
    }
    public boolean isOnline(){
        ConnectivityManager cm =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myInfo = cm.getActiveNetworkInfo();
        if(myInfo != null && myInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }

    public void doSomething(View view) {
        if(isOnline()== true) {
            c1 = new ConnectInternetTask(this);
            c1.execute(sp.getSelectedItem()+ ed.getText().toString());
        }
        else {
            Toast.makeText(this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
        }
    }

}

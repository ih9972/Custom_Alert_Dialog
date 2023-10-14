package com.example.customalertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        AdapterView.OnItemClickListener {
    AlertDialog.Builder builder;
    LinearLayout my_alert_dialog;
    EditText the_a1,the_d;
    Spinner spinner;
    String type_of_sires;
    ArrayAdapter<String> spinadp;
    String[] temp = {"arithmetic","geometric"};
    double a1,d,sn;
    ListView listV;
    TextView a1_display, d_display, n_display,sn_display;
    ArrayAdapter<Double> adp;
    Double[] arry = new Double[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        type_of_sires = "arithmetic";
        spinadp = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,temp);
        a1_display = (TextView) findViewById(R.id.textView5);
        d_display = (TextView) findViewById(R.id.textView7);
        n_display = (TextView) findViewById(R.id.textView9);
        sn_display = (TextView) findViewById(R.id.textView11);
        adp = new ArrayAdapter<Double>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arry);
        listV = (ListView) findViewById(R.id.listV);
        for(int i=0; i<20; i++){
            arry[i] = 0.0;
        }
        listV.setAdapter(adp);
        listV.setOnItemClickListener(this);
    }

    /**
     * Called when the  user clicks on  the dropdown    item    on the  spinner
     * Saves the choice the user made
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            type_of_sires = "arithmetic";
        }
        else {
            type_of_sires = "geometric";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Called when the user clicks on the "Enter Data" button.
     * It start the alert dialog
     * @param view
     */
    public void start(View view) {
        my_alert_dialog =(LinearLayout) getLayoutInflater().inflate(R.layout.my_alert_dialog,null);
        spinner = (Spinner) my_alert_dialog.findViewById(R.id.spinner);
        spinner.setAdapter(spinadp);
        spinner.setOnItemSelectedListener(this);
        the_a1 = (EditText) my_alert_dialog.findViewById(R.id.a1);
        the_d = (EditText) my_alert_dialog.findViewById(R.id.d);
        builder=new AlertDialog.Builder(this);
        builder.setView(my_alert_dialog);
        builder.setTitle("Enter Data Please");
        builder.setPositiveButton("Enter Data", clickListener);
        builder.setNegativeButton("Cancel",clickListener);
        builder.setNeutralButton("Clear",clickListener);
        builder.show();
    }

    /**
     * gets the data from the alert dialog
     * calculates the data and put it in the list view
     */
    DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (!(the_a1.getText().toString().equals("")) && !(the_d.getText().toString().equals(""))) {
                    a1 = Double.parseDouble(the_a1.getText().toString());
                    d = Double.parseDouble(the_d.getText().toString());
                    listV.setVisibility(View.VISIBLE);
                    a1_display.setText("" + a1);
                    d_display.setText("" + d);
                    if (type_of_sires.equals("arithmetic")) {
                        for (int i = 0; i < arry.length; i++) {
                            arry[i] = a1 + (i * d);
                        }
                    }
                    else if (type_of_sires.equals("geometric")) {
                        for (int i = 0; i < arry.length; i++) {
                            arry[i] = a1 * Math.pow(d, (i));
                        }
                    }
                    listV.setAdapter(adp);
                    dialog.cancel();
                }
                else{
                    Toast.makeText(my_alert_dialog.getContext(),"answer all the fields", Toast.LENGTH_LONG).show();
                }
            }
            if (which == DialogInterface.BUTTON_NEUTRAL) {
                a1_display.setText("");
                d_display.setText("");
                n_display.setText("");
                sn_display.setText("");
                listV.setVisibility(View.GONE);
                dialog.cancel();
            }
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            }
        }
    };

    /**
     * This method is called when an item is clicked in the list view
     * it calculates the index of the item and the sum of the series and displays it.
     * @param parent The AdapterView where the click happened.
     * @param view The view within the AdapterView that was clicked (this
     *            will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        n_display.setText(""+(position+1) );
        if(type_of_sires.equals("arithmetic")){
            sn=((2*a1+(d*position))*(position+1))/2;
        }
        else {
            if (d==1){
                sn = (position+1)*a1;
            }
            else{
                sn = a1*((Math.pow(d,(position+1))-1)/(d-1));
            }
        }
        sn_display.setText(""+sn);
    }

    /**
     * this method is called when the "cradits" button is clicked
     * it start an intent that will open a different activity
     * @param view
     */
    public void tocradits(View view) {
        Intent si = new Intent(this, MainActivity2.class);
        startActivity(si);
    }
}

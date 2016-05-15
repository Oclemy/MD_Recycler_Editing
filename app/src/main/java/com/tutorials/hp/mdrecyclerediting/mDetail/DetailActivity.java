package com.tutorials.hp.mdrecyclerediting.mDetail;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tutorials.hp.mdrecyclerediting.R;
import com.tutorials.hp.mdrecyclerediting.mData.CRUD;
import com.tutorials.hp.mdrecyclerediting.mData.Spacecraft;
import com.tutorials.hp.mdrecyclerediting.mData.SpacecraftsCollection;

public class DetailActivity extends AppCompatActivity {

    TextView nameTxt,descTxt;
    String name,desc;
    int pos;
    EditText nameEditTxt,descEditTxt;
    Button saveBtn,deleteBtn;
    CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        crud=new CRUD(SpacecraftsCollection.getSpacecrafts());

        nameTxt= (TextView) findViewById(R.id.nameTxtDetail);
        descTxt= (TextView) findViewById(R.id.descDetailTxt);

        //RECEIEVE DATA
        Intent i=this.getIntent();
        name=i.getExtras().getString("NAME_KEY");
        desc=i.getExtras().getString("DESC_KEY");
        pos=i.getExtras().getInt("POS_KEY");

        //BIND
        nameTxt.setText(name);
        descTxt.setText(desc);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               displayUpdateDialog();
            }
        });
    }

    //UPDATE OR DELETE
    private void displayUpdateDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("ADD DATA");
        d.setContentView(R.layout.dialog_layout);

        nameEditTxt= (EditText) d.findViewById(R.id.nameEditTxt);
        descEditTxt= (EditText) d.findViewById(R.id.descEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);
        deleteBtn= (Button) d.findViewById(R.id.deleteBtn);

        nameEditTxt.setText(name);
        descEditTxt.setText(desc);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spacecraft s = new Spacecraft();

                name = nameEditTxt.getText().toString();
                desc = descEditTxt.getText().toString();

                s.setName(name);
                s.setDescription(desc);

                if (crud.update(pos, s)) {
                    nameEditTxt.setText(name);
                    descEditTxt.setText(desc);

                    nameTxt.setText(name);
                    descTxt.setText(desc);
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(crud.delete(pos))
                {
                    //KILL THIS ACTIVITY and go back to master activity
                    DetailActivity.this.finish();

                }
            }
        });

        d.show();
    }

}

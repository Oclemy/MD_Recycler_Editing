package com.tutorials.hp.mdrecyclerediting;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.tutorials.hp.mdrecyclerediting.mData.CRUD;
import com.tutorials.hp.mdrecyclerediting.mData.Spacecraft;
import com.tutorials.hp.mdrecyclerediting.mData.SpacecraftsCollection;
import com.tutorials.hp.mdrecyclerediting.mRecycler.MyAdapter;


public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    MyAdapter adapter;
    CRUD crud;

    EditText nameEditTxt,descEditTxt;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        crud=new CRUD(SpacecraftsCollection.getSpacecrafts());
        adapter=new MyAdapter(this,crud.getSpacecrafts());

        //rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });
    }

    //ADD DATA
    //SHOW DIALOG
    private void displayDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("ADD DATA");
        d.setContentView(R.layout.dialog_layout);

        nameEditTxt= (EditText) d.findViewById(R.id.nameEditTxt);
        descEditTxt= (EditText) d.findViewById(R.id.descEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spacecraft s=new Spacecraft();
                s.setName(nameEditTxt.getText().toString());
                s.setDescription(descEditTxt.getText().toString());

                if(crud.addNew(s))
                {
                    nameEditTxt.setText("");
                    descEditTxt.setText("");

                    rv.setAdapter(adapter);

                }
            }
        });

        d.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        rv.setAdapter(adapter);
    }
}










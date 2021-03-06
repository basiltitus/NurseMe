package com.example.nurseme;

import android.app.LauncherActivity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    EditText location;
    ImageView emptyimg,empimg2;
    int emp=0;
    TextView emptytext,emptext2;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<NurseRecyclerClass> listItems;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        emptyimg=findViewById(R.id.ic_searchfoundnothing);
        emptytext=findViewById(R.id.textView24);
        emptext2=findViewById(R.id.textView64);
        empimg2=findViewById(R.id.ic_searchicon);
        location=findViewById(R.id.editText2);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        sp=findViewById(R.id.spinner3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems   =   new ArrayList<>();
       /* String[] districts=getResources().getStringArray(R.array.districts);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,districts);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.se
    */}
    public void locationsearch(View v)
    {listItems.clear();

    emp=0;
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
        Query query2 = reference2.child("NursePersonalInfo").orderByChild("locality").equalTo(location.getText().toString().toLowerCase());
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {



                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            NursePersonalInfo n = dataSnapshot1.getValue(NursePersonalInfo.class);
                            if(n.getStatus().equals("nil")) {
                                emptyimg.setVisibility(View.GONE);
                                empimg2.setVisibility(View.GONE);
                                emptext2.setVisibility(View.GONE);
                                emptytext.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                NurseRecyclerClass nrc = new NurseRecyclerClass(n.getName(), n.getLocality(), n.getEmail());
                                listItems.add(nrc);
                                //Toast.makeText(SearchActivity.this, "1." + n.getName(), Toast.LENGTH_SHORT).show();
                            } }
                    } catch (Exception e) {
                        Toast.makeText(SearchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    emp=1;

                }

                adapter=new NurseSearchAdapterClass(listItems,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(emp==1)
        {
            emptytext.setVisibility(View.VISIBLE);
            emptyimg.setVisibility(View.VISIBLE);
        }
checksize();
    }
    public void districtsearch(View v){
        emp=0;
        listItems.clear();
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
        Query query2 = reference2.child("NursePersonalInfo").orderByChild("district").equalTo(sp.getSelectedItem().toString());
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    try {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            NursePersonalInfo n = dataSnapshot1.getValue(NursePersonalInfo.class);
                            if(n.getStatus().equals("nil")) {
                                empimg2.setVisibility(View.GONE);
                                emptext2.setVisibility(View.GONE);
                                emptyimg.setVisibility(View.GONE);
                                emptytext.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                NurseRecyclerClass nrc = new NurseRecyclerClass(n.getName(), n.getLocality(), n.getEmail());
                                listItems.add(nrc);

                              //  Toast.makeText(SearchActivity.this, "2." + n.getName(), Toast.LENGTH_SHORT).show();
                            } }
                    } catch (Exception e) {
                        Toast.makeText(SearchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    emp=1;
                }

                adapter=new NurseSearchAdapterClass(listItems,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(emp==1)
        {
            emptytext.setVisibility(View.VISIBLE);
            emptyimg.setVisibility(View.VISIBLE);
        }
        checksize();
    }
    public void checksize()
    {
        if( listItems.size()==0)
        {
            emptytext.setVisibility(View.VISIBLE);
            emptyimg.setVisibility(View.VISIBLE);
            empimg2.setVisibility(View.GONE);
            emptext2.setVisibility(View.GONE);

        }
    }
}
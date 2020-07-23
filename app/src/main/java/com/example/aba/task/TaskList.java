package com.example.aba.task;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aba.R;
import com.example.aba.comment.days.DayComment;
import com.example.aba.users.UserDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class TaskList extends AppCompatActivity {
    ListView taskList;
    String idSelected;

    TextView noTaskText;
    ArrayList<String> allTask = new ArrayList<>();
    ArrayList<String> selectedItems = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;
    DatabaseReference reff;
    Calendar calendar =Calendar.getInstance();
    String currentData = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        reff = FirebaseDatabase.getInstance().getReference().child("users/"+ UserDetails.username);



        taskList = findViewById(R.id.usersList);
        noTaskText = findViewById(R.id.noUsersText);
        taskList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.checkbox_listview,R.id.usersList);
        taskList.setAdapter(adapter);
        ImageView addKid =  findViewById(R.id.addKid);

        addKid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openComment();
            }
        });
        pd = new ProgressDialog(TaskList.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://ionkid-abd2f.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });


        RequestQueue rQueue = Volley.newRequestQueue(TaskList.this);
        rQueue.add(request);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String selectedItem=((TextView)view).getText().toString();
               idSelected= String.valueOf(get(position)+1);
               if(selectedItems.contains(selectedItem)){
                   selectedItems.remove(selectedItem);
                   reff.child("kids/").child(UserDetails.kidName).child("tasks/"+currentData).child("status").child(String.valueOf(get(position+1))).setValue("unchecked");
                   Toast.makeText(TaskList.this,idSelected,Toast.LENGTH_LONG).show();

               }else {
                   selectedItems.add(selectedItem);
                   reff.child("kids/").child(UserDetails.kidName).child("tasks/"+currentData).child("status").child(String.valueOf(get(position+1))).setValue("checked");
                   Toast.makeText(TaskList.this,idSelected,Toast.LENGTH_LONG).show();
               }

            }
        });

    }



    private int get(int position) {
        return position;
    }


    public void doOnSuccess(String s) {
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()) {
                key = i.next().toString();

                if (!key.equals(UserDetails.username)) {
                    allTask.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (totalUsers <= 1) {
            noTaskText.setVisibility(View.VISIBLE);
            taskList.setVisibility(View.GONE);
        } else {
            noTaskText.setVisibility(View.GONE);
            taskList.setVisibility(View.VISIBLE);
            taskList.setAdapter(new ArrayAdapter<String>(this, R.layout.checkbox_listview, allTask));
        }

        pd.dismiss();
    }
    public void openComment(){

        Intent intent = new Intent(this, DayComment.class);
        startActivity(intent);
    }
}
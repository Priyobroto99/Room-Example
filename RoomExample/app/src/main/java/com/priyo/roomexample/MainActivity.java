package com.priyo.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.priyo.roomexample.db.Contact;
import com.priyo.roomexample.db.ContactsAppDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ContactsAppDatabase contactsAppDatabase;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();

    TextView txt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.text);
        contactsAppDatabase= Room.databaseBuilder(getApplicationContext(),ContactsAppDatabase.class,"ContactDB")
                .allowMainThreadQueries().build();
        //contactArrayList.addAll(contactsAppDatabase.getContactDAO().getContacts());

       /* String s="";
        for(Contact c: contactArrayList){
            s= s= c.getName() + "\n";

            Log.i("name",c.getName());
            Log.i("email",c.getEmail());
            Log.i("id",c.getId()+"");
        }

        txt.setText(s);*/

    }

    private void deleteContact(Contact contact, int position) {

        contactArrayList.remove(position);
        contactsAppDatabase.getContactDAO().deleteContact(contact);
        //contactsAdapter.notifyDataSetChanged();
    }

    private void updateContact(String name, String email, int position) {

        Contact contact = contactArrayList.get(position);

        contact.setName(name);
        contact.setEmail(email);

        contactsAppDatabase.getContactDAO().updateContact(contact);

        contactArrayList.set(position, contact);

        //contactsAdapter.notifyDataSetChanged();


    }

    private void createContact(String name, String email) {

        long id = contactsAppDatabase.getContactDAO().addContact(new Contact(0,name, email));


        Contact contact = contactsAppDatabase.getContactDAO().getContact(id);

        if (contact != null) {

            contactArrayList.add(0, contact);
           // contactsAdapter.notifyDataSetChanged();

        }

    }
}

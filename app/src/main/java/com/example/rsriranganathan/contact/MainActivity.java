package com.example.rsriranganathan.contact;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ListView list;
    String [] names;
    String [] numbers;
    Button sortbutton;
    Button searchbutton;
    int order=0;

    int[] images = {R.drawable.messi,R.drawable.neymar,R.drawable.suarez,R.drawable.rakitic,R.drawable.iniesta,R.drawable.xavi,R.drawable.alba,R.drawable.mascherano,R.drawable.pique,R.drawable.alves,R.drawable.bravo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sortbutton= (Button) findViewById(R.id.sort_button);
        sortbutton.setOnClickListener(this);
        searchbutton= (Button) findViewById(R.id.search_button);
        searchbutton.setOnClickListener(this);

        Resources res = getResources();
        names=res.getStringArray(R.array.CAMPions);
        numbers=res.getStringArray(R.array.NOUmbers);
        list = (ListView) findViewById(R.id.contact_list);
        custom_adapter adapter= new custom_adapter(this,names,images,numbers);
        list. setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.sort_button)
        {
            int i, j, tempimg;
        String tempname, tempno;
        if ((order % 2) == 0) {
            for (i = 0; i < (names.length - 1); i++) {
                for (j = 0; j < names.length - i - 1; j++) {
                    if (names[j + 1].compareTo(names[j]) < 0) {
                        tempname = names[j];
                        names[j] = names[j + 1];
                        names[j + 1] = tempname;
                        tempimg = images[j];
                        images[j] = images[j + 1];
                        images[j + 1] = tempimg;
                        tempno = numbers[j];
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = tempno;

                    }
                }
            }
            order++;
            Button sortb= (Button) findViewById(R.id.sort_button);
            sortb.setText("SORT---DESCENDING");

        } else {
            for (i = 0; i < (names.length - 1); i++) {
                for (j = 0; j < names.length - i - 1; j++) {
                    if (names[j + 1].compareTo(names[j]) > 0) {
                        tempname = names[j];
                        names[j] = names[j + 1];
                        names[j + 1] = tempname;
                        tempimg = images[j];
                        images[j] = images[j + 1];
                        images[j + 1] = tempimg;
                        tempno = numbers[j];
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = tempno;

                    }
                }
            }
            order++;
            Button sortb= (Button) findViewById(R.id.sort_button);
            sortb.setText("SORT---ASCENDING");
        }
        list = (ListView) findViewById(R.id.contact_list);
        custom_adapter adapter = new custom_adapter(this, names, images, numbers);
        list.setAdapter(adapter);
    }
        else if(v.getId()==R.id.search_button)
       {
           String query;
           int flag=0;
          EditText search= (EditText) findViewById(R.id.search_name);
          query = String.valueOf(search.getText());
          for(int i=0;i<names.length;i++)
          {
              if(query.equalsIgnoreCase(names[i]))
              {   flag++;
                  Toast.makeText(MainActivity.this,"contact found:"+names[i],Toast.LENGTH_LONG).show();
                  break;
              }

          }
           if(flag==0)
           {
               Toast.makeText(MainActivity.this,"contact not found",Toast.LENGTH_LONG).show();
           }
       }



    }
}

class custom_adapter extends ArrayAdapter<String>
{
    Context context;
    int[] photo;
    String[] players;
    String[] shirt;
    custom_adapter(Context c,String[] names,int[] i,String[] nums )
    {
       super(c, R.layout.custom_row, R.id.contact_name, names);
       this.context=c;
        photo=i;
        players=names;
        shirt=nums;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        if(row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             row = inflater.inflate(R.layout.custom_row, parent, false);
        }
        ImageView img= (ImageView) row.findViewById(R.id.contact_image);
        TextView singlename = (TextView) row.findViewById(R.id.contact_name);
        TextView singleno= (TextView) row.findViewById(R.id.contact_no);
        img.setImageResource(photo[position]);
        singlename.setText(players[position]);
        singleno.setText(shirt[position]);
        return row;
    }
}

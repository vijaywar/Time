package com.moonlight.time;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class detailes extends AppCompatActivity {
    ListView layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes);
        layout=findViewById(R.id.listview);
        DatabaseVi vi=new DatabaseVi(this);
  //      ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
     //           R.layout.item_view, R.id.itemTextView,vi.getAllContacts() );
        CustomListAdapter adapterinstance=new CustomListAdapter(this,vi.getAllContacts());
        layout.setAdapter(adapterinstance);
    }
}


class CustomListAdapter extends BaseAdapter {

    Activity activity;
    ArrayList <Contact> customListDataModelArrayList =  new ArrayList<Contact>();
    LayoutInflater layoutInflater = null;

    public CustomListAdapter(Activity activity, ArrayList customListDataModelArray){
        this.activity=activity;
        this.customListDataModelArrayList = customListDataModelArray;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return customListDataModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return customListDataModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder{
        RatingBar rating;
        TextView tv_name,tv_discription;

    }
    ViewHolder viewHolder = null;

    // this method  is called each time for arraylist data size.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View vi=view;
        final int pos = position;
        if(vi == null){

            // create  viewholder object for list_rowcell View.
            viewHolder = new ViewHolder();
            // inflate list_rowcell for each row
            vi = layoutInflater.inflate(R.layout.adapter,null);
            viewHolder.rating = (RatingBar) vi.findViewById(R.id.ratingBar2);
            viewHolder.tv_name = (TextView) vi.findViewById(R.id.textView);
            //viewHolder.tv_discription = (TextView) vi.findViewById(R.id.tv_discription);
            /*We can use setTag() and getTag() to set and get custom objects as per our requirement.
            The setTag() method takes an argument of type Object, and getTag() returns an Object.*/
            vi.setTag(viewHolder);
        }else {

            /* We recycle a View that already exists */
            viewHolder= (ViewHolder) vi.getTag();
        }
        Contact data=customListDataModelArrayList.get(pos);
       // viewHolder.image_view.setImageResource(customListDataModelArrayList.get(pos).getImage_id());
        viewHolder.tv_name.setText(data.getName());
      //  System.out.println(data.getPhoneNumber());
        viewHolder.rating.setRating( Float.parseFloat(data.getPhoneNumber()));
       // viewHolder.tv_discription.setText(customListDataModelArrayList.get(pos).getImageDiscription());

        return vi;
    }
}
package com.example.user.familycyclefinal.Kazi;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.familycyclefinal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OfficeofKazis extends AppCompatActivity {

    HashMap<String,List<String>> myHeader;
    List<String> myChild;
    ExpandableListView explist;
     MyAdapterKazioffice adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officeof_kazis);

        explist = (ExpandableListView)findViewById(R.id.expandable_listvieww);
        myHeader =   MyAdapterKazioffice.DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new  MyAdapterKazioffice(getApplication(),myHeader,myChild);
        explist.setAdapter(adapter);



    }
}



class  MyAdapterKazioffice extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> childTitles;
    private List<String> headTitles;


  /*   MyAdapterKazioffice(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {
        this.ctx = ctx;
        this.childTitles = childTitles;
        this.headTitles = headTitles;
    } */

     MyAdapterKazioffice(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {

        this.ctx = ctx;
        this.childTitles = childTitles;
        this.headTitles = headTitles;
    }

    @Override
    public int getGroupCount() {
        return headTitles.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childTitles.get(headTitles.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return headTitles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childTitles.get(headTitles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title = (String) this.getGroup(i);
        if(view==null){
            LayoutInflater inflater = (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.header_expandable_custom,null);  // header declared
        }

        //GET GROUP/TEAM ITEM

        TextView txt = (TextView) view.findViewById(R.id.idheaderrrr);
        txt.setTypeface(null, Typeface.BOLD);
        txt.setText(title);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);

        String header;
        header = getGroup(i).toString();

        /*
            HeaderDetails.put("Kazi A.K.M Abdussalam", ChildDetails1);
            HeaderDetails.put("Kazi A.K.M Mahbubur Rahman", ChildDetails2);
            HeaderDetails.put("Kazi Abul Kashem Mohammad Mohibullah", ChildDetails3);
            // first three kazi
            // http://borbodhu.com/index.php/directory_listing/directory_category/kazi_office
            HeaderDetails.put("Mogbazar Kazi (Marriage Registrar) Office", ChildDetails4);

         */


        if (header == "Kazi A.K.M Abdussalam"){
            imageView.setImageResource(R.drawable.signup_kazi);
        }
        if (header == "Kazi A.K.M Mahbubur Rahman"){
            imageView.setImageResource(R.drawable.signup_kazi);
        }
        if (header == "Kazi Abul Kashem Mohammad Mohibullah"){
            imageView.setImageResource(R.drawable.signup_kazi);
        }
        if (header == "Mogbazar Kazi (Marriage Registrar) Office"){
            imageView.setImageResource(R.drawable.kazioffice);
        }



        return  view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String title = (String) this.getChild(i,i1);

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.childitems_expandable_custom,null); // childitems declared
        }
        TextView txt = (TextView) view.findViewById(R.id.idchildddd);
        txt.setText(title);
        return  view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public static class DataProvider {

        public static HashMap<String, List<String>> getInfo() {
            HashMap<String, List<String>> HeaderDetails = new HashMap<String, List<String>>();

            List<String> ChildDetails1 = new ArrayList<String>();
           ChildDetails1.add("Address : Ward 68 99, Ohiullah Road, Armani Tola, Ketwali, Dhaka \n" + "Ketwali,Dhaka");
           ChildDetails1.add("Services : Kazi Office");
           ChildDetails1.add("Business hours : 09am-11pm");

            List<String> ChildDetails2 = new ArrayList<String>();
            ChildDetails2.add("Address : Ward 82 56/A, Haricharan Roy Road Mill Barak, Sutrapur, Dhaka \n" +
                    "Sutrapur,Dhaka");
            ChildDetails2.add("Services : Kazi Office ");
            ChildDetails2.add("Business hours : 09am-11pm");


            List<String> ChildDetails3 = new ArrayList<String>();
            ChildDetails3.add("Address : Ward 92 House 343/433, Lane-3, Section-7, Pallabi, Mirpur,Dhaka 8/12, Pallabi Mirpur \n" +
                    "Mirpur,Dhaka");
            ChildDetails3.add("Services : Kazi Office ");
            ChildDetails3.add("Business hours : 09am-11pm");

            List<String> ChildDetails4 = new ArrayList<String>();
            ChildDetails4.add("Address: 109, Mogbazar Kazi office Lane, Ramna");
            ChildDetails4.add("Ph: 9331282, 8317565");



            HeaderDetails.put("Kazi A.K.M Abdussalam", ChildDetails1);
            HeaderDetails.put("Kazi A.K.M Mahbubur Rahman", ChildDetails2);
            HeaderDetails.put("Kazi Abul Kashem Mohammad Mohibullah", ChildDetails3);
            // first three kazi
            // http://borbodhu.com/index.php/directory_listing/directory_category/kazi_office
            HeaderDetails.put("Mogbazar Kazi (Marriage Registrar) Office", ChildDetails4);


            return HeaderDetails;
        }
    }
}








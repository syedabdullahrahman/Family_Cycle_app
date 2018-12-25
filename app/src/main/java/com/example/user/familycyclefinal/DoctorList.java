package com.example.user.familycyclefinal;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorList extends AppCompatActivity {

    HashMap<String,List<String>> myHeader;
    List<String> myChild;
    ExpandableListView explist;
    MyAdapterDoctorsList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        explist = (ExpandableListView)findViewById(R.id.expandable_listvieww);
        myHeader =   MyAdapterDoctorsList.DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new  MyAdapterDoctorsList(getApplication(),myHeader,myChild);
        explist.setAdapter(adapter);

    }
}



class  MyAdapterDoctorsList extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> childTitles;
    private List<String> headTitles;


  /*   MyAdapterDoctorsList(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {
        this.ctx = ctx;
        this.childTitles = childTitles;
        this.headTitles = headTitles;
    } */

    MyAdapterDoctorsList(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {

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
             HeaderDetails.put("Professor Dr. Selim Shakur", ChildDetails1);
            HeaderDetails.put("Professor Dr. Syed Khairul Amin", ChildDetails2);
            HeaderDetails.put("Professor Dr. Mohammed Istiaque Hossain", ChildDetails3);
            // first three kazi
            // http://borbodhu.com/index.php/directory_listing/directory_category/kazi_office
            HeaderDetails.put("Professor Dr. Ekhlasur Rahman", ChildDetails4);
            HeaderDetails.put("Professor Golam Maeen Uddin", ChildDetails5);
            HeaderDetails.put("Professor Dr. Karim Khan", ChildDetails6);
            HeaderDetails.put("Professor Dr. Shafiqul Hoque", ChildDetails7);

         */


        if (header == "Professor Dr. Selim Shakur"){
            imageView.setImageResource(R.drawable.doctor_list);
        }
        if (header == "Professor Dr. Syed Khairul Amin"){
            imageView.setImageResource(R.drawable.doctor_list);
        }
        if (header == "Professor Dr. Mohammed Istiaque Hossain"){
            imageView.setImageResource(R.drawable.doctor_list);
        }
        if (header == "Professor Dr. Ekhlasur Rahman"){
            imageView.setImageResource(R.drawable.doctor_list);
        }
        if (header == "Professor Golam Maeen Uddin"){
            imageView.setImageResource(R.drawable.doctor_list);
        }
        if (header == "Professor Dr. Karim Khan"){
            imageView.setImageResource(R.drawable.doctor_list);
        }
        if (header == "Professor Dr. Shafiqul Hoque"){
            imageView.setImageResource(R.drawable.doctor_list);
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
            ChildDetails1.add("Qualification: MBBS, FRCPCH ( UK ), FRCP ( London )\n" +
                    "FRCP ( Edin ), FRCP ( Glasgow ), MRCP ( UK ),\n" +
                    "DCH ( Glasgow ), DCH ( Edin )\n" +
                    "Designation: Consultant & Head of the Department\n" +
                    "Department of Pediatrics\n" +
                    "Organization: United Hospital Limited, Gulshan, Dhaka, Bangladesh\n" +
                    "Chamber: United Hospital Limited\n" +
                    "Location: Plot # 15, Road # 71, Gulshan – 2, Dhaka-1212, Bangladesh\n" +
                    "Phone: +880-2-8836000, 88364444");

            List<String> ChildDetails2 = new ArrayList<String>();
            ChildDetails2.add("MBBS (Dhaka), DCH (Glasgow), MRCP (UK)\n" +
                    "FRCP (Edin), FRCP (Glasgow)\n" +
                    "Ex. Academic Director\n" +
                    "Bangladesh Institute of Child Health\n" +
                    "Dhaka Shishu Hospital\n" +
                    "Chamber: LABAID LIMITED (Annex)\n" +
                    "House No. 1, Road No. 4, Dhanmondi\n" +
                    "Dhaka-1205, Bangladesh.\n" +
                    "Phone : 8610793-8, 9670210-3, 8631177\n" +
                    "Mobile: 01916-957664 (For Appointment)");


            List<String> ChildDetails3 = new ArrayList<String>();
            ChildDetails3.add("Qualification: MBBS (DMC), DCH (Scotland), MRCP (UK), SBE (KSA), FRCP (Edin)\n" +
                    "Designation: Senior Consultant\n" +
                    "Organization: Apollo Hospitals Dhaka\n" +
                    "Chamber: Apollo Hospitals Dhaka\n" +
                    "Location: Plot # 81, Block # E, Bashundhara R/A, Dhaka – 1229, Bangladesh\n" +
                    "Phone: +880-2-8401661, Hotline – 10678");
            List<String> ChildDetails4 = new ArrayList<String>();
            ChildDetails4.add("Qualification : MBBS, FCPS, FRCS ( Edin )\n" +
                    "Designation: Professor & Head, Department of Pediatrics\n" +
                    "Organization: Dhaka Medical College & Hospital\n" +
                    "Chamber: Anwer Khan Modern Hospital Ltd.\n" +
                    "Location: House # 17, Road # 8, Dhanmondi, Dhaka – 1205, Bangladesh\n" +
                    "Phone: +880-2-9661213, 9670295, 9667985");
            List<String> ChildDetails5 = new ArrayList<String>();
            ChildDetails5.add("MBBS, FCPS (Child Disease), FRCP (Edin)\n" +
                    "Fellow – Child Kidney Disease, Australia\n" +
                    "Professor, Department of Child Health,\n" +
                    "Bangabandhu Sheikh Mujib Medical University (BSMMU) (PG Hospital)\n" +
                    "Chamber :\n" +
                    "Al-Raji Hospital (Pvt.) Ltd.\n" +
                    "12, Farmgate, Dhaka-1205, Bangladesh.\n" +
                    "Phone: 8121172, 9133563-4 (chamber)");
            List<String> ChildDetails6 = new ArrayList<String>();
            ChildDetails6.add("Qualification : MBBS, S.Paed ( Vienna), MCPS ( Child ),\n" +
                    "DCH ( Glasgow), DTM & H ( London ), MPH ( DU )\n" +
                    "Designation: Professor of Pediatric, Child Health\n" +
                    "Organization: Community Based Medical College\n" +
                    "Chamber: Renaissance Hospital & research Institute Ltd.\n" +
                    "Location: House # 60/A, Road # 4/A, Dhanmondi, Dhaka – 1205, Bangladesh\n" +
                    "Phone: +880-2-8626899, 9664930, +880 1711350724");
            List<String> ChildDetails7 = new ArrayList<String>();
            ChildDetails7.add("MBBS, FCPS, FICS, FACS (USA)\n" +
                    "Fellow in Paed. Surg. (Japan)\n" +
                    "Professor & Chairman\n" +
                    "Paediatric Surgery\n" +
                    "BSMMU\n" +
                    "Chamber: Central Hospital Limited\n" +
                    "House # 2, Road # 5, Green Road\n" +
                    "Dhanmondi, Dhaka.\n" +
                    "Phone: 9660015-19, 8624514-9\n" +
                    "\n" +
                    " ");



            HeaderDetails.put("Professor Dr. Selim Shakur", ChildDetails1);
            HeaderDetails.put("Professor Dr. Syed Khairul Amin", ChildDetails2);
            HeaderDetails.put("Professor Dr. Mohammed Istiaque Hossain", ChildDetails3);
            // first three kazi
            // http://borbodhu.com/index.php/directory_listing/directory_category/kazi_office
            HeaderDetails.put("Professor Dr. Ekhlasur Rahman", ChildDetails4);
            HeaderDetails.put("Professor Golam Maeen Uddin", ChildDetails5);
            HeaderDetails.put("Professor Dr. Karim Khan", ChildDetails6);
            HeaderDetails.put("Professor Dr. Shafiqul Hoque", ChildDetails7);


            return HeaderDetails;
        }
    }
}








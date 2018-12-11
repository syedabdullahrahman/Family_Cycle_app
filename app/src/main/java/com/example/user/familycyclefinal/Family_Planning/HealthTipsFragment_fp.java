package com.example.user.familycyclefinal.Family_Planning;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.familycyclefinal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HealthTipsFragment_fp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthTipsFragment_fp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthTipsFragment_fp extends Fragment {

    HashMap<String,List<String>> myHeader;
    List<String> myChild;
    ExpandableListView explist;
    MyAdapterHTipsFp adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HealthTipsFragment_fp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthTipsFragment_fp.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthTipsFragment_fp newInstance(String param1, String param2) {
        HealthTipsFragment_fp fragment = new HealthTipsFragment_fp();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_tips_fragment_fp, container, false);
        explist = (ExpandableListView)view.findViewById(R.id.expandable_listvieww);
        myHeader =  MyAdapterHTipsFp.DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new MyAdapterHTipsFp(getContext(),myHeader,myChild);
        explist.setAdapter(adapter);


        return view;
    }


    public static class MyAdapter extends ArrayAdapter {
        int [] imageArray;
        String [] titleArray;
        String [] descriptionArray;


        public MyAdapter(@NonNull Context context, String [] title1, String [] description1 , int [] img1) {
            super(context, R.layout.af_healthtips_custom_list_items,R.id.tipstitle,title1);
            this.imageArray=img1;
            this.descriptionArray=description1;
            this.titleArray=title1;
        }

        public  View getView(int position,View convertView,ViewGroup parent){

            //inflating the layout
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.af_healthtips_custom_list_items,parent,false);

            // get the reference to the view object
            ImageView myImage = (ImageView) row.findViewById(R.id.tipspic);
            TextView mTyitle = (TextView) row.findViewById(R.id.tipstitle);
            TextView myDescription = (TextView) row.findViewById(R.id.tipsdescription);

            // providing the elemnet of an array by specifying its position
            myImage.setImageResource(imageArray[position]);
            mTyitle.setText(titleArray[position]);
            myDescription.setText(descriptionArray[position]);


            return row;
        }
    }












    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(context,"Feature fragment",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}




class MyAdapterHTipsFp extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> childTitles;
    private List<String> headTitles;


  /*  MyAdapterHTipsFp(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {
        this.ctx = ctx;
        this.childTitles = childTitles;
        this.headTitles = headTitles;
    } */

    MyAdapterHTipsFp(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {

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
          HeaderDetails.put("Abstinence", ChildDetails1);
            HeaderDetails.put("Condoms", ChildDetails2);
            HeaderDetails.put("Birth Control Pills", ChildDetails3);
            HeaderDetails.put("Birth Control Ring", ChildDetails4);
            HeaderDetails.put("Surgery", ChildDetails5);
            HeaderDetails.put("Using basal body temparature method", ChildDetails6); // https://www.lybrate.com/topic/using-basal-body-temperature-method-for-natural-family-planning/aed9cf197757989032bae26156ca29d7?lpt=PS-HF
            HeaderDetails.put("Oral contraceptives", ChildDetails7); // https://www.marriage.com/advice/family/the-ultimate-guide-to-family-planning-key-questions-answered/
            HeaderDetails.put("Your birth control is your choice", ChildDetails8); // https://stayteen.org/sex-ed/article/5-tips-talking-your-doctor-about-birth-control


         */

        if (header == "Abstinence"){
            imageView.setImageResource(R.drawable.abstinence);
        }
        if (header == "Condoms"){
            imageView.setImageResource(R.drawable.condoms);
        }
        if (header == "Birth Control Pills"){
            imageView.setImageResource(R.drawable.birthcontrolpills);
        }
        if (header == "Birth Control Ring"){
            imageView.setImageResource(R.drawable.birthcontrolring);
        }
        if (header == "Using basal body temparature method"){
            imageView.setImageResource(R.drawable.basalbody);
        }
        if (header == "Oral contraceptives"){
            imageView.setImageResource(R.drawable.oralcontraceptives);
        }
        if (header == "Your birth control is your choice"){
            imageView.setImageResource(R.drawable.birth_control);
        }
        if (header == "Surgery"){
            imageView.setImageResource(R.drawable.surgery);
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
            ChildDetails1.add("Abstinence is a method of birth control that focuses on the act of avoiding sexual intercourse entirely. It is taught mainly to adolescents around the time of middle school, but can be utilized by people of all ages. Abstinence is referred to as the only method of family planning that is 100% effective in preventing pregnancy and protecting against sexually transmitted diseases (STDs). However, once a serious and intimate relationship arises, and the prospect of bring biological children into the home is considered, abstinence no longer remains a viable option of family planning.");


            List<String> ChildDetails2 = new ArrayList<String>();
            ChildDetails2.add("Condoms are perhaps the most popular form of birth control. Condoms are thin latex covering that are placed over the penis and create a barrier between the sperm and the vagina. Though the male version is vastly more popular, there are also female condoms available for general purchase and use.");

            List<String> ChildDetails3 = new ArrayList<String>();
            ChildDetails3.add("There are a plethora of birth control pills on the market, and along with condoms, these are an extremely popular choice for family planning. Birth control pills keep the woman�s ovaries from releasing eggs which prevents fertilization. It is estimated that birth control pills are 95% effective with standard use. Most pills must be taken at the same time every day to help eliminate the risk of getting pregnant, but there is also a type of pill that is taken continuously for 3 months. Birth control pills help prevent pregnancy but do not prevent the spread of STDs.");

            List<String> ChildDetails4 = new ArrayList<String>();
            ChildDetails4.add("The birth control ring is a relatively small, flexible ring that is placed within the vagina, where it releases a steady supply of progestin and estrogen hormones, preventing the ovaries from releasing eggs much like the birth control pill. The ring stays in the vagina for approximately three weeks, after which it is discarded. It is estimated to be 99% effective when used as prescribed. However the ring has been known to cause side effects such as weight gain and chronic nausea, and they do not aid in the prevention of the spread of STDs.");

            List<String> ChildDetails5 = new ArrayList<String>();
            ChildDetails5.add("Both men and woman can be sterilized through operation. For women, tubal ligation is the process of cutting off the fallopian tubes� access to releasing eggs into the uterus for fertilization. For men, a vasectomy is performed wherein the tubes that carry the sperm are blocked. Sterilization is estimated to be 100% effective but should be considered a permanent decision and is recommended for those patients who already have children.\n" + "\n" +"When it comes to family planning and birth control, there are myriad options to choose from. The above options are just a few of the many that exist. When choosing the option that is right for you and your significant other, it is best to consult your doctor about the different possibilities. Schedule an appointment online today and see your OBGYN about what form of contraceptive is right for you.");

            List<String> ChildDetails6 = new ArrayList<String>();
            ChildDetails6.add("When women wish to get pregnant, the basal body temperature can be used to determine the best days to perform sexual intercourse. On the other hand, women that want to avoid pregnancy can use this technique to help prevent unprotected intercourse. This method alone does not provide adequate warning time to prevent pregnancy efficiently; it should be used in combination with other fertility awareness methods when women want to avoid pregnancy.\n" + "\n" +"Tracking the basal body temperature for contraception or fertility reasons is not just economical but the method offers no side effects. Some women choose this method because it conforms to their religious beliefs.\n" + "This method can also be used to detect pregnancy. Immediately after ovulation, a rise in the basal body temperature (lasting for more than 18 days) can signal the onset of pregnancy.");

            List<String> ChildDetails7 = new ArrayList<String>();
            ChildDetails7.add("When used effectively, oral contraceptives (ie. the pill) may in fact have some beneficial effects on your health. Certain types of birth control pills may help to clear up acne, as well as regulating the menstrual period. For women who have suffered from heavy and painful periods, the pill can be an absolute blessing, as periods now become lighter, with hardly any cramps or other premenstrual symptoms. According to some studies, regular use of oral contraceptives can reduce the risk of ovarian cysts.");

            List<String> ChildDetails8 = new ArrayList<String>();
            ChildDetails8.add("Your health care provider can tell you a lot about different methods of contraception. What they can’t tell you is which one is best for you. What they also can’t do is make you stay on a method you’re trying out that you don’t like. While it’s true that most side effects will go away after a couples of months, if you’re not happy with a method you don’t have to keep using it. “In the end it is your body, and you have to live in it,” Says Dr. Kate. “Even if your doc counsels you to stick it out with a new method that’s giving you trouble, if you’re miserable, demand to find a new method. Even if that means taking out an IUD or implant.  You’re the boss when it comes to your body.");

           // first five tips from...
            // http://www.obgynwesthouston.com/family-planning-tips-and-birth-control.php

            HeaderDetails.put("Abstinence", ChildDetails1);
            HeaderDetails.put("Condoms", ChildDetails2);
            HeaderDetails.put("Birth Control Pills", ChildDetails3);
            HeaderDetails.put("Birth Control Ring", ChildDetails4);
            HeaderDetails.put("Surgery", ChildDetails5);
            HeaderDetails.put("Using basal body temparature method", ChildDetails6); // https://www.lybrate.com/topic/using-basal-body-temperature-method-for-natural-family-planning/aed9cf197757989032bae26156ca29d7?lpt=PS-HF
            HeaderDetails.put("Oral contraceptives", ChildDetails7); // https://www.marriage.com/advice/family/the-ultimate-guide-to-family-planning-key-questions-answered/
            HeaderDetails.put("Your birth control is your choice", ChildDetails8); // https://stayteen.org/sex-ed/article/5-tips-talking-your-doctor-about-birth-control


            return HeaderDetails;
        }
    }
}







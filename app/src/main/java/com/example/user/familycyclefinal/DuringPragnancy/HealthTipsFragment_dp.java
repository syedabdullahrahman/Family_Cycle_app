package com.example.user.familycyclefinal.DuringPragnancy;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * {@link HealthTipsFragment_dp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthTipsFragment_dp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthTipsFragment_dp extends Fragment {


    HashMap<String,List<String>> myHeader;
    List<String> myChild;
    ExpandableListView explist;
    MyAdapterHTipsDp adapter;






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HealthTipsFragment_dp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthTipsFragment_dp.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthTipsFragment_dp newInstance(String param1, String param2) {
        HealthTipsFragment_dp fragment = new HealthTipsFragment_dp();
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
        View view = inflater.inflate(R.layout.fragment_health_tips_fragment_dp, container, false);


        explist = (ExpandableListView)view.findViewById(R.id.expandable_listvieww);
        myHeader =  MyAdapterHTipsDp.DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new MyAdapterHTipsDp(getContext(),myHeader,myChild);
        explist.setAdapter(adapter);
        
        return view;
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



class MyAdapterHTipsDp extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> childTitles;
    private List<String> headTitles;


  /*  MyAdapterHTipsDp(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {
        this.ctx = ctx;
        this.childTitles = childTitles;
        this.headTitles = headTitles;
    } */

    MyAdapterHTipsDp(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {

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
         HeaderDetails.put("Staying Healthy During Pregnancy", ChildDetails1);
            HeaderDetails.put("Take a Prenatal Vitamin", ChildDetails2);
            HeaderDetails.put("Exercise", ChildDetails3);
            HeaderDetails.put("Write a Birth Plan", ChildDetails4);
            HeaderDetails.put("Educate Yourself", ChildDetails5);
            HeaderDetails.put("Practice Kegels", ChildDetails6);
            HeaderDetails.put("Change Up Chores", ChildDetails7);
            HeaderDetails.put("Track Your Weight Gain", ChildDetails8);

         */

        if (header == "Staying Healthy During Pregnancy"){
            imageView.setImageResource(R.drawable.preg_health);
        }
        if (header == "Take a Prenatal Vitamin"){
            imageView.setImageResource(R.drawable.vitamin);
        }
        if (header == "Exercise"){
            imageView.setImageResource(R.drawable.pregexercise);
        }
        if (header == "Write a Birth Plan"){
            imageView.setImageResource(R.drawable.birthplan);
        }
        if (header == "Educate Yourself"){
            imageView.setImageResource(R.drawable.pregeducation);
        }
        if (header == "Practice Kegels"){
            imageView.setImageResource(R.drawable.practicekegals);
        }
        if (header == "Change Up Chores"){
            imageView.setImageResource(R.drawable.changeupchores);
        }
        if (header == "Track Your Weight Gain"){
            imageView.setImageResource(R.drawable.prehweight);
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
            ChildDetails1.add("If you're pregnant or thinking about getting pregnant, you probably know some of the basic pregnancy advice about taking care of yourself and the baby: don't smoke or be around secondhand smoke, don't drink, and get your rest. Here are more pregnancy tips, from taking vitamins to what to do with the kitty litter, that can help ensure safe and healthy prenatal development.");

            List<String> ChildDetails2 = new ArrayList<String>();
            ChildDetails2.add("Even when you're still trying to conceive, it's smart to start taking prenatal vitamins. Your baby's neural cord, which becomes the brain and spinal cord, develops within the first month of pregnancy, so it's important you get essential nutrients, like folic acid, calcium, and iron, from the very start.Prenatal vitamins are available over the counter at most drug stores, or you can get them by prescription from your doctor. If taking them makes you feel queasy, try taking them at night or with a light snack. Chewing gum or sucking on hard candy afterward can help, too. ");

            List<String> ChildDetails3 = new ArrayList<String>();
            ChildDetails3.add("Staying active is important for your general health and can help you reduce stress, control your weight, improve circulation, boost your mood, and sleep better. Take a pregnancy exercise class or walk at least 15-20 minutes every day at a moderate pace, in cool, shaded areas or indoors in order to prevent overheating. \n" + "\n" + "Pilates, yoga, swimming, and walking are also great activities for most pregnant women, but be sure to check with your doctor first before starting any exercise program. Aim for 30 minutes of exercise most days of the week. Listen to your body, though, and don't overdo it.");

            List<String> ChildDetails4 = new ArrayList<String>();
            ChildDetails4.add("Determined to have a doula? Counting on that epidural? Write down your wishes and give a copy to everyone involved with the delivery. According to the American Pregnancy Association, here are some things to consider when writing your birth plan: - Who you want present, including children or siblings of the baby\n" +
                    "- Procedures you want to avoid\n" +
                    "- What positions you prefer for labor and delivery\n" +
                    "- Special clothing you'd like to wear\n" +
                    "- Whether you want music or a special focal point\n" +
                    "- Whether you want pain medications, and what kind\n" +
                    "- What to do if complications arise ");

            List<String> ChildDetails5 = new ArrayList<String>();
            ChildDetails5.add("Even if this isn't your first baby, attending a childbirth class will help you feel more prepared for delivery. Not only will you have the chance to learn more about childbirth and infant care, but you can ask specific questions and voice any concerns. You'll also become more acquainted with the facility and its staff. Now is also a good time to brush up on your family's medical history. Talk to your doctor about problems with past pregnancies, and report any family incidences of birth defects.");


            List<String> ChildDetails6 = new ArrayList<String>();
            ChildDetails6.add("Kegel exercises strengthen the pelvic floor muscles, which support your bladder, bowels, and uterus. Done correctly, this simple exercise can help make your delivery easier and prevent problems later with incontinence. The best part: No one can tell you're doing them, so you can practice kegels in the car, while you're sitting at your desk, or even standing in line at the grocery store. Here's how to do them right:\n" +
                    "\n" +
                    "- Practice squeezing as though you're stopping the flow of urine when you use the bathroom\n" +
                    "- Hold for three seconds, then relax for three\n" +
                    "- Repeat 10 times");


            List<String> ChildDetails7 = new ArrayList<String>();
            ChildDetails7.add("Even everyday tasks like scrubbing the bathroom or cleaning up after pets can become risky when you're pregnant. Exposure to toxic chemicals, lifting heavy objects, or coming in contact with bacteria can harm you and your baby. Here are some things to (hooray!) take off your to-do-list:\n" +
                    "\n" +
                    "- Heavy lifting\n" +
                    "- Climbing on stepstools or ladders\n" +
                    "- Changing kitty litter (to avoid toxoplasmosis, a disease caused by a parasite which cats can carry)\n" +
                    "- Using harsh chemicals\n" +
                    "- Standing for long periods of time, especially near a hot stove\n" +
                    "\n" +
                    "Also, wear gloves if you're working in the yard where cats may have been, and wash your hands thoroughly after handling raw meat.");



            List<String> ChildDetails8 = new ArrayList<String>();
            ChildDetails8.add("We know—you're eating for two. But packing on too many extra pounds may make them hard to lose later. At the same time, not gaining enough weight can put the baby at risk for a low-weight birth, a major cause of developmental problems. Recently the Institute of Medicine (IOM) issued new guidelines for weight gain during pregnancy. Here's what the IOM recommends, based on a woman's BMI (body mass index) before becoming pregnant with one baby:\n" +
                    "\n" +
                    "- Underweight: Gain 28-40 pounds\n" +
                    "- Normal weight: Gain 25-35 pounds\n" +
                    "- Overweight: Gain 15-25 pounds\n" +
                    "- Obese: Gain 11-20 pounds\n" +
                    "\n" +
                    "Check in with your doctor frequently to make sure you're gaining at a healthy rate.");

            HeaderDetails.put("Staying Healthy During Pregnancy", ChildDetails1);
            HeaderDetails.put("Take a Prenatal Vitamin", ChildDetails2);
            HeaderDetails.put("Exercise", ChildDetails3);
            HeaderDetails.put("Write a Birth Plan", ChildDetails4);
            HeaderDetails.put("Educate Yourself", ChildDetails5);
            HeaderDetails.put("Practice Kegels", ChildDetails6);
            HeaderDetails.put("Change Up Chores", ChildDetails7);
            HeaderDetails.put("Track Your Weight Gain", ChildDetails8);
           // HeaderDetails.put("Go Shoe Shopping", ChildDetails9);
           // HeaderDetails.put("Rethink Your Spa Style", ChildDetails10);
           // HeaderDetails.put("Eat Folate-Rich Foods", ChildDetails11);
           // HeaderDetails.put("Recharge with Fruit", ChildDetails12);
           // HeaderDetails.put("Go Fish", ChildDetails13);
           // HeaderDetails.put("Wear Sunscreen", ChildDetails14);
           // HeaderDetails.put("Travel Smart", ChildDetails15);
           // HeaderDetails.put("Say Yes to Cravings—Sometimes", ChildDetails16);



            return HeaderDetails;
        }
    }
}







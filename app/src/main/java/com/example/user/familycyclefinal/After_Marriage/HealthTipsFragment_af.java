package com.example.user.familycyclefinal.After_Marriage;

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
 * {@link HealthTipsFragment_af.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthTipsFragment_af#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthTipsFragment_af extends Fragment {

    HashMap<String,List<String>> myHeader;
    List<String> myChild;
    ExpandableListView explist;
    MyAdapterHTipsAf adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HealthTipsFragment_af() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthTipsFragment_af.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthTipsFragment_af newInstance(String param1, String param2) {
        HealthTipsFragment_af fragment = new HealthTipsFragment_af();
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
        View view = inflater.inflate(R.layout.fragment_health_tips_fragment_af, container, false);

        explist = (ExpandableListView)view.findViewById(R.id.expandable_listvieww);
        myHeader =  MyAdapterHTipsAf.DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new MyAdapterHTipsAf(getContext(),myHeader,myChild);
        explist.setAdapter(adapter);

        return  view;
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
            Toast.makeText(context,"Health tips fragment",Toast.LENGTH_SHORT).show();
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



class MyAdapterHTipsAf extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> childTitles;
    private List<String> headTitles;


  /*  MyAdapterHTipsAf(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {
        this.ctx = ctx;
        this.childTitles = childTitles;
        this.headTitles = headTitles;
    } */

    MyAdapterHTipsAf(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {

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

        if (header == "Respect"){
            imageView.setImageResource(R.drawable.respect);
        }
        if (header == "Honesty"){
            imageView.setImageResource(R.drawable.honesty);
        }
        if (header == "Quality Sex"){
            imageView.setImageResource(R.drawable.quality_sex);
        }
        if (header == "Closeness and Intemacy"){
            imageView.setImageResource(R.drawable.closeness_intimacy);
        }
        if (header == "Fight Fair"){
            imageView.setImageResource(R.drawable.fight_fair);
        }
        if (header == "Unity"){
            imageView.setImageResource(R.drawable.unity);
        }
        if (header == "Love Each Other"){
            imageView.setImageResource(R.drawable.loving_each_other);
        }
        if (header == "Relish the silence"){
            imageView.setImageResource(R.drawable.relishsilence);
        }

        // drop down moving right


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
            ChildDetails1.add("In a healthy marriage, each spouse respects all aspects of the other spouse.This is displayed in your tone, in the way you respond, your attitude,  and your ability to hold your spouse’s opinion higher than others (the exception is when they are wrong, morally, or ethically.");

            List<String> ChildDetails2 = new ArrayList<String>();
            ChildDetails2.add("There are no secrets in a healthy marriage.Honesty is the basis for trust. Trust is earned, it is never a right.Having an honest relationship creates a kind of buffer between you and the difficulties of the world. Having a mate you can trust and rely on also makes it easier to take those risks that help us grow.");


            List<String> ChildDetails3 = new ArrayList<String>();
            ChildDetails3.add(" Not talking about quantity here, as long as you both are happy with how often, the quality is what matters.");


            List<String> ChildDetails4 = new ArrayList<String>();
            ChildDetails4.add("Connecting and spending time with each other: Knowing on a deeper level, the connection between each other. This comes with hard work and time.You must cultivate your marriage relationship.");

            List<String> ChildDetails5 = new ArrayList<String>();
            ChildDetails5.add(" Married couples have disagreements.If a couple never disagrees about anything, I would be worried that they are not being as open and honest about everything with each other. ");

            List<String> ChildDetails6 = new ArrayList<String>();
            ChildDetails6.add(" They are working together to achieve their marriage goals and committed to each other.Seeing each other as equals, no one is above the other.Grow together as a couple while the marriage evolves ");

            List<String> ChildDetails7 = new ArrayList<String>();
            ChildDetails7.add(" Say I love you to one another. Hearing this is reassuring about the way your partner feels about your relationship. It's a small gesture that can keep your connection alive. Call each other during the day to say it if you miss your chance in the morning. ");

            List<String> ChildDetails8 = new ArrayList<String>();
            ChildDetails8.add("Sometimes the best way to address a problem is to just walk away from it — as in seriously let it go. Not every slight must be addressed. Know that not every insult is intended. Practice letting go as much as you can. Forgive more. Forget more. Bite your tongue until the tip bleeds. And once in a while, remind yourself of why you married this person. Focus on those reasons and let stuff pass without mention. \n" +
                    "\n" +
                    "The trick to successful silence, however, is that you really let the problem pass. If you stay silent and still harbor bad thoughts, well, that’s where ulcers come from. As the Beatles told us, “Let It Be.");

            HeaderDetails.put("Respect", ChildDetails1);
            HeaderDetails.put("Honesty", ChildDetails2);
            HeaderDetails.put("Quality Sex", ChildDetails3);
            HeaderDetails.put("Closeness and Intemacy", ChildDetails4);
            HeaderDetails.put("Fight Fair", ChildDetails5);
            HeaderDetails.put("Unity", ChildDetails6);
            HeaderDetails.put("Love Each Other", ChildDetails7);
            HeaderDetails.put("Relish the silence", ChildDetails8);


            return HeaderDetails;
        }
    }
}




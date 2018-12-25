package com.example.user.familycyclefinal.Family_Planning;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.familycyclefinal.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeaturesFragment_fp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeaturesFragment_fp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeaturesFragment_fp extends Fragment {

    GridView lv;
    String []titles = {"Ovulation and Period","Menstrual Calender",
            "Fertility analysis and window","Predict expected date of pregnancy",
            "Natural methods of avoiding pregnancy","Improve Womens health","Track weight",
            "Medication of Birth Control"};
    int [] images = {R.drawable.period,R.drawable.menstrual,
            R.drawable.fertility,R.drawable.pragnancy_test,
            R.drawable.natural_methods_avoiding_pregnancy,R.drawable.womens_health,R.drawable.track_weight,
            R.drawable.birth_control};



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FeaturesFragment_fp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeaturesFragment_fp.
     */
    // TODO: Rename and change types and number of parameters
    public static FeaturesFragment_fp newInstance(String param1, String param2) {
        FeaturesFragment_fp fragment = new FeaturesFragment_fp();
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
        View view= inflater.inflate(R.layout.fragment_features_fragment_fp, container, false);

        lv = (GridView) view.findViewById(R.id.idlistview);
        FeaturesFragment_fp.MyAdapter adapter = new FeaturesFragment_fp.MyAdapter(getActivity(),titles,images);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(getActivity(),ovulationperioid.class);
                    startActivity(intent);
                }
                if(i==1){
                    Intent intent = new Intent(getActivity(),Menustralcalender.class);
                    startActivity(intent);
                }
                if(i==2){
                    Intent intent = new Intent(getActivity(),Fertilitywindow.class);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent = new Intent(getActivity(),PredictPregnancy.class);
                    startActivity(intent);
                }
                if(i==4){
                    Intent intent = new Intent(getActivity(),NaturalMethodsForAvoingPregnancy.class);
                    startActivity(intent);
                }
                if(i==5){
                    Intent intent = new Intent(getActivity(),ImproveWomenHealth.class);
                    startActivity(intent);
                }
                if(i==6){
                    Intent intent = new Intent(getActivity(),TrackWeight.class);
                    startActivity(intent);
                }
                if(i==7){
                    Intent intent = new Intent(getActivity(),Medicationtobirthcontrol.class);
                    startActivity(intent);
                }
            }
        });



        return view;
    }


    class MyAdapter extends ArrayAdapter{
        int [] imageArray;
        String[] titlesArray;
        public MyAdapter(Context context,String [] titles1,int [] image1){
            super(context,R.layout.list_item,R.id.idtitle,titles1);
            this.imageArray = image1;
            this.titlesArray=titles1;
        }
        public View getView(int position, View convertView,ViewGroup parent){
            // Inflating the layout
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.list_itemgrid,parent,false);

            //get the refernces to the view object
            ImageView myImage = (ImageView)row.findViewById(R.id.idicon);
            TextView myTitle = (TextView)row.findViewById(R.id.idtitle);

            //providing the elements of an array by specifying its position
            myImage.setImageResource(imageArray[position]);
            myTitle.setText(titlesArray[position]);

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

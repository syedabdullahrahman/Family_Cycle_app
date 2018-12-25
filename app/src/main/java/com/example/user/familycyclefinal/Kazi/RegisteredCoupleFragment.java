package com.example.user.familycyclefinal.Kazi;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.familycyclefinal.R;
import com.example.user.familycyclefinal.internet.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisteredCoupleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisteredCoupleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisteredCoupleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<JSONObject> mEntries;

    // variable declare
    String[] regnumTitle ; //= {"Reg Num:", "Reg Num:"}
    String[] regnum ;//= {"123", "456"}
    String[] groomnameTitle ;//= {"Groom Name:", "Groom Name:"}
    String[] groomname ;//= {"shawon", "abdullah"}
    String[] bridenameTitle ;//= {"Bride Name", "Bride Name"}
    String[] bridename ;//= {"abc", "ema"}
    String[] dateTitle ;//= {"Date", "Date"}
    String[] date ;//= {"10-1-2018", "12-02-2017"}
    int[] images = {R.drawable.couple, R.drawable.couple} ;
    ListView lv;
    //SearchView searchView;
    ProgressDialog dialog;


    private OnFragmentInteractionListener mListener;

    public RegisteredCoupleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisteredCoupleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisteredCoupleFragment newInstance(String param1, String param2) {
        RegisteredCoupleFragment fragment = new RegisteredCoupleFragment();
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

        View view = inflater.inflate(R.layout.fragment_registered_couple, container, false);


        // custom listview er kaj part-1
        lv = (ListView) view.findViewById(R.id.couplelist);
    //    searchView = (SearchView)view.findViewById(R.id.searchRegistrationnumber);
        
       // searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        


        String url = "https://family-cycle.herokuapp.com/FamilyAssistance/marriageRegistration/all";

        mEntries = new ArrayList<>();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.show();

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                mEntries.add(jsonObject);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), "Network Error occurred !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                        //Toast.makeText(getContext(),"Response: " +mEntries.size(),Toast.LENGTH_SHORT).show();
                        regnumTitle = new String[mEntries.size()];
                        regnum = new String[mEntries.size()];
                        groomnameTitle = new String[mEntries.size()];
                        groomname = new String[mEntries.size()];
                        bridenameTitle = new String[mEntries.size()];
                        bridename = new String[mEntries.size()];
                        dateTitle = new String[mEntries.size()];
                        date = new String[mEntries.size()];
                        images = new int[mEntries.size()];

                        for (int i = 0; i < mEntries.size(); i++) {
                            regnumTitle[i] = "Registration Number:";
                            groomnameTitle[i] = "Groom Name:";
                            bridenameTitle[i] = "Bride Name";
                            dateTitle[i] = "Date of Marriage:";
                            images[i] =R.drawable.couple;
                            JSONObject j = mEntries.get(i);
                            try {
                                regnum[i] = j.getString("regNumber");
                                groomname[i] = j.getString("gName");
                                bridename[i] = j.getString("bName");
                                date[i]=j.getString("date");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        MyAdapter adapter = new MyAdapter(getActivity(), regnumTitle, regnum, groomnameTitle, groomname, bridenameTitle, bridename, dateTitle, date, images);
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Network Error occurred. Try again.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

        return view;
    }
    
    
    


    class MyAdapter extends ArrayAdapter {
        int[] imageArray;
        String[] regnumTitleArray;
        String[] regnumArray;
        String[] groomnameTitleArray;
        String[] groomnameArray;
        String[] bridenameTitleArray;
        String[] bridenameArray;
        String[] dateTitleArray;
        String[] dateArray;

        public MyAdapter(Context context, String[] r1, String[] r2, String[] g1, String[] g2, String[] b1,
                         String[] b2, String[] d1, String[] d2, int[] image1) {

            super(context, R.layout.itemof_couplelist, R.id.idreg_num, r1);
            this.imageArray = image1;
            this.regnumTitleArray = r1;
            this.regnumArray = r2;
            this.groomnameTitleArray = g1;
            this.groomnameArray = g2;
            this.bridenameTitleArray = b1;
            this.bridenameArray = b2;
            this.dateTitleArray = d1;
            this.dateArray = d2;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflating the layout
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.itemof_couplelist, parent, false);

            //get the refernces to the view object
            ImageView myImage = (ImageView) row.findViewById(R.id.idicon);
            TextView myregnumTitle = (TextView) row.findViewById(R.id.idreg_num);
            TextView myregnum = (TextView) row.findViewById(R.id.regnum_ashbe);
            TextView mygroomnameTitle = (TextView) row.findViewById(R.id.idgroom_name);
            TextView mygroomname = (TextView) row.findViewById(R.id.groomname_ashbe);
            TextView mybridenameTitle = (TextView) row.findViewById(R.id.idbride_name);
            TextView mybridename = (TextView) row.findViewById(R.id.bridename_ashbe);
            TextView mydateTitle = (TextView) row.findViewById(R.id.id_date);
            TextView mydate = (TextView) row.findViewById(R.id.date_ashbe);


            //providing the elements of an array by specifying its position
            myImage.setImageResource(imageArray[position]);

            myregnumTitle.setText(regnumTitleArray[position]);
            myregnum.setText(regnumArray[position]);
            mygroomnameTitle.setText(groomnameTitleArray[position]);
            mygroomname.setText(groomnameArray[position]);
            mybridenameTitle.setText(bridenameTitleArray[position]);
            mybridename.setText(bridenameArray[position]);
            mydateTitle.setText(dateTitleArray[position]);
            mydate.setText(dateArray[position]);

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
            //Toast.makeText(context, "Registered Married Couple", Toast.LENGTH_SHORT).show();
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


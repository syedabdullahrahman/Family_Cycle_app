package com.example.user.familycyclefinal.Family_Planning;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
 * {@link QueryFragment_fp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QueryFragment_fp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryFragment_fp extends Fragment {


    String[] noteTitle ;
    String[] noteDescription ;

    private ArrayList<JSONObject> mEntries;

    ListView lv;
    ProgressDialog dialog;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public QueryFragment_fp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryFragment_fp.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryFragment_fp newInstance(String param1, String param2) {
        QueryFragment_fp fragment = new QueryFragment_fp();
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
        View view = inflater.inflate(R.layout.fragment_query_fragment_fp, container, false);



        lv = (ListView) view.findViewById(R.id.usernotelist);

        String url = "https://family-cycle.herokuapp.com/FamilyAssistance/post/all";

        mEntries = new ArrayList<>();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Setting up list");
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

                        Toast.makeText(getContext(),"Response: " +mEntries.size(),Toast.LENGTH_SHORT).show();
                        noteTitle = new String[mEntries.size()];

                        noteDescription = new String[mEntries.size()];

                        for (int i = 0; i < mEntries.size(); i++) {


                            JSONObject j = mEntries.get(i);
                            try {
                                noteTitle[i] = j.getString("title");
                                noteDescription[i] = j.getString("description");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        QueryFragment_fp.MyAdapter adapter = new QueryFragment_fp.MyAdapter(getActivity(), noteTitle, noteDescription);
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);




        return view;
    }




    class MyAdapter extends ArrayAdapter {
        //int[] imageArray;
        String[] noteTitleArray;
        String[] noteDescriptionArray;

        public MyAdapter(Context context, String[] r1, String[] r2) {

            super(context, R.layout.list_for_note, R.id.idtitle, r1); // ????
            //this.imageArray = image1;
            this.noteTitleArray = r1;
            this.noteDescriptionArray = r2;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflating the layout
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.list_for_note, parent, false);

            //get the refernces to the view object
            // ImageView myImage = (ImageView) row.findViewById(R.id.idicon);

            TextView mynoteTitle = (TextView) row.findViewById(R.id.idtitle);
            TextView mynoteDescription = (TextView) row.findViewById(R.id.iddescription);



            //providing the elements of an array by specifying its position
            //myImage.setImageResource(imageArray[position]);

            mynoteTitle.setText(noteTitleArray[position]);
            mynoteDescription.setText(noteDescriptionArray[position]);


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

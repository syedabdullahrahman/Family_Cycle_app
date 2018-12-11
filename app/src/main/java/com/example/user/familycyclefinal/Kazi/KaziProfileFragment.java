package com.example.user.familycyclefinal.Kazi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.familycyclefinal.R;
import com.example.user.familycyclefinal.internet.JsonObjectRequest;
import com.example.user.familycyclefinal.internet.MySingleton;
import com.example.user.familycyclefinal.objects.Kazi;
import com.example.user.familycyclefinal.objects.currentUser;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KaziProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KaziProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KaziProfileFragment extends Fragment {

    ImageView edit;
    ProgressDialog dialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KaziProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KaziProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KaziProfileFragment newInstance(String param1, String param2) {
        KaziProfileFragment fragment = new KaziProfileFragment();
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
        final View view = inflater.inflate(R.layout.fragment_kazi_profile, container, false);

        edit = (ImageView) view.findViewById(R.id.imageView23);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KaziProfileEdit.class);
                startActivity(intent);
            }
        });
        dialog = new ProgressDialog(getContext());

        dialog.setMessage("Loading...");
        dialog.show();

        final TextView kazirname = view.findViewById(R.id.kazirname);
        final TextView kaziredu = view.findViewById(R.id.kaziredu);
        final TextView kazidob = view.findViewById(R.id.kazidob);
        final TextView kazinid = view.findViewById(R.id.kazinid);
        final TextView kazitin = view.findViewById(R.id.kazitin);
        final TextView kaziremail = view.findViewById(R.id.kaziremail);
        final TextView kazirlicense = view.findViewById(R.id.kazirlicense);
        final TextView kaziroffice = view.findViewById(R.id.kaziroffice);
        final TextView kazirarea = view.findViewById(R.id.kazirarea);
        final TextView kazirnumber = view.findViewById(R.id.kazirnumber);


        final Kazi k = currentUser.getInstance().getKazi();
        String url = "https://family-cycle.herokuapp.com/FamilyAssistance/kazi/findkazi?license=" + k.getKaziLicenceNumber();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Kazi tmp = new Kazi();
                        tmp.makeObject(response);
                        currentUser.getInstance().setKazi(tmp);
                        kazirname.setText(tmp.getName());
                        kaziredu.setText(tmp.getEduBackground());
                        kazidob.setText(tmp.getDob());
                        kazinid.setText(tmp.getNid());
                        kazitin.setText(tmp.getTin());
                        kaziremail.setText(tmp.getKaziEmail());
                        kazirlicense.setText(tmp.getKaziLicenceNumber());
                        kaziroffice.setText(tmp.getOfficeAddress());
                        kazirarea.setText(tmp.getPreferedArea());
                        kazirnumber.setText(tmp.getContact());

                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), "Something went wrong. Try again 11", Toast.LENGTH_SHORT).show();
                    }
                });
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
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
            //Toast.makeText(context, "Kazi's Profile", Toast.LENGTH_SHORT).show();
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

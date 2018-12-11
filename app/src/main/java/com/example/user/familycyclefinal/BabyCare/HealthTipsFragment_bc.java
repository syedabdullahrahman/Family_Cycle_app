package com.example.user.familycyclefinal.BabyCare;

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
 * {@link HealthTipsFragment_bc.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthTipsFragment_bc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthTipsFragment_bc extends Fragment {


    HashMap<String,List<String>> myHeader;
    List<String> myChild;
    ExpandableListView explist;
    MyAdapterHTipsBc adapter;


    //private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    //private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();

    //private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;









    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HealthTipsFragment_bc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthTipsFragment_bc.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthTipsFragment_bc newInstance(String param1, String param2) {
        HealthTipsFragment_bc fragment = new HealthTipsFragment_bc();
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
        View view = inflater.inflate(R.layout.fragment_health_tips_fragment_bc, container, false);

        explist = (ExpandableListView)view.findViewById(R.id.expandable_listvieww);
        myHeader =  MyAdapterHTipsBc.DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new MyAdapterHTipsBc(getContext(),myHeader,myChild);
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




class MyAdapterHTipsBc extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> childTitles;
    private List<String> headTitles;


  /*  MyAdapterHTipsBc(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {
        this.ctx = ctx;
        this.childTitles = childTitles;
        this.headTitles = headTitles;
    } */

    MyAdapterHTipsBc(Context ctx, HashMap<String, List<String>> childTitles, List<String> headTitles) {

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
         HeaderDetails.put("Hints for Nursing", ChildDetails1);
            HeaderDetails.put("Sleeping", ChildDetails2);
            HeaderDetails.put("Keeping your baby awake during feeding", ChildDetails3);
            HeaderDetails.put("Help your baby bond with Dad", ChildDetails4);
            HeaderDetails.put("Let Your Baby Lead The Way", ChildDetails5);
            HeaderDetails.put("Baby's first baths", ChildDetails6);
            HeaderDetails.put("Know when to ask for help", ChildDetails7);
            HeaderDetails.put("Remove Diaper Rashes", ChildDetails8);

         */

        if (header == "Keeping your baby awake during feeding"){
            imageView.setImageResource(R.drawable.baby_feeding);
        }
        if (header == "Sleeping"){
            imageView.setImageResource(R.drawable.baby_sleeping);
        }
        if (header == "Hints for Nursing"){
            imageView.setImageResource(R.drawable.babynursing);
        }
        if (header == "Help your baby bond with Dad"){
            imageView.setImageResource(R.drawable.babydad);
        }
        if (header == "Let Your Baby Lead The Way"){
            imageView.setImageResource(R.drawable.babylead);
        }
        if (header == "Baby's first baths"){
            imageView.setImageResource(R.drawable.babybath);
        }
        if (header == "Know when to ask for help"){
            imageView.setImageResource(R.drawable.babyhelp);
        }
        if (header == "Remove Diaper Rashes"){
            imageView.setImageResource(R.drawable.babydiaper);
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
            ChildDetails1.add("Babies eat and eat and eat. Although nature has done a pretty good job of providing you and your baby with the right equipment, in the beginning it's almost guaranteed to be harder than you expected. From sore nipples to tough latch-ons, nursing can seem overwhelming.");
            ChildDetails1.add("1. Women who seek help have a higher success rate. \"Think of ways to ensure success before you even give birth,\" suggests Stacey Brosnan, a lactation consultant in New York City. Talk with friends who had a good nursing experience, ask baby's pediatrician for a lactation consultant's number, or attend a La Leche League (nursing support group) meeting (see laleche.org to find one).");
            ChildDetails1.add("2. Use hospital resources. Kira Sexton, a Brooklyn, New York, mom, says, \"I learned everything I could about breastfeeding before I left the hospital.\" Ask if there's a nursing class or a lactation consultant on staff. Push the nurse-call button each time you're ready to feed the baby, and ask a nurse to spot you and offer advice.");
            ChildDetails1.add("3. Prepare. At home, you'll want to drop everything to feed the baby the moment she cries for you. But Heather O'Donnell, a mom in New York City, suggests taking care of yourself first. \"Get a glass of water and a book or magazine to read.\" And, because breastfeeding can take a while, she says, pee first! ");
            ChildDetails1.add("4. Try a warm compress if your breasts are engorged or you have blocked ducts. A heating pad or a warm, wet washcloth works, but a flax pillow (often sold with natural beauty products) is even better. \"Heat it in the microwave, and conform it to your breast,\" says Laura Kriska, a mom in Brooklyn, New York ");
            ChildDetails1.add("5. Heat helps the milk flow, but if your breasts are sore after nursing, try a cold pack. Amy Hooker, a San Diego mom, says, \"A bag of frozen peas worked really well for me.\" ");
            ChildDetails1.add("6. If you want baby to eventually take a bottle, introduce it after breastfeeding is established but before the 3-month mark. Many experts say 6 to 8 weeks is good, but \"we started each of our kids on one bottle a day at 3 weeks,\" says Jill Sizemore, a mom in Pendleton, Indiana ");


            List<String> ChildDetails2 = new ArrayList<String>();
            ChildDetails2.add("If your infant isn't eating, he's probably sleeping. Newborns log as many as 16 hours of sleep a day but only in short bursts. The result: You'll feel on constant alert and more exhausted than you ever thought possible. Even the best of us can come to resent the severe sleep deprivation.");
            ChildDetails2.add("Stop obsessing about being tired. There's only one goal right now: Care for your baby. \"You're not going to get a full night's sleep, so you can either be tired and angry or just tired,\" says Vicki Lansky, author of Getting Your Child to Sleep...and Back to Sleep (Book Peddlers). \"Just tired is easier.\"");
            ChildDetails2.add("Take shifts. One night it's Mom's turn to rock the cranky baby, the next it's Dad's turn. Amy Reichardt and her husband, Richard, parents in Denver, worked out a system for the weekends, when Richard was off from work. \"I'd be up with the baby at night but got to sleep in. Richard did all the morning care, then got to nap later.\"");
            ChildDetails2.add(" The old adage \"Sleep when your baby sleeps\" really is the best advice. \"Take naps together and go to bed early,\" says Sarah Clark, a mom in Washington, D.C.");
            ChildDetails2.add(" What if your infant has trouble sleeping? Do whatever it takes: Nurse or rock baby to sleep; let your newborn fall asleep on your chest or in the car seat. \"Don't worry about bad habits yet. It's about survival -- yours!\" says Jean Farnham, a Los Angeles mom.");



            List<String> ChildDetails3 = new ArrayList<String>();
            ChildDetails3.add("When our baby was eating slowly and sleepily, my husband and I would massage her cheek to stimulate her to eat faster. A gentle stroke with a fingertip on her cheek was all it took, and on those long sleepless nights, this simple trick was a godsend! Our friends have found it works great with their infants too. When babies eat efficiently until they're full before going to sleep, they sleep for longer between feedings. And that means you’re both likely to be calmer!");



            List<String> ChildDetails4 = new ArrayList<String>();
            ChildDetails4.add("Make sure your baby has ample time alone with Daddy. His touch and voice are different than yours, and this will begin a bonding process and give you a break. Plus, it gets the baby used to being with someone other than you. The first few times can be hard. Make sure your baby is fed and well rested, as this will give you at least one or two hours before you're needed again. Then leave Dad and the baby alone. If you stay nearby, make sure the baby can’t see or hear you, and resist the urge to go into the room and \"fix\" things if she starts crying. Your baby cries with you and you experiment to find out what's wrong. Dads need time to do this too - in their own way. By allowing this time, your child will learn there is more than one way to receive comfort, which will help immensely when you leave your baby with a sitter or another family member for the first time. You could have your partner bathe her, put her to bed or just read or talk to her.");

            List<String> ChildDetails5 = new ArrayList<String>();
            ChildDetails5.add("Being a first-time parent can be stressful - especially when everyone wants to put in their two cents and what they're telling you doesn't feel right. As soon as I came home with my baby, my friends and relatives started giving me advice (more like demands) on how to raise her - they wanted me to do everything on schedule. It was nerve-racking, but I learned to ignore it and remember that this is my child. I couldn't bear the thought of hearing him cry in hunger because it hadn’t been three hours since his last feeding. If you let your baby - not someone else - tell you when he is hungry or tired, you will find that he (and you!) will be much happier and healthier.");

            List<String> ChildDetails6 = new ArrayList<String>();
            ChildDetails6.add("After the baby's umbilical cord stump falls off (generally by week 3), you’ll finally be able to give her a real bath. To keep the baby warmer, more comfortable and less likely to cry, place a warm washcloth over her tummy during the bath. It makes all the difference between a happy water baby and a miserable one. Also, if your house is on the colder side, turn up the heat a little before the bath so the cold air won't be as much of a shock after the bath. These tips made all the difference for my little girl - she loves bath time.");

            List<String> ChildDetails7 = new ArrayList<String>();
            ChildDetails7.add("If you're having trouble breast-feeding, ask a lactation consultant or your baby's doctor for help — especially if every feeding is painful or your baby isn't gaining weight. If you haven't worked with a lactation consultant, ask your baby's doctor for a referral or check with the obstetrics department at a local hospital.");

            List<String> ChildDetails8 = new ArrayList<String>();
            ChildDetails8.add(" These are very common in babies and can be prevented and healed quite easily. Just make sure you change your baby’s diapers after every bowel movement and, after cleaning the area with a mild soap and water, apply a cream that contains zinc oxide. This cream prevents the formation of unwanted moisture and keeps rashes at bay.\n" +"\n" +"Nothing but the best for your precious little one! ");


            HeaderDetails.put("Hints for Nursing", ChildDetails1);
            HeaderDetails.put("Sleeping", ChildDetails2);
            HeaderDetails.put("Keeping your baby awake during feeding", ChildDetails3);
            HeaderDetails.put("Help your baby bond with Dad", ChildDetails4);
            HeaderDetails.put("Let Your Baby Lead The Way", ChildDetails5);
            HeaderDetails.put("Baby's first baths", ChildDetails6);
            HeaderDetails.put("Know when to ask for help", ChildDetails7);
            HeaderDetails.put("Remove Diaper Rashes", ChildDetails8);


            return HeaderDetails;
        }
    }
}







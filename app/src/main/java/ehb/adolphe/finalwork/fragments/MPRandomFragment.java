package ehb.adolphe.finalwork.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.activities.GameActivity;
import ehb.adolphe.finalwork.adapter.CourseAdapter;
import ehb.adolphe.finalwork.adapter.SubjectAdapter;
import ehb.adolphe.finalwork.model.Course;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

import static android.view.Gravity.CENTER;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MPRandomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MPRandomFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private FeatureCoverFlow coverFlow;
    private CourseAdapter courseAdapter;
    private List<Course> courses = new ArrayList<>();
    private TextSwitcher mTitle;

    public MPRandomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LBGlobalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LBGlobalFragment newInstance(String param1, String param2) {
        LBGlobalFragment fragment = new LBGlobalFragment();
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
        View view = inflater.inflate(R.layout.fragment_mp_random, container, false);
        initData();
        mTitle = view.findViewById(R.id.title);
        mTitle.setFactory(() -> {
            LayoutInflater inf = LayoutInflater.from(getActivity());
            TextView txt = (TextView)inf.inflate(R.layout.layout_title,null);
            txt.setTextColor(R.color.midnight_grey);
            txt.setTextSize(30);
            txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            return txt;
        });
        Animation in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation( getActivity().getApplicationContext(), R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        courseAdapter = new CourseAdapter(courses,getActivity().getApplicationContext());
        coverFlow = view.findViewById(R.id.coverFlow);
        coverFlow.setAdapter(courseAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText( "Take a quick " + courses.get(position).getName() + " quiz!");
            }

            @Override
            public void onScrolling() {

            }
        });

        //als je op foto drukt zal er iets gebeuren.
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //naar login gaan
                Log.d("myTag", "test click");
                //Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                // startActivity(intentLogin);

                //popup single or multi kiezen
                startActivity(new Intent(getActivity().getApplicationContext(), GameActivity.class));
            }
        });

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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initData() {

        courses.add(new Course("C#","https://camo.githubusercontent.com/0617f4657fef12e8d16db45b8d73def73144b09f/68747470733a2f2f646576656c6f7065722e6665646f726170726f6a6563742e6f72672f7374617469632f6c6f676f2f6373686172702e706e67"));
        courses.add(new Course("Html","https://cdn0.iconfinder.com/data/icons/HTML5/512/HTML_Logo.png"));
        courses.add(new Course("C++","https://raw.githubusercontent.com/isocpp/logos/master/cpp_logo.png"));
        courses.add(new Course("Java","https://qph.fs.quoracdn.net/main-qimg-48b7a3d8958565e7aa3ad4dbf2312770.webp"));
        courses.add(new Course("SapUI5","https://www.simplifier.io/wp-content/uploads/2018/01/sapui5-logo_simplifier.png"));
        courses.add(new Course("JS","https://i1.wp.com/theicom.org/wp-content/uploads/2016/03/js-logo.png?fit=500%2C500&ssl=1"));
        courses.add(new Course("XML","https://pngimage.net/wp-content/uploads/2018/06/xml-logo-png-3.png"));
        courses.add(new Course("NodeJS","https://www.sitevela.com/img2/nodejs_i1.png"));
        courses.add(new Course("Android","http://pngimg.com/uploads/android_logo/android_logo_PNG34.png"));
        courses.add(new Course("Swift","https://www.symphony-solutions.eu/wp-content/uploads/2018/04/programming-language-swift.png"));
        courses.add(new Course("React","https://neoteric.eu/wp-content/uploads/2015/08/react-logo-300x300.png"));
    }
}

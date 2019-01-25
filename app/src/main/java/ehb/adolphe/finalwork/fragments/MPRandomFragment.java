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
import ehb.adolphe.finalwork.activities.MainActivity;
import ehb.adolphe.finalwork.activities.ModeActivity;
import ehb.adolphe.finalwork.adapter.SubjectAdapter;
import ehb.adolphe.finalwork.model.Subject;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


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
    private SubjectAdapter subjectAdapter;
    private List<Subject> subjectList = new ArrayList<>();
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
            return txt;
        });
        Animation in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation( getActivity().getApplicationContext(), R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        subjectAdapter = new SubjectAdapter(subjectList,getActivity().getApplicationContext());
        coverFlow = view.findViewById(R.id.coverFlow);
        coverFlow.setAdapter(subjectAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(subjectList.get(position).getName());
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

        subjectList.add(new Subject("C#","https://banner2.kisspng.com/20180831/iua/kisspng-c-programming-language-logo-microsoft-visual-stud-atlas-portfolio-5b89919299aab1.1956912415357423546294.jpg"));
        subjectList.add(new Subject("Html","https://cdn0.iconfinder.com/data/icons/HTML5/512/HTML_Logo.png"));
        subjectList.add(new Subject("C++","https://raw.githubusercontent.com/isocpp/logos/master/cpp_logo.png"));
    }
}

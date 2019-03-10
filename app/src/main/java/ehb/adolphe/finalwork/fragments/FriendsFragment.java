package ehb.adolphe.finalwork.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.adapter.ListAdapter;
import ehb.adolphe.finalwork.model.Friend;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends ListFragment implements android.widget.SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    static List<Friend> friends;
    private ListAdapter adapter;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    private int fragmentId;

    public FriendsFragment() {
        // Required empty public constructor
    }


    public static FriendsFragment newInstance(ArrayList<Friend> ff) {
        FriendsFragment fragment = new FriendsFragment();
        fragment.setFriends(ff);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_fsearch, container, false);
        ListView listView = layout.findViewById(android.R.id.list);
        TextView emptyTextView = layout.findViewById(R.id.empty);
        listView.setEmptyView(emptyTextView);
        setListAdapter(adapter);
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed (Uri uri){
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach (Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach () {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onMenuItemActionExpand (MenuItem menuItem){
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse (MenuItem menuItem){
        return true;
    }

    @Override
    public boolean onQueryTextSubmit (String s){
        return true;
    }

    @Override
    public boolean onQueryTextChange (String charSequence){

        if (charSequence == null || charSequence.trim().isEmpty()) {
            adapter = new ListAdapter(friends, false);
            setListAdapter(adapter);
            return false;
        }
        adapter.getFilter().filter(charSequence);
        return true;
    }

    @Override
    public void onListItemClick (ListView l, View v,int position, long id){
        super.onListItemClick(l, v, position, id);
        String item = (String) l.getAdapter().getItem(position);
        // TODO: itemClickListener oproepen om informatie door te geven
        getFragmentManager().popBackStack();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("");
    }


    public void setFriends(List<Friend> friends) {
        this.friends = friends;
        this.adapter = new ListAdapter(friends, false);
        setListAdapter(adapter);
    }

    public void setAdapter(ListAdapter adapter) {
        this.adapter = adapter;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setmListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public void setFragmentId(int fragmentId) {
        this.fragmentId = fragmentId;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public ListAdapter getAdapter() {
        return adapter;
    }

    public Context getmContext() {
        return mContext;
    }

    public OnFragmentInteractionListener getmListener() {
        return mListener;
    }

    public int getFragmentId() {
        return fragmentId;
    }


}



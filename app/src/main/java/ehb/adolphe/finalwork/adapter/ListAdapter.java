package ehb.adolphe.finalwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.databinding.FriendListItemBinding;
import ehb.adolphe.finalwork.databinding.FsearchItemBinding;
import ehb.adolphe.finalwork.model.Friend;

public class ListAdapter extends BaseAdapter implements Filterable {
    List<Friend> mData;
    List<Friend> friendList;
    ValueFilter filter;
    private LayoutInflater inflater;
    Boolean forSearch;

    public ListAdapter(List<Friend> friends, Boolean forSearch) {
        mData=friends;
        this.forSearch = forSearch;
        friendList = friends;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Friend getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        Friend f = mData.get(position);
        FsearchItemBinding rowItemBinding1 = null;
        FriendListItemBinding rowItemBinding2 = null;
        if(forSearch){
            rowItemBinding1 = DataBindingUtil.inflate(inflater, R.layout.fsearch_item, parent, false);
            rowItemBinding1.friendName.setText(f.getFname()+ " " + f.getLname());
            rowItemBinding1.friendEmail.setText(f.getEmail());
            rowItemBinding1.playAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("user", new Gson().toJson(mData.get(position)));
                    // TODO: implement onClick
                }
            });
        } {
            rowItemBinding2 = DataBindingUtil.inflate(inflater, R.layout.friend_list_item, parent, false);
            rowItemBinding2.friendName.setText(f.getFname()+ " " + f.getLname());
            rowItemBinding2.friendEmail.setText(f.getEmail());
        }
        return forSearch? rowItemBinding1.getRoot(): rowItemBinding2.getRoot();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new ValueFilter();
        }
        return filter;
    }

    class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Friend> filterList = new ArrayList<>();
                for (int i = 0; i < friendList.size(); i++) {
                    if (((friendList.get(i).getFname() + " " +
                            friendList.get(i).getLname()).toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        filterList.add(friendList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = friendList.size();
                results.values = friendList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List<Friend>) results.values;
            notifyDataSetChanged();
        }

    }
}

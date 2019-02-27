package ehb.adolphe.finalwork.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;
import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.databinding.FsearchItemBinding;
import ehb.adolphe.finalwork.model.Friend;

public class ListAdapter extends BaseAdapter implements Filterable {
    List<Friend> mData;
    List<Friend> mStringFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;

    public ListAdapter(List<Friend> cancel_type) {
        mData=cancel_type;
        mStringFilterList = cancel_type;
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
        FsearchItemBinding rowItemBinding = DataBindingUtil.inflate(inflater, R.layout.fsearch_item, parent, false);
        rowItemBinding.friendName.setText(f.getFname()+ " " + f.getLname());
        rowItemBinding.friendEmail.setText(f.getEmail());

        return rowItemBinding.getRoot();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Friend> filterList = new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if (((mStringFilterList.get(i).getFname() + " " + mStringFilterList.get(i).getLname()).toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
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

package ehb.adolphe.finalwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.model.Subject;

public class SubjectAdapter extends BaseAdapter {

    private List<Subject> subjectList;
    private Context mContext;

    public SubjectAdapter(List<Subject> subjectList, Context mContext) {
        this.subjectList = subjectList;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public Object getItem(int i) {
        return subjectList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;

        if(rowView == null) {
            rowView = LayoutInflater.from(mContext).inflate(R.layout.layout_item,null);
            TextView name = rowView.findViewById(R.id.label);
            ImageView image = rowView.findViewById(R.id.image);

            Picasso.with(mContext).load(subjectList.get(i).getImageURL()).into(image);
            name.setText(subjectList.get(i).getName());
        }
        return rowView;



    }
}

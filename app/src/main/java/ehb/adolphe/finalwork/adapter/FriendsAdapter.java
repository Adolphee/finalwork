package ehb.adolphe.finalwork.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.model.Friend;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {
    Context ctx;
    ArrayList<Friend> friends;
    FriendViewHolder viewHolder;
    int index;

    public FriendsAdapter(Context ctx, ArrayList<Friend> friends) {
        this.ctx = ctx;
        this.friends = friends;
    }

    public FriendViewHolder getViewholder(){ return viewHolder; }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater li = LayoutInflater.from(ctx);
        view = li.inflate(R.layout.friend_list_item, viewGroup, false);
        viewHolder = new FriendViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder friendViewHolder, final int i) {
        index = i;

        friendViewHolder.tv_fname.setText(friends.get(i).getFname() + " " + friends.get(i).getLname());
        friendViewHolder.tv_email.setText(friends.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        if(friends != null) return friends.size();
        return 0;
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {

        TextView tv_fname, tv_lname, tv_email, tv_city, tv_studies;
        ConstraintLayout friend_list_item;

        public ConstraintLayout getFriend_list_item() {
            return friend_list_item;
        }

        public FriendViewHolder(View itemView) {
            super(itemView);

            tv_fname = (TextView) itemView.findViewById(R.id.friend_name);
            //tv_lname = (TextView) itemView.findViewById();
            tv_email = (TextView) itemView.findViewById(R.id.friend_email);
            // tv_city = (ImageView) itemView.findViewById(R.id.book_img);
            // tv_studies = (ConstraintLayout) itemView.findViewById(R.id.bookcard);
        }
    }
}

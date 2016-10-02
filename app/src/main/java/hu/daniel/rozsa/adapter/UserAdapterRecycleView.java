package hu.daniel.rozsa.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class UserAdapterRecycleView extends RecyclerView.Adapter<UserAdapterRecycleView.MyViewHolder> {

    private List<User> nearbyUsers;
    private Context context;
    private MyOnItemClickListener onItemClickListener;

    public UserAdapterRecycleView(List<User> nearbyUsers, MyOnItemClickListener onClickListener) {
        this.nearbyUsers = nearbyUsers;
        this.onItemClickListener = onClickListener;
    }


    public interface MyOnItemClickListener {
        void onItemClick(User user);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                                      .inflate(R.layout.list_item_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User currentUser = nearbyUsers.get(position);
        holder.tvName.setText(currentUser.name);
        holder.tvAge.setText(String.valueOf(currentUser.age));
        holder.tvLocation.setText(currentUser.location);
        holder.imgProfile.setImageDrawable(ContextCompat.getDrawable(context, currentUser.profileResId));

        holder.itemView.setTag(position);
        holder.tvAge.setTag(position);
    }

    @Override
    public int getItemCount() {
        return nearbyUsers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvAge;
        TextView tvLocation;
        CircleImageView imgProfile;

        MyViewHolder(View itemView) {
            super(itemView);

            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
            tvName = (TextView) itemView.findViewById(R.id.tvUserName);
            imgProfile = (CircleImageView) itemView.findViewById(R.id.imgProfilePicture);

            tvAge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) tvAge.getTag();
                    Toast.makeText(context, "current age: " + nearbyUsers.get(pos).age, Toast.LENGTH_SHORT)
                         .show();
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            User clickedUser = nearbyUsers.get(pos);
            onItemClickListener.onItemClick(clickedUser);
        }
    }

}

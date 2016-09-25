package hu.daniel.rozsa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class UserAdapter extends BaseAdapter {


    private List<User> users;

    public UserAdapter(List<User> nearbyUsers) {
        users = nearbyUsers;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            Context context = viewGroup.getContext();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_user, viewGroup, false);
            holder.tvAge = (TextView) convertView.findViewById(R.id.tvAge);
            holder.tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvUserName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User currentUser = getItem(position);
        holder.tvName.setText(currentUser.name);
        holder.tvAge.setText(String.valueOf(currentUser.age));
        holder.tvLocation.setText(currentUser.location);

        return convertView;
    }


    private class ViewHolder {
        TextView tvName;
        TextView tvAge;
        TextView tvLocation;
    }
}

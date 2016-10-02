package hu.daniel.rozsa.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.rozsa.Logic;
import hu.daniel.rozsa.activity.MainActivity;
import hu.daniel.rozsa.adapter.UserAdapter;
import hu.daniel.rozsa.adapter.UserAdapterRecycleView;
import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class UserListFragment extends Fragment {

    private UserAdapter userAdapter;
    private UserAdapterRecycleView userAdapterRecycleView;

    public static UserListFragment newInstance() {

        Bundle args = new Bundle();

        UserListFragment fragment = new UserListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lvUsers = (ListView) view.findViewById(R.id.lvUsers);
        final RecyclerView recyclerViewUsers = (RecyclerView) view.findViewById(R.id.recycleViewUser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewUsers.setLayoutManager(layoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        final List<User> nearbyUsers = new ArrayList<>();

        userAdapter = new UserAdapter(nearbyUsers);
        userAdapterRecycleView = new UserAdapterRecycleView(nearbyUsers, new UserAdapterRecycleView.MyOnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                ((MainActivity)getActivity()).moveToUserDetailScreen(user);
            }
        });


        lvUsers.setAdapter(userAdapter);
        recyclerViewUsers.setAdapter(userAdapterRecycleView);

        Logic.getInstance()
             .getNearbyUsersInteractor()
             .getNearbyUsers(new OnCompleteResult<List<User>>() {
                 @Override
                 public void onSuccess(List<User> result) {
                     generateImageForUser(result);
                     nearbyUsers.clear();
                     nearbyUsers.addAll(result);
                     userAdapter.notifyDataSetChanged();
                     userAdapterRecycleView.notifyDataSetChanged();
                 }

                 @Override
                 public void onError(String errorMsg) {

                 }
             });

        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = nearbyUsers.get(position);
                ((MainActivity)getActivity()).moveToUserDetailScreen(selectedUser);
            }
        });
    }

    private void generateImageForUser(List<User> result) {
        for (User user : result) {
            int profileResId = user.profileResId;
            switch (profileResId) {
                case 0:
                    user.profileResId = R.drawable.fake_bean;
                    break;
                case 1:
                    user.profileResId = R.drawable.fake_jon;
                    break;
                case 2:
                    user.profileResId = R.drawable.fake_kim;
                    break;
                case 3:
                    user.profileResId = R.drawable.fake_lagertha;
                    break;
                case 4:
                    user.profileResId = R.drawable.fake_ragnar;
                    break;
                case 5:
                    user.profileResId = R.drawable.fake_wut;
                    break;
                case 6:
                    user.profileResId = R.drawable.fake_ygritte;
                    break;
            }
        }
    }
}

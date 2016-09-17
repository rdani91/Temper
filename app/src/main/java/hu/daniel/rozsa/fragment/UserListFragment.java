package hu.daniel.rozsa.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.rozsa.Logic;
import hu.daniel.rozsa.adapter.UserAdapter;
import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class UserListFragment extends Fragment {

    private UserAdapter userAdapter;

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
        final List<User> nearbyUsers = new ArrayList<>();
        userAdapter = new UserAdapter(nearbyUsers);
        lvUsers.setAdapter(userAdapter);

        Logic.getInstance()
             .getGatewayFactory()
             .getUserGateway()
             .getNearbyUsers(User.Gender.FEMALE, new OnCompleteResult<List<User>>() {
                 @Override
                 public void onSuccess(List<User> result) {
                     nearbyUsers.clear();
                     nearbyUsers.addAll(result);
                     userAdapter.notifyDataSetChanged();
                 }

                 @Override
                 public void onError(String errorMsg) {

                 }
             });
    }
}

package hu.daniel.rozsa.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hu.daniel.rozsa.logic.entity.User;
import hu.rozsa.daniel.tender.R;

public class UserDetailScreen  extends Fragment{
    private static final String EXTRA_USER = "extra_user";

    private ImageView imgProfilePicture;
    private Context context;

    public static UserDetailScreen newInstance(User selectedUser) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_USER, selectedUser);
        UserDetailScreen fragment = new UserDetailScreen();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadUIElements(view);
        User selectedUser = (User) getArguments().getSerializable(EXTRA_USER);

        imgProfilePicture.setImageDrawable(ContextCompat.getDrawable(context, selectedUser.profileResId));
    }

    private void loadUIElements(View rootView) {
        imgProfilePicture = (ImageView) rootView.findViewById(R.id.imgProfilePicture);
    }
}

package com.derick.uts.ui.notifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.derick.uts.DaftarActivity;
import com.derick.uts.LoginActivity;
import com.derick.uts.MainActivity;
import com.derick.uts.R;
import com.derick.uts.databinding.FragmentNotificationsBinding;

import org.w3c.dom.Text;

import java.util.Objects;

public class NotificationsFragment extends Fragment{

    private FragmentNotificationsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView txtNama = (TextView) getActivity().findViewById(R.id.txtNama);
        String nama = getActivity().getIntent().getExtras().getString("username");
        txtNama.setText(nama);

        Button btnLogout = (Button) getActivity().findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
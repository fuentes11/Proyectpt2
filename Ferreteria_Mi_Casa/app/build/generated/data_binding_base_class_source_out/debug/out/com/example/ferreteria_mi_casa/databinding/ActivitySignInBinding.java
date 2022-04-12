// Generated by view binder compiler. Do not edit!
package com.example.ferreteria_mi_casa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ferreteria_mi_casa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignInBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnout;

  @NonNull
  public final Button btnsingup;

  @NonNull
  public final EditText etxgmailsignin;

  @NonNull
  public final EditText etxnumingin;

  @NonNull
  public final EditText etxpasswordsingin;

  private ActivitySignInBinding(@NonNull LinearLayout rootView, @NonNull Button btnout,
      @NonNull Button btnsingup, @NonNull EditText etxgmailsignin, @NonNull EditText etxnumingin,
      @NonNull EditText etxpasswordsingin) {
    this.rootView = rootView;
    this.btnout = btnout;
    this.btnsingup = btnsingup;
    this.etxgmailsignin = etxgmailsignin;
    this.etxnumingin = etxnumingin;
    this.etxpasswordsingin = etxpasswordsingin;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignInBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignInBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_in, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignInBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnout;
      Button btnout = ViewBindings.findChildViewById(rootView, id);
      if (btnout == null) {
        break missingId;
      }

      id = R.id.btnsingup;
      Button btnsingup = ViewBindings.findChildViewById(rootView, id);
      if (btnsingup == null) {
        break missingId;
      }

      id = R.id.etxgmailsignin;
      EditText etxgmailsignin = ViewBindings.findChildViewById(rootView, id);
      if (etxgmailsignin == null) {
        break missingId;
      }

      id = R.id.etxnumingin;
      EditText etxnumingin = ViewBindings.findChildViewById(rootView, id);
      if (etxnumingin == null) {
        break missingId;
      }

      id = R.id.etxpasswordsingin;
      EditText etxpasswordsingin = ViewBindings.findChildViewById(rootView, id);
      if (etxpasswordsingin == null) {
        break missingId;
      }

      return new ActivitySignInBinding((LinearLayout) rootView, btnout, btnsingup, etxgmailsignin,
          etxnumingin, etxpasswordsingin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
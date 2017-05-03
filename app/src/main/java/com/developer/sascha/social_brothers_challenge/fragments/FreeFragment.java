package com.developer.sascha.social_brothers_challenge.fragments;


import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.sascha.social_brothers_challenge.opengl.MyGLRenderer;


/**
 *  Fragment Containing 9 OpenGL Cubes
 * */

public class FreeFragment extends Fragment {

    public FreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Create a new OpenGL view
        GLSurfaceView view = new GLSurfaceView(this.getActivity());
        view.setRenderer(new MyGLRenderer());
        return view;
    }

}

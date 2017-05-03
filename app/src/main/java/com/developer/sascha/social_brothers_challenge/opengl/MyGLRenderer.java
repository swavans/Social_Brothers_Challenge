package com.developer.sascha.social_brothers_challenge.opengl;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.developer.sascha.social_brothers_challenge.shapes.Cube;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private Cube mCube = new Cube();
    private float mCubeRotation;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        gl.glClearDepthf(2.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        for (float i = -5.0f; i <= 5; i += 5) {
            for (float j = -5.0f; j <= 5; j += 5) {
                gl.glPushMatrix();
                gl.glTranslatef(i, j, -25.0f);
                gl.glRotatef(mCubeRotation, 1.0f, 1.0f, 1.0f);
                mCube.draw(gl);
                gl.glPopMatrix();
            }
        }
        gl.glLoadIdentity();
        mCubeRotation -= 0.75f;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
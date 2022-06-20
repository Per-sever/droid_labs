package com.example.laboratorywork7;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class MyCube {
    private final int mProgramm;
    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    static final int COORDS_PER_VERTEX = 3;

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    " gl_Position = uMVPMatrix * vPosition;" +
//                    "gl_PointSize = 10.0" +
                    "}";
    private int vPMatrixHandle;


    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "gl_FragColor = vColor;" +
                    "}";


    static float squareCoords[] = {
            -0.5f, 0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f};
    private short drawOrder[] = {
            0, 1, 2, 0, 2, 3,
            4, 5, 6, 4, 6, 7,
            7, 6, 1, 7, 1, 0,
            1, 6, 5, 1, 6, 2,
            3, 2, 5, 3, 5, 4,
            7, 0, 3, 7, 3, 4};
    float color[] = {0.0f, 0.7f, 0.7f, 1.0f};

    public MyCube() {
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        mProgramm = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgramm, vertexShader);
        GLES20.glAttachShader(mProgramm, fragmentShader);
        GLES20.glLinkProgram(mProgramm);


    }

    private int positionHandle;
    private int colorHandle;

    private final int vertexCount = squareCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    public void draw(float[] mvpMatrix) {
        // Добавляем программу в среду OpenGL ES
        GLES20.glUseProgram(mProgramm);
        // Получаем элемент vPosition вершинного шейдера
        positionHandle = GLES20.glGetAttribLocation(mProgramm, "vPosition");
        // Подключаем массив вершин
        GLES20.glEnableVertexAttribArray(positionHandle);
        // Подготовка координат вершин треугольника
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
        // Получаем элемент vColor фрагментного шейдера
        colorHandle = GLES20.glGetUniformLocation(mProgramm, "vColor");
        // Устанавливаем цвет для рисования треугольника
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
        // Рисуем треугольник
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgramm, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
                            GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, vertexCount);
        // Отключаем массив вершин
        GLES20.glDisableVertexAttribArray(positionHandle);
    }


}

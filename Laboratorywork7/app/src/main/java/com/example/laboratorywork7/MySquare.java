package com.example.laboratorywork7;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class MySquare {
    private final int mProgram;


    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    " gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "gl_FragColor = vColor;" +
                    "}";


    private int positionHandle;
    private int colorHandle;

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
            -0.5f, 0.5f, 0.0f, // верхняя левая вершина
            -0.5f, -0.5f, 0.0f, // нижняя левая вершина
            0.5f, -0.5f, 0.0f, // нижняя правая вершина
            0.5f, 0.5f, 0.0f}; // верхняя правая вершина
    private short drawOrder[] = {0, 1, 2, 0, 2, 3}; //порядок рисования
    float color[] = {0.0f, 0.5f, 0.5f, 1.0f};

    private final int vertexCount = squareCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;


    public MySquare() {

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

        // создание пустой OpenGL ES программы
        mProgram = GLES20.glCreateProgram();
        // Добавление вершинного шейдера в программу
        GLES20.glAttachShader(mProgram, vertexShader);
        // Добавление фрагментного шейдера в программу
        GLES20.glAttachShader(mProgram, fragmentShader);
        // Связывание программы
        GLES20.glLinkProgram(mProgram);
//
    }


    public void draw() {
        // Добавляем программу в среду OpenGL ES
        GLES20.glUseProgram(mProgram);
        // Получаем элемент vPosition вершинного шейдера
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // Подключаем массив вершин
        GLES20.glEnableVertexAttribArray(positionHandle);
        // Подготовка координат вершин треугольника
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
        // Получаем элемент vColor фрагментного шейдера
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // Устанавливаем цвет для рисования треугольника
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
        // Рисуем треугольник
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        // Отключаем массив вершин
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}

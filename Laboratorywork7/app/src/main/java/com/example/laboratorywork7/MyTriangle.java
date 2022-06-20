package com.example.laboratorywork7;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class MyTriangle {

    private final int mProgram;


    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    " gl_Position = uMVPMatrix * vPosition;" +
                    "}";
    private int vPMatrixHandle;


    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "gl_FragColor = vColor;" +
                    "}";

    private FloatBuffer vertexBuffer;

    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {0.0f, 0.622008459f, 0.0f, -0.5f, -0.311004243f, 0.0f, 0.5f, -0.311004243f, 0.0f};
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    private int positionHandle;
    private int colorHandle;

    public MyTriangle() {


        //инициализируем буфер байтов вершин для координат фигуры; 4 байта на одно значение float
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length *
                4);
        bb.order(ByteOrder.nativeOrder());
        // создаем буфер вершин из ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
//        ColorBuffer.put(color);
        vertexBuffer.position(0);


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


// 4 байта на вершину


    public void draw(float [] mvpMatrix) {
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
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        // Отключаем массив вершин
        GLES20.glDisableVertexAttribArray(positionHandle);
    }


}
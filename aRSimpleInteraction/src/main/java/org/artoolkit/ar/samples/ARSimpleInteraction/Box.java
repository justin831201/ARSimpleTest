package org.artoolkit.ar.samples.ARSimpleInteraction;

import android.opengl.GLES10;

import org.artoolkit.ar.base.rendering.RenderUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Justin on 2017/5/7.
 */

public class Box {
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;

    public Box() {
        this(40.0f, 0.0f, 0.0f, 20.0f);
    }

    public Box(float size) {
        this(size, 0.0F, 0.0F, 0.0F);
    }

    public Box(float size, float x, float y, float z) {
        this.setArrays(size, x, y, z);
    }

    private void setArrays(float size, float x, float y, float z) {
        float hs = size / 2.0F;
        float[] vertices = new float[]{x - hs, y - hs, z - hs, x + hs, y - hs, z - hs, x + hs, y + hs, z - hs, x - hs, y + hs, z - hs, x - hs, y - hs, z + hs, x + hs, y - hs, z + hs, x + hs, y + hs, z + hs, x - hs, y + hs, z + hs};
//        float[] vertices = new float[]{x - hs, y - hs, z - hs, x + hs, y - hs, z - hs, x + hs, y + hs, z - hs, x - hs, y + hs, z - hs, x - hs, y - hs, z + hs, x + hs, y - hs, z + hs};
        float c = 1.0f;
//        float[] colors = new float[]{0.0F, 0.0F, 0.0F, c, c, 0.0F, 0.0F, c, c, c, 0.0F, c, 0.0F, c, 0.0F, c, 0.0F, 0.0F, c, c, c, 0.0F, c, c, c, c, c, c, 0.0F, c, c, c};
        float[] colors = new float[]{0.0F, 0.0F, 0.0F, c, 0.0F, 0.0F, 0.0F, c, 0.0F, 0.0F, 0.0F, c, 0.0F, 0.0F, 0.0F, c, 0.0F, 0.0F, 0.0F, c, 0.0F, 0.0F, 0.0F, c, 0.0F, 0.0F, 0.0F, c, 0.0F, 0.0F, 0.0F, c};
        byte[] indices = new byte[]{0, 4, 5, 0, 5, 1, 1, 5, 6, 1, 6, 2, 2, 6, 7, 2, 7, 3, 3, 7, 4, 3, 4, 0, 4, 7, 6, 4, 6, 5, 3, 0, 1, 3, 1, 2};
        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        this.mColorBuffer = RenderUtils.buildFloatBuffer(colors);
        this.mIndexBuffer = RenderUtils.buildByteBuffer(indices);
    }

    public void draw(GL10 gl) {
        GLES10.glColorPointer(4, 5126, 0, this.mColorBuffer);
        GLES10.glVertexPointer(3, 5126, 0, this.mVertexBuffer);
        GLES10.glEnableClientState('聶');
        GLES10.glEnableClientState('聴');
        GLES10.glDrawElements(4, 36, 5121, this.mIndexBuffer);
        GLES10.glDisableClientState('聶');
        GLES10.glDisableClientState('聴');
    }

    public void setColor(float one, float two, float three) {
        float bottomOne = one - 0.4f;
        float bottomTwo = two - 0.4f;
        float bottomThree = three - 0.4f;
        float medianOne = one - 0.3f;
        float medianTwo = two - 0.3f;
        float medianThree = three - 0.3f;
        float alpha = 0.95f;
        float[] colors = new float[]{bottomOne, bottomTwo, bottomThree, alpha, bottomOne, bottomTwo, bottomThree, alpha, bottomOne, bottomTwo, bottomThree, alpha, medianOne, medianTwo, medianThree, alpha, medianOne, medianTwo, medianThree, alpha, medianOne, medianTwo, medianThree, alpha, medianOne, medianTwo, medianThree, alpha, one, two, three, alpha};
        this.mColorBuffer = RenderUtils.buildFloatBuffer(colors);
    }
}


/*
 *  ARSimpleInteraction.java
 *  ARToolKit5
 *
 *  Disclaimer: IMPORTANT:  This Daqri software is supplied to you by Daqri
 *  LLC ("Daqri") in consideration of your agreement to the following
 *  terms, and your use, installation, modification or redistribution of
 *  this Daqri software constitutes acceptance of these terms.  If you do
 *  not agree with these terms, please do not use, install, modify or
 *  redistribute this Daqri software.
 *
 *  In consideration of your agreement to abide by the following terms, and
 *  subject to these terms, Daqri grants you a personal, non-exclusive
 *  license, under Daqri's copyrights in this original Daqri software (the
 *  "Daqri Software"), to use, reproduce, modify and redistribute the Daqri
 *  Software, with or without modifications, in source and/or binary forms;
 *  provided that if you redistribute the Daqri Software in its entirety and
 *  without modifications, you must retain this notice and the following
 *  text and disclaimers in all such redistributions of the Daqri Software.
 *  Neither the name, trademarks, service marks or logos of Daqri LLC may
 *  be used to endorse or promote products derived from the Daqri Software
 *  without specific prior written permission from Daqri.  Except as
 *  expressly stated in this notice, no other rights or licenses, express or
 *  implied, are granted by Daqri herein, including but not limited to any
 *  patent rights that may be infringed by your derivative works or by other
 *  works in which the Daqri Software may be incorporated.
 *
 *  The Daqri Software is provided by Daqri on an "AS IS" basis.  DAQRI
 *  MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 *  THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
 *  FOR A PARTICULAR PURPOSE, REGARDING THE DAQRI SOFTWARE OR ITS USE AND
 *  OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 *
 *  IN NO EVENT SHALL DAQRI BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
 *  MODIFICATION AND/OR DISTRIBUTION OF THE DAQRI SOFTWARE, HOWEVER CAUSED
 *  AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
 *  STRICT LIABILITY OR OTHERWISE, EVEN IF DAQRI HAS BEEN ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 *
 *  Copyright 2015 Daqri, LLC.
 *  Copyright 2011-2015 ARToolworks, Inc.
 *
 *  Author(s): Julian Looser, Philip Lamb
 *
 */

package org.artoolkit.ar.samples.ARSimpleInteraction;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import org.artoolkit.ar.base.ARActivity;
import org.artoolkit.ar.base.rendering.ARRenderer;

import java.util.EventListener;

/**
 * Another simple example that includes a small amount of user interaction.
 */
public class ARSimpleInteraction extends ARActivity implements audioObserver {

    /**
     * A custom renderer is used to produce a new visual experience.
     */
    private String Tag = "Audio Manager";
    private SimpleInteractiveRenderer simpleRenderer = new SimpleInteractiveRenderer(this);
    private int audioFiles [] = {R.raw.baybreeze, R.raw.goodforyou, R.raw.theysay};
    private MediaPlayer[] audioPlayers = new MediaPlayer[3];
    private  boolean soundPlaying [] = new boolean[3];

    /**
     * The FrameLayout where the AR view is displayed.
     */
    private FrameLayout mainLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        mainLayout = (FrameLayout) this.findViewById(R.id.mainLayout);
        for (int i=0;i<audioPlayers.length;i++) {
            Log.e(Tag, "Create Media Player " + i);
            audioPlayers[i] = MediaPlayer.create(this, audioFiles[i]);
            audioPlayers[i].setVolume(1f, 1f);
            soundPlaying[i] = false;
        }
    }

    /**
     * By overriding {@link supplyRenderer}, the custom renderer will be used rather than
     * the default renderer which does nothing.
     *
     * @return The custom renderer to use.
     */
    @Override
    protected ARRenderer supplyRenderer() {
        return simpleRenderer;
    }

    /**
     * By overriding {@link supplyFrameLayout}, the layout within this Activity's UI will be
     * used.
     */
    @Override
    protected FrameLayout supplyFrameLayout() {
        return mainLayout;

    }

    @Override
    public void playMusic(int ID) {
        if (audioPlayers[ID] != null && soundPlaying[ID] == false) {
            Log.d(Tag, "[ ID: " + ID + " ] Start Music ");
            audioPlayers[ID].start();
            soundPlaying[ID] = true;
        }
        else {
            Log.e(Tag, "[ ID: " + ID + " ] Null To Start ");
        }
    }

    @Override
    public void stopMusic(int ID) {
        if (audioPlayers[ID] != null && soundPlaying[ID]) {
            Log.d(Tag, "[ ID: " + ID + " ] Stop Music");
            audioPlayers[ID].stop();
            soundPlaying[ID] = false;
        }
        else {
            Log.e(Tag, "[ ID: " + ID + " ] Null To Stop");
        }
    }
}